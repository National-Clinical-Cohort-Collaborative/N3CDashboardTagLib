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
import javax.imageio.ImageIO;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardThumbnail extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardThumbnail.class);
	int ID = 0;
	int id2 = 0;
	int seqnum = 0;
	String title = null;
	String description = null;
	String path = null;
	String thumbnailPath = null;
	byte[] thumbnail = null;
	String thumbnailName = null;

	public int doStartTag() throws JspException {
		try {
			PreparedStatement stmt = getConnection().prepareStatement("select seqnum,title,description,path,thumbnail_path,thumbnail,thumbnail_name from n3c_dashboard.dashboard where id = ? and id2 = ?");
			stmt.setInt(1,ID);
			stmt.setInt(2,id2);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (seqnum == 0)
					seqnum = rs.getInt(1);
				if (title == null)
					title = rs.getString(2);
				if (description == null)
					description = rs.getString(3);
				if (path == null)
					path = rs.getString(4);
				if (thumbnailPath == null)
					thumbnailPath = rs.getString(5);
				if (thumbnail == null)
					thumbnail = rs.getBytes(6);
				if (thumbnailName == null)
					thumbnailName = rs.getString(7);
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

	public int getID () {
		return ID;
	}

	public void setID (int ID) {
		this.ID = ID;
	}

	public int getActualID () {
		return ID;
	}

	public int getId2 () {
		return id2;
	}

	public void setId2 (int id2) {
		this.id2 = id2;
	}

	public int getActualId2 () {
		return id2;
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
		ID = 0;
		id2 = 0;
		seqnum = 0;
		title = null;
		description = null;
		path = null;
		thumbnailPath = null;
		thumbnail = null;
		thumbnailName = null;
	}

}
