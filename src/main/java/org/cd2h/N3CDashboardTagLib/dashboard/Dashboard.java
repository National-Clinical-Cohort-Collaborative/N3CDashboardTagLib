package org.cd2h.N3CDashboardTagLib.dashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;


import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.Sequence;

@SuppressWarnings("serial")
public class Dashboard extends N3CDashboardTagLibTagSupport {

	static Dashboard currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(Dashboard.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

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

	private String var = null;

	private Dashboard cachedDashboard = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {


			DashboardIterator theDashboardIterator = (DashboardIterator)findAncestorWithClass(this, DashboardIterator.class);

			if (theDashboardIterator != null) {
				did = theDashboardIterator.getDid();
			}

			if (theDashboardIterator == null && did == 0) {
				// no did was provided - the default is to assume that it is a new Dashboard and to generate a new did
				did = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or did was provided as an attribute - we need to load a Dashboard from the database
				boolean found = false;
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
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving did " + did, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving did " + did);
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error retrieving did " + did,e);
			}

		} finally {
			freeConnection();
		}

		if(pageContext != null){
			Dashboard currentDashboard = (Dashboard) pageContext.getAttribute("tag_dashboard");
			if(currentDashboard != null){
				cachedDashboard = currentDashboard;
			}
			currentDashboard = this;
			pageContext.setAttribute((var == null ? "tag_dashboard" : var), currentDashboard);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedDashboard != null){
				pageContext.setAttribute((var == null ? "tag_dashboard" : var), this.cachedDashboard);
			}else{
				pageContext.removeAttribute((var == null ? "tag_dashboard" : var));
				this.cachedDashboard = null;
			}
		}

		try {
			Boolean error = null; // (Boolean) pageContext.getAttribute("tagError");
			if(pageContext != null){
				error = (Boolean) pageContext.getAttribute("tagError");
			}

			if(error != null && error){

				freeConnection();
				clearServiceState();

				Exception e = (Exception) pageContext.getAttribute("tagErrorException");
				String message = (String) pageContext.getAttribute("tagErrorMessage");

				Tag parent = getParent();
				if(parent != null){
					return parent.doEndTag();
				}else if(e != null && message != null){
					throw new JspException(message,e);
				}else if(parent == null){
					pageContext.removeAttribute("tagError");
					pageContext.removeAttribute("tagErrorException");
					pageContext.removeAttribute("tagErrorMessage");
				}
			}
			if (commitNeeded) {
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.dashboard set title = ?, description = ?, path = ?, thumbnail_path = ?, thumbnail = ?, thumbnail_name = ?, blurb = ?, limitations = ?, jsp = ?, active = ? where did = ? ");
				stmt.setString( 1, title );
				stmt.setString( 2, description );
				stmt.setString( 3, path );
				stmt.setString( 4, thumbnailPath );
				stmt.setBytes( 5, thumbnail );
				stmt.setString( 6, thumbnailName );
				stmt.setString( 7, blurb );
				stmt.setString( 8, limitations );
				stmt.setString( 9, jsp );
				stmt.setBoolean( 10, active );
				stmt.setInt(11,did);
				stmt.executeUpdate();
				stmt.close();
			}
		} catch (SQLException e) {
			log.error("Error: IOException while writing to the user", e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: IOException while writing to the user");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: IOException while writing to the user");
			}

		} finally {
			clearServiceState();
			freeConnection();
		}
		return super.doEndTag();
	}

	public void insertEntity() throws JspException, SQLException {
		if (did == 0) {
			did = Sequence.generateID();
			log.debug("generating new Dashboard " + did);
		}

		if (title == null){
			title = "";
		}
		if (description == null){
			description = "";
		}
		if (path == null){
			path = "";
		}
		if (thumbnailPath == null){
			thumbnailPath = "";
		}
		if (thumbnailName == null){
			thumbnailName = "";
		}
		if (blurb == null){
			blurb = "";
		}
		if (limitations == null){
			limitations = "";
		}
		if (jsp == null){
			jsp = "";
		}
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.dashboard(did,title,description,path,thumbnail_path,thumbnail,thumbnail_name,blurb,limitations,jsp,active) values (?,?,?,?,?,?,?,?,?,?,?)");
		stmt.setInt(1,did);
		stmt.setString(2,title);
		stmt.setString(3,description);
		stmt.setString(4,path);
		stmt.setString(5,thumbnailPath);
		stmt.setBytes(6,thumbnail);
		stmt.setString(7,thumbnailName);
		stmt.setString(8,blurb);
		stmt.setString(9,limitations);
		stmt.setString(10,jsp);
		stmt.setBoolean(11,active);
		stmt.executeUpdate();
		stmt.close();
		freeConnection();
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

	public String getTitle () {
		if (commitNeeded)
			return "";
		else
			return title;
	}

	public void setTitle (String title) {
		this.title = title;
		commitNeeded = true;
	}

	public String getActualTitle () {
		return title;
	}

	public String getDescription () {
		if (commitNeeded)
			return "";
		else
			return description;
	}

	public void setDescription (String description) {
		this.description = description;
		commitNeeded = true;
	}

	public String getActualDescription () {
		return description;
	}

	public String getPath () {
		if (commitNeeded)
			return "";
		else
			return path;
	}

	public void setPath (String path) {
		this.path = path;
		commitNeeded = true;
	}

	public String getActualPath () {
		return path;
	}

	public String getThumbnailPath () {
		if (commitNeeded)
			return "";
		else
			return thumbnailPath;
	}

	public void setThumbnailPath (String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
		commitNeeded = true;
	}

	public String getActualThumbnailPath () {
		return thumbnailPath;
	}

	public byte[] getThumbnail () {
		return thumbnail;
	}

	public void setThumbnail (byte[] thumbnail) {
		this.thumbnail = thumbnail;
		commitNeeded = true;
	}

	public byte[] getActualThumbnail () {
		return thumbnail;
	}

	public String getThumbnailName () {
		if (commitNeeded)
			return "";
		else
			return thumbnailName;
	}

	public void setThumbnailName (String thumbnailName) {
		this.thumbnailName = thumbnailName;
		commitNeeded = true;
	}

	public String getActualThumbnailName () {
		return thumbnailName;
	}

	public String getBlurb () {
		if (commitNeeded)
			return "";
		else
			return blurb;
	}

	public void setBlurb (String blurb) {
		this.blurb = blurb;
		commitNeeded = true;
	}

	public String getActualBlurb () {
		return blurb;
	}

	public String getLimitations () {
		if (commitNeeded)
			return "";
		else
			return limitations;
	}

	public void setLimitations (String limitations) {
		this.limitations = limitations;
		commitNeeded = true;
	}

	public String getActualLimitations () {
		return limitations;
	}

	public String getJsp () {
		if (commitNeeded)
			return "";
		else
			return jsp;
	}

	public void setJsp (String jsp) {
		this.jsp = jsp;
		commitNeeded = true;
	}

	public String getActualJsp () {
		return jsp;
	}

	public boolean getActive () {
		return active;
	}

	public void setActive (boolean active) {
		this.active = active;
		commitNeeded = true;
	}

	public boolean getActualActive () {
		return active;
	}

	public String getVar () {
		return var;
	}

	public void setVar (String var) {
		this.var = var;
	}

	public String getActualVar () {
		return var;
	}

	public static Integer didValue() throws JspException {
		try {
			return currentInstance.getDid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function didValue()");
		}
	}

	public static String titleValue() throws JspException {
		try {
			return currentInstance.getTitle();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function titleValue()");
		}
	}

	public static String descriptionValue() throws JspException {
		try {
			return currentInstance.getDescription();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function descriptionValue()");
		}
	}

	public static String pathValue() throws JspException {
		try {
			return currentInstance.getPath();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function pathValue()");
		}
	}

	public static String thumbnailPathValue() throws JspException {
		try {
			return currentInstance.getThumbnailPath();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function thumbnailPathValue()");
		}
	}

	public static byte[] thumbnailValue() throws JspException {
		try {
			return currentInstance.getThumbnail();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function thumbnailValue()");
		}
	}

	public static String thumbnailNameValue() throws JspException {
		try {
			return currentInstance.getThumbnailName();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function thumbnailNameValue()");
		}
	}

	public static String blurbValue() throws JspException {
		try {
			return currentInstance.getBlurb();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function blurbValue()");
		}
	}

	public static String limitationsValue() throws JspException {
		try {
			return currentInstance.getLimitations();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function limitationsValue()");
		}
	}

	public static String jspValue() throws JspException {
		try {
			return currentInstance.getJsp();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function jspValue()");
		}
	}

	public static Boolean activeValue() throws JspException {
		try {
			return currentInstance.getActive();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function activeValue()");
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
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
