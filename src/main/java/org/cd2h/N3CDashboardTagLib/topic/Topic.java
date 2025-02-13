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

	int did = 0;
	int tid = 0;
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
				did = theDashboard.getDid();
			}

			TopicIterator theTopicIterator = (TopicIterator)findAncestorWithClass(this, TopicIterator.class);

			if (theTopicIterator != null) {
				tid = theTopicIterator.getTid();
				did = theTopicIterator.getDid();
			}

			if (theTopicIterator == null && theDashboard == null && tid == 0) {
				// no tid was provided - the default is to assume that it is a new Topic and to generate a new tid
				tid = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or tid was provided as an attribute - we need to load a Topic from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select seqnum,title,path from n3c_dashboard.topic where did = ? and tid = ?");
				stmt.setInt(1,did);
				stmt.setInt(2,tid);
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
			log.error("JDBC error retrieving tid " + tid, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving tid " + tid);
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error retrieving tid " + tid,e);
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.topic set seqnum = ?, title = ?, path = ? where did = ?  and tid = ? ");
				stmt.setInt( 1, seqnum );
				stmt.setString( 2, title );
				stmt.setString( 3, path );
				stmt.setInt(4,did);
				stmt.setInt(5,tid);
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
		if (tid == 0) {
			tid = Sequence.generateID();
			log.debug("generating new Topic " + tid);
		}

		if (title == null){
			title = "";
		}
		if (path == null){
			path = "";
		}
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.topic(did,tid,seqnum,title,path) values (?,?,?,?,?)");
		stmt.setInt(1,did);
		stmt.setInt(2,tid);
		stmt.setInt(3,seqnum);
		stmt.setString(4,title);
		stmt.setString(5,path);
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

	public int getTid () {
		return tid;
	}

	public void setTid (int tid) {
		this.tid = tid;
	}

	public int getActualTid () {
		return tid;
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

	public static Integer didValue() throws JspException {
		try {
			return currentInstance.getDid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function didValue()");
		}
	}

	public static Integer tidValue() throws JspException {
		try {
			return currentInstance.getTid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function tidValue()");
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
		did = 0;
		tid = 0;
		seqnum = 0;
		title = null;
		path = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
