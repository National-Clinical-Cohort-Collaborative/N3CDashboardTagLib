package org.cd2h.n3c_dashboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.cd2h.N3CDashboardTagLib.util.LocalProperties;
import org.cd2h.N3CDashboardTagLib.util.PropertyLoader;

public class Analyzer {
	static Logger logger = Logger.getLogger(Analyzer.class);
	static LocalProperties prop_file = null;
	static Connection conn = null;

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		PropertyConfigurator.configure(args[0]);
		prop_file = PropertyLoader.loadProperties("dashboard_analyzer");
		conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT table_name FROM information_schema.tables WHERE table_schema = 'n3c_dashboard_domain' order by 1");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String domain = rs.getString(1);
			switch(domain) {
			case "age_ideal":
				logger.info("Domain: age");
				processAge();
				break;
			case "age_secondary":
			case "age_minimum":
				break;
			default:
				logger.info("Domain: " + domain);
				processDomain(domain);
			}
		}
		stmt.close();
	}
	
	static void processAge() throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT table_name FROM information_schema.columns"
				+ " WHERE table_schema = 'n3c_dashboard_ph'" + " AND column_name = 'age'" + " order by 1");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String table = rs.getString(1);
			int idealCount = processAge("age_ideal", table);
			int secondaryCount = processAge("age_secondary", table);
			int minimumCount = processAge("age_minimum", table);
			if (idealCount > 1 && secondaryCount > 1 && minimumCount > 1) {
				PreparedStatement countStmt = conn.prepareStatement("select age,count(*) from n3c_dashboard_ph." + table + " group by 1 order by 1");
				ResultSet countRS = countStmt.executeQuery();
				while (countRS.next()) {
					if (countRS.isFirst()) {
						logger.info("\tTable: " + table);
						logger.info("\t\tideal: " + idealCount + "\tsecondary: " + secondaryCount + "\t minimum: " + minimumCount);						
					}
					String value = countRS.getString(1);
					int count = countRS.getInt(2);
					logger.info("\t\t\tvalue: " + value + "\tcount: " + count);
				}
				countStmt.close();
			}
		}
		stmt.close();
	}
	
	static int processAge(String domain, String table) throws SQLException {
		int count = 0;
		PreparedStatement stmt = conn.prepareStatement("select value,age,count(*)"
				+ " from n3c_dashboard_ph." + table + " full outer join n3c_dashboard_domain." + domain + " on (age=value)"
				+ " where value is null or " + domain + " is null group by 1,2");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String value = rs.getString(1);
			String dom = rs.getString(2);
			int actual = rs.getInt(3);
			logger.debug("\t\tdomain: " + value + "\tvalue:" + dom + "\tcount: " + actual);
			count++;
		}
		return count;
	}

	static void processDomain(String domain) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT table_name FROM information_schema.columns"
														+ " WHERE table_schema = 'n3c_dashboard_ph'"
														+ " AND column_name = ?"
														+ " order by 1");
		stmt.setString(1, domain);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String table = rs.getString(1);
			logger.debug("\tTable: " + table);
			processTable(domain, table);
		}
		stmt.close();
	}

	static void processTable(String domain, String table) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("select value,"+domain+",count(*)"
														+ " from n3c_dashboard_ph."+table+" full outer join n3c_dashboard_domain."+domain+" on ("+domain+"=value)"
														+ " where value is null or "+domain+" is null group by 1,2");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			if (rs.isFirst()) {
				logger.info("\tTable: " + table);
			}
			String value = rs.getString(1);
			String dom = rs.getString(2);
			int count = rs.getInt(3);
			logger.info("\t\tdomain: " + value + "\tvalue:" + dom + "\tcount: " + count);
		}

	}

	static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Properties props = new Properties();
		props.setProperty("user", prop_file.getProperty("jdbc.user"));
		props.setProperty("password", prop_file.getProperty("jdbc.password"));
		Connection conn = DriverManager.getConnection(prop_file.getProperty("jdbc.url"), props);
		conn.setAutoCommit(false);
		return conn;
	}

}
