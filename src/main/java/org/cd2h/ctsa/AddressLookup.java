package org.cd2h.ctsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
import org.json.JSONObject;
import org.json.JSONTokener;

public class AddressLookup {
	static Logger logger = Logger.getLogger(AddressLookup.class);
	static LocalProperties prop_file = null;
	static Connection conn = null;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		PropertyConfigurator.configure(args[0]);
		prop_file = PropertyLoader.loadProperties("ctsa");
		conn = getConnection();

		PreparedStatement stmt = conn.prepareStatement("select distinct perf_site_addr from ctsa.grants where perf_site_addr is not null and perf_site_addr not in (select perf_site_addr from ctsa.census_address_raw)");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String perfAddress = rs.getString(1);
			String address = perfAddress.trim();
			if (address.matches(".* [-0-9]+$"))
//			if (address.length() - address.lastIndexOf(' ') == 10 || address.length() - address.lastIndexOf(' ') == 6)
				address = address.substring(0,address.lastIndexOf(' '));
			logger.info("address: " + perfAddress + "\t" + URLEncoder.encode(address));
			
			if (address.length() >= 100) {
				logger.info("\tskipping due to length: " + address.length());
				continue;
			}
			URL requestURL = new URL("https://geocoding.geo.census.gov/geocoder/locations/onelineaddress?benchmark=4&format=json&address=" + URLEncoder.encode(address));
			BufferedReader reader = new BufferedReader(new InputStreamReader(requestURL.openConnection().getInputStream()));
			JSONObject response = new JSONObject(new JSONTokener(reader));
			logger.info("response: " + response.toString(3));
			
			PreparedStatement insStmt = conn.prepareStatement("insert into ctsa.census_address_raw values(?,?::jsonb)");
			insStmt.setString(1, perfAddress);
			insStmt.setString(2, response.toString(3));
			insStmt.executeUpdate();
			insStmt.close();
		}
		stmt.close();
	}

	static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Properties props = new Properties();
		props.setProperty("user", prop_file.getProperty("jdbc.user"));
		props.setProperty("password", prop_file.getProperty("jdbc.password"));
		Connection conn = DriverManager.getConnection(prop_file.getProperty("jdbc.url"), props);
		conn.setAutoCommit(true);
		return conn;
	}
}
