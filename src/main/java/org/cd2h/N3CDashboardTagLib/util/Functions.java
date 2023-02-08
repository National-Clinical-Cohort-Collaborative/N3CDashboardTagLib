package org.cd2h.N3CDashboardTagLib.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.jsp.JspTagException;

import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;

public class Functions {
	
	public static int dashboardDidByTitle(String title) throws JspTagException {
	    int ID = 0;

		Dashboard dashboard = new Dashboard();
		try {
			PreparedStatement stat = dashboard.getConnection().prepareStatement("select did from n3c_dashboard.dashboard where title = ?");
			stat.setString(1, title);
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				ID = crs.getInt(1);
			}
			stat.close();
			dashboard.freeConnection();
		} catch (Exception e) {
			throw new JspTagException("Error: JDBC error generating dashboardDidByName");
		}
		return ID;
	}

	public static int dashboardDidByPath(String path) throws JspTagException {
	    int ID = 0;

		Dashboard dashboard = new Dashboard();
		try {
			PreparedStatement stat = dashboard.getConnection().prepareStatement("select did from n3c_dashboard.dashboard where path = ?");
			stat.setString(1, path);
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				ID = crs.getInt(1);
			}
			stat.close();
			dashboard.freeConnection();
		} catch (Exception e) {
			throw new JspTagException("Error: JDBC error generating dashboardDidByPath");
		}
		return ID;
	}

}
