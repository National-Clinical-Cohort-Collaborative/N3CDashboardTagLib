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

import org.cd2h.N3CDashboardTagLib.category.Category;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.Sequence;

@SuppressWarnings("serial")
public class Dashboard extends N3CDashboardTagLibTagSupport {

	static Dashboard currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(Dashboard.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int ID = 0;
	int id2 = 0;
	int seqnum = 0;
	String title = null;
	String description = null;
	String path = null;
	String thumbnailPath = null;

	private String var = null;

	private Dashboard cachedDashboard = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			if (theCategory!= null)
				parentEntities.addElement(theCategory);

			if (theCategory == null) {
			} else {
				ID = theCategory.getID();
			}

			DashboardIterator theDashboardIterator = (DashboardIterator)findAncestorWithClass(this, DashboardIterator.class);

			if (theDashboardIterator != null) {
				ID = theDashboardIterator.getID();
				id2 = theDashboardIterator.getId2();
			}

			if (theDashboardIterator == null && theCategory == null && id2 == 0) {
				// no id2 was provided - the default is to assume that it is a new Dashboard and to generate a new id2
				id2 = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or id2 was provided as an attribute - we need to load a Dashboard from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select seqnum,title,description,path,thumbnail_path from n3c_dashboard.dashboard where id = ? and id2 = ?");
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
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving id2 " + id2, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving id2 " + id2);
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error retrieving id2 " + id2,e);
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.dashboard set seqnum = ?, title = ?, description = ?, path = ?, thumbnail_path = ? where id = ?  and id2 = ? ");
				stmt.setInt( 1, seqnum );
				stmt.setString( 2, title );
				stmt.setString( 3, description );
				stmt.setString( 4, path );
				stmt.setString( 5, thumbnailPath );
				stmt.setInt(6,ID);
				stmt.setInt(7,id2);
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
		if (id2 == 0) {
			id2 = Sequence.generateID();
			log.debug("generating new Dashboard " + id2);
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
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.dashboard(id,id2,seqnum,title,description,path,thumbnail_path) values (?,?,?,?,?,?,?)");
		stmt.setInt(1,ID);
		stmt.setInt(2,id2);
		stmt.setInt(3,seqnum);
		stmt.setString(4,title);
		stmt.setString(5,description);
		stmt.setString(6,path);
		stmt.setString(7,thumbnailPath);
		stmt.executeUpdate();
		stmt.close();
		freeConnection();
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

	public int getSeqnum () {
		return seqnum;
	}

	public void setSeqnum (int seqnum) {
		this.seqnum = seqnum;
		commitNeeded = true;
	}

	public int getActualSeqnum () {
		return seqnum;
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

	public String getVar () {
		return var;
	}

	public void setVar (String var) {
		this.var = var;
	}

	public String getActualVar () {
		return var;
	}

	public static Integer IDValue() throws JspException {
		try {
			return currentInstance.getID();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function IDValue()");
		}
	}

	public static Integer id2Value() throws JspException {
		try {
			return currentInstance.getId2();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function id2Value()");
		}
	}

	public static Integer seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
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

	private void clearServiceState () {
		ID = 0;
		id2 = 0;
		seqnum = 0;
		title = null;
		description = null;
		path = null;
		thumbnailPath = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
