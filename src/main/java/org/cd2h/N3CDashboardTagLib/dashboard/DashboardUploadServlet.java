package org.cd2h.N3CDashboardTagLib.dashboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cd2h.N3CDashboardTagLib.Sequence;

@WebServlet("/DashboardUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class DashboardUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(DashboardUploadServlet.class);
	protected DataSource theDataSource = null;

	public DashboardUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int did = 0;
		if (request.getParameter("did") != null)
			did = Integer.parseInt(request.getParameter("did").trim());
		String title = request.getParameter("title").trim();
		String description = request.getParameter("description").trim();
		String path = request.getParameter("path").trim();
		String thumbnailPath = request.getParameter("thumbnailPath").trim();
		String thumbnailName = request.getParameter("thumbnailName").trim();
		String blurb = request.getParameter("blurb").trim();
		String limitations = request.getParameter("limitations").trim();

		Part thumbnail = null;

		for (Part part : request.getParts()) {
			Payload payload = getPayload(part);
			if (payload == null)
				continue;
			log.debug(payload.name + " file: " + payload.file);

			switch(payload.name) {
			case "thumbnail":
				thumbnail = part;
				thumbnailName = payload.file;
				break;
			}
		}

		try {
			Connection conn = getConnection();
			PreparedStatement stmt = null;
			boolean recordExists = false;

			stmt = conn.prepareStatement("select count(*) from n3c_dashboard.dashboard where did = ?");
			stmt.setInt(1,did);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int count = rs.getInt(1);
				recordExists = count == 1;
			}
			stmt.close();

			if (recordExists) {
				log.debug("image upload: " + thumbnail);
				int paramCount = 0;
				stmt = conn.prepareStatement("update n3c_dashboard.dashboard set title = ?, description = ?, path = ?, thumbnail_path = ?, thumbnail_name = ?, blurb = ?, limitations = ? where did = ?");
				stmt.setString(++paramCount,title);
				stmt.setString(++paramCount,description);
				stmt.setString(++paramCount,path);
				stmt.setString(++paramCount,thumbnailPath);
				stmt.setString(++paramCount,thumbnailName);
				stmt.setString(++paramCount,blurb);
				stmt.setString(++paramCount,limitations);
				stmt.setInt(++paramCount,did);
				stmt.execute();
				stmt.close();
				if (thumbnail != null) {
					paramCount = 0;
					stmt = conn.prepareStatement("update n3c_dashboard.dashboard set thumbnail=?,thumbnail_name=? where did = ?");
					stmt.setBinaryStream(++paramCount, thumbnail.getInputStream(), thumbnail.getSize());
					stmt.setString(++paramCount, thumbnailName);
					stmt.setInt(++paramCount,did);
					stmt.execute();
					stmt.close();
				}
			} else {
				int paramCount = 0;
				stmt = conn.prepareStatement("insert into n3c_dashboard.dashboard(did,title,description,path,thumbnail_path,thumbnail,thumbnail_name,blurb,limitations) values(?,?,?,?,?,?,?,?,?)");
				stmt.setInt(++paramCount,Sequence.generateID());
				stmt.setString(++paramCount,title);
				stmt.setString(++paramCount,description);
				stmt.setString(++paramCount,path);
				stmt.setString(++paramCount,thumbnailPath);
				stmt.setBinaryStream(++paramCount, thumbnail.getInputStream(), thumbnail.getSize());
				stmt.setString(++paramCount,thumbnailName);
				stmt.setString(++paramCount,blurb);
				stmt.setString(++paramCount,limitations);
				stmt.execute();
				stmt.close();
			}

			conn.close();
		} catch (SQLException e) {
			log.error("Failed to make database connection: " + e);
		}

		response.sendRedirect(request.getContextPath() + "/dashboard/list.jsp");
	}

	private Payload getPayload(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		String name = null;
		String file = null;
		for (String token : tokens) {
			if (token.trim().startsWith("name")) {
				name =  token.substring(token.indexOf("=") + 2, token.length()-1);
			}
			if (token.trim().startsWith("filename")) {
				file =  token.substring(token.indexOf("=") + 2, token.length()-1);
			}
		}

		if (file == null || file.length() == 0)
			return null;

		return new Payload(name, file);
	}

	public DataSource getDataSource() {
		if (theDataSource == null) try {
			theDataSource = (DataSource)new InitialContext().lookup("java:/comp/env/jdbc/N3CDashboardTagLib");
		} catch (Exception e) {
			log.error("Error in database initialization", e);
		}

	return theDataSource;
	}

	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	class Payload {
		String name = null;
		String file = null;

		Payload(String name, String file) {
			this.name = name;
			this.file = file;
		}
	}


}
