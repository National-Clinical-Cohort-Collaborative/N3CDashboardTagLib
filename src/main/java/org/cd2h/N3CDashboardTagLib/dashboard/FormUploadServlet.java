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

@WebServlet("/FormUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB
public class FormUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LogManager.getLogger(FormUploadServlet.class);
    protected DataSource theDataSource = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormUploadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID").toLowerCase().trim());
        int id2 = 0;
        if (request.getParameter("id2") != null)
        	id2 = Integer.parseInt(request.getParameter("id2").toLowerCase().trim());
        int seqnum = Integer.parseInt(request.getParameter("seqnum").toLowerCase().trim());
        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();
        String path = request.getParameter("path").trim();
        String thumbnail_path = request.getParameter("thumbnailPath").trim();
        
        Part thumbnail = null;
        String thumbnail_name = null;

        for (Part part : request.getParts()) {
        	
            Payload payload = getPayload(part);
            if (payload == null)
            	continue;
            log.info(payload.name + " file: " + payload.file);

            switch(payload.name) {
            case "thumbnail":
            	thumbnail = part;
                thumbnail_name = payload.file;
            	break;
            }
         }
        
        try {
			Connection conn = getConnection();
			PreparedStatement stmt = null;
			boolean recordExists = false;
			
			stmt = conn.prepareStatement("select count(*) from n3c_dashboard.dashboard where id = ? and id2 = ?");
			stmt.setInt(1, id);
			stmt.setInt(2, id2);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int count = rs.getInt(1);
				recordExists = count == 1;
			}
			stmt.close();
			
			if (recordExists) {
				log.info("image upload: " + thumbnail);
				int paramCount = 0;
				stmt = conn.prepareStatement("update n3c_dashboard.dashboard set seqnum=?,title=?,description=?,path=?,thumbnail_path=? where id=? and id2=?");
				stmt.setInt(++paramCount, seqnum);
				stmt.setString(++paramCount, title);
				stmt.setString(++paramCount, description);
				stmt.setString(++paramCount, path);
				stmt.setString(++paramCount, thumbnail_path);
				stmt.setInt(++paramCount, id);
				stmt.setInt(++paramCount, id2);
				stmt.execute();
				stmt.close();
				if (thumbnail != null) {
					stmt = conn.prepareStatement("update n3c_dashboard.dashboard set thumbnail=?,thumbnail_name=? where id = ? and id2 = ?");
					stmt.setBinaryStream(1, thumbnail.getInputStream(), thumbnail.getSize());
					stmt.setString(2, thumbnail_name);
					stmt.setInt(3, id);
					stmt.setInt(4, id2);
					stmt.execute();
					stmt.close();
				}
			} else {
				int paramCount = 0;
				stmt = conn.prepareStatement("insert into n3c_dashboard.dashboard(id,id2,seqnum,title,description,path,thumbnail_path,thumbnail,thumbnail_name) values(?,?,?,?,?,?,?,?,?)");
				stmt.setInt(++paramCount, id);
				stmt.setInt(++paramCount, Sequence.generateID());
				stmt.setInt(++paramCount, seqnum);
				stmt.setString(++paramCount, title);
				stmt.setString(++paramCount, description);
				stmt.setString(++paramCount, path);
				stmt.setString(++paramCount, thumbnail_path);
				stmt.setBinaryStream(++paramCount, thumbnail.getInputStream(), thumbnail.getSize());
				stmt.setString(++paramCount, thumbnail_name);
				stmt.execute();
				stmt.close();
			}
			
			conn.close();
		} catch (SQLException e) {
			log.error("Failed to make database connection: " + e);
		}
        
        request.setAttribute("message", "Files uploaded successfully!");
        getServletContext().getRequestDispatcher("/dashboard/list.jsp").forward(request, response);
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
