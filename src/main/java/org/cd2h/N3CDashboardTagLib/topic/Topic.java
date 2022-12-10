package org.cd2h.N3CDashboardTagLib.topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.Sequence;

@SuppressWarnings("serial")
public class Topic extends N3CDashboardTagLibTagSupport {

	static Topic currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(Topic.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int ID = 0;
	int id2 = 0;
	int id3 = 0;
	int seqnum = 0;
	String title = null;
	String path = null;

	private String var = null;

	private Topic cachedTopic = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (theDashboard!= null)
				parentEntities.addElement(theDashboard);

			if (theDashboard == null) {
			} else {
				ID = theDashboard.getID();
				id2 = theDashboard.getId2();
			}

			TopicIterator theTopicIterator = (TopicIterator)findAncestorWithClass(this, TopicIterator.class);

			if (theTopicIterator != null) {
				ID = theTopicIterator.getID();
				id2 = theTopicIterator.getId2();
				id3 = theTopicIterator.getId3();
			}

			if (theTopicIterator == null && theDashboard == null && id3 == 0) {
				// no id3 was provided - the default is to assume that it is a new Topic and to generate a new id3
				id3 = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or id3 was provided as an attribute - we need to load a Topic from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select seqnum,title,path from n3c_dashboard.topic where id = ? and id2 = ? and id3 = ?");
				stmt.setInt(1,ID);
				stmt.setInt(2,id2);
				stmt.setInt(3,id3);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (seqnum == 0)
						seqnum = rs.getInt(1);
					if (title == null)
						title = rs.getString(2);
					if (path == null)
						path = rs.getString(3);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving id3 " + id3, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving id3 " + id3);
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error retrieving id3 " + id3,e);
			}

		} finally {
			freeConnection();
		}

		if(pageContext != null){
			Topic currentTopic = (Topic) pageContext.getAttribute("tag_topic");
			if(currentTopic != null){
				cachedTopic = currentTopic;
			}
			currentTopic = this;
			pageContext.setAttribute((var == null ? "tag_topic" : var), currentTopic);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedTopic != null){
				pageContext.setAttribute((var == null ? "tag_topic" : var), this.cachedTopic);
			}else{
				pageContext.removeAttribute((var == null ? "tag_topic" : var));
				this.cachedTopic = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.topic set seqnum = ?, title = ?, path = ? where id = ?  and id2 = ?  and id3 = ? ");
				stmt.setInt( 1, seqnum );
				stmt.setString( 2, title );
				stmt.setString( 3, path );
				stmt.setInt(4,ID);
				stmt.setInt(5,id2);
				stmt.setInt(6,id3);
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
		if (id3 == 0) {
			id3 = Sequence.generateID();
			log.debug("generating new Topic " + id3);
		}

		if (title == null){
			title = "";
		}
		if (path == null){
			path = "";
		}
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.topic(id,id2,id3,seqnum,title,path) values (?,?,?,?,?,?)");
		stmt.setInt(1,ID);
		stmt.setInt(2,id2);
		stmt.setInt(3,id3);
		stmt.setInt(4,seqnum);
		stmt.setString(5,title);
		stmt.setString(6,path);
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

	public int getId3 () {
		return id3;
	}

	public void setId3 (int id3) {
		this.id3 = id3;
	}

	public int getActualId3 () {
		return id3;
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

	public static Integer id3Value() throws JspException {
		try {
			return currentInstance.getId3();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function id3Value()");
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

	public static String pathValue() throws JspException {
		try {
			return currentInstance.getPath();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function pathValue()");
		}
	}

	private void clearServiceState () {
		ID = 0;
		id2 = 0;
		id3 = 0;
		seqnum = 0;
		title = null;
		path = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
