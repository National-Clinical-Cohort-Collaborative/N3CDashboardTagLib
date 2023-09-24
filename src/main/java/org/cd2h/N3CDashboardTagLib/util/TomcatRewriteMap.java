package org.cd2h.N3CDashboardTagLib.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import org.apache.catalina.valves.rewrite.RewriteMap;

public class TomcatRewriteMap implements RewriteMap {
	String[] params = null;
	Hashtable<String,String> rewriteMap = new Hashtable<String,String>();
	
	@Override
	public void setParameters(String... params) {
		this.params = params;
		System.out.println("TomcatRewriteMap " + params[0] + " processing parameters: " + arrayToString(params));
		load(params);
	}

	@Override
	public String setParameters(String params) {
		this.params = params.split(" ");
		System.out.println("TomcatRewriteMap " + this.params[0] + " processing parameters: " + params);
		load(this.params);
		return null;
	}

	@Override
	public String lookup(String key) {
		System.out.println("TomcatRewriteMap " + params[0] + " lookup: " + key + " -> " + rewriteMap.get(key));
		if (key.equals(params[0])) {
			System.out.println("TomcatRewriteMap " + params[0] + " reloading...");
			load(params);
		}
		return rewriteMap.get(key);
	}
	
	private void load(String[] params) {
		rewriteMap = new Hashtable<String,String>();
		
		// this one is built-in to support verification
		rewriteMap.put(params[0], "/redirects.jsp");
		
		// 0 - reload label
		// 1 - JDBC URL
		// 2 - schema
		// 3 - table
		// 4 - source
		// 5 - target
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(params[1]);
			
			PreparedStatement stmt = conn.prepareStatement("select " + params[4] + "," + params[5] + " from " + params[2] + "." + params[3]);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String source = rs.getString(1);
				String target = rs.getString(2);
				System.out.println("\tsource: " + source + "\ttarget: " + target);
				rewriteMap.put(source, target);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static String arrayToString(String[] theArray) {
		StringBuffer theBuffer = new StringBuffer();
		theBuffer.append("[");
		theBuffer.append(theArray[0]);

		for (int i = 1; i < theArray.length; i++)
			theBuffer.append(", " + theArray[i]);

		theBuffer.append("]");
		return theBuffer.toString();
	}
}
