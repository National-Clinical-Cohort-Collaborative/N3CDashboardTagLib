package org.cd2h.N3CDashboardTagLib.dashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardThumbnail extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardThumbnail.class);
	int did = 0;
	String title = null;
	String description = null;
	String path = null;
	String thumbnailPath = null;
	byte[] thumbnail = null;
	String thumbnailName = null;
	String blurb = null;
	String limitations = null;
	String jsp = null;
	boolean active = false;

	public int doStartTag() throws JspException {
		try {
			PreparedStatement stmt = getConnection().prepareStatement("select title,description,path,thumbnail_path,thumbnail,thumbnail_name,blurb,limitations,jsp,active from n3c_dashboard.dashboard where did = ?");
			stmt.setInt(1,did);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (title == null)
					title = rs.getString(1);
				if (description == null)
					description = rs.getString(2);
				if (path == null)
					path = rs.getString(3);
				if (thumbnailPath == null)
					thumbnailPath = rs.getString(4);
				if (thumbnail == null)
					thumbnail = rs.getBytes(5);
				if (thumbnailName == null)
					thumbnailName = rs.getString(6);
				if (blurb == null)
					blurb = rs.getString(7);
				if (limitations == null)
					limitations = rs.getString(8);
				if (jsp == null)
					jsp = rs.getString(9);
				if (active == false)
					active = rs.getBoolean(10);
			}
			stmt.close();

			HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-disposition", "attachment;filename=\"" + thumbnailName + "\"");
			if (thumbnailName.toLowerCase().endsWith(".pdf"))
			        response.setContentType("application/pdf");
			else if (thumbnailName.toLowerCase().endsWith(".doc"))
			    response.setContentType("application/msword");
			else if (thumbnailName.toLowerCase().endsWith(".docx"))
			    response.setContentType("application/msword");
			else if (thumbnailName.toLowerCase().endsWith(".ppt"))
			    response.setContentType("application/mspowerpoint");
			else if (thumbnailName.toLowerCase().endsWith(".pptx"))
			    response.setContentType("application/mspowerpoint");
			response.setContentLength(thumbnail.length);
			
			// write ByteArrayOutputStream to the response OutputStream
			copy(new ByteArrayInputStream(thumbnail), response.getOutputStream());
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for thumbnail tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for thumbnail tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for thumbnail tag ");
			}

		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		clearServiceState();
		return super.doEndTag();
	}

	public int getDid () {
		return did;
	}

	public void setDid (int did) {
		this.did = did;
	}

	public int getActualDid () {
		return did;
	}

	public static void copy(InputStream inStream, OutputStream outStream) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(inStream);
			out = new BufferedOutputStream(outStream);
			while (true) {
				int data = in.read();
				if (data == -1) {
					break;
				}
	 			out.write(data);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	private void clearServiceState () {
		did = 0;
		title = null;
		description = null;
		path = null;
		thumbnailPath = null;
		thumbnail = null;
		thumbnailName = null;
		blurb = null;
		limitations = null;
		jsp = null;
		active = false;
	}

}
