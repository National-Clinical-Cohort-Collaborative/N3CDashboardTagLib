package org.cd2h.N3CDashboardTagLib.tagDefinition;

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
public class TagDefinition extends N3CDashboardTagLibTagSupport {

	static TagDefinition currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(TagDefinition.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int tid = 0;
	String tag = null;

	private String var = null;

	private TagDefinition cachedTagDefinition = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {


			TagDefinitionIterator theTagDefinitionIterator = (TagDefinitionIterator)findAncestorWithClass(this, TagDefinitionIterator.class);

			if (theTagDefinitionIterator != null) {
				tid = theTagDefinitionIterator.getTid();
			}

			if (theTagDefinitionIterator == null && tid == 0) {
				// no tid was provided - the default is to assume that it is a new TagDefinition and to generate a new tid
				tid = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or tid was provided as an attribute - we need to load a TagDefinition from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select tag from n3c_dashboard.tag_definition where tid = ?");
				stmt.setInt(1,tid);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (tag == null)
						tag = rs.getString(1);
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
			TagDefinition currentTagDefinition = (TagDefinition) pageContext.getAttribute("tag_tagDefinition");
			if(currentTagDefinition != null){
				cachedTagDefinition = currentTagDefinition;
			}
			currentTagDefinition = this;
			pageContext.setAttribute((var == null ? "tag_tagDefinition" : var), currentTagDefinition);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedTagDefinition != null){
				pageContext.setAttribute((var == null ? "tag_tagDefinition" : var), this.cachedTagDefinition);
			}else{
				pageContext.removeAttribute((var == null ? "tag_tagDefinition" : var));
				this.cachedTagDefinition = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.tag_definition set tag = ? where tid = ? ");
				stmt.setString( 1, tag );
				stmt.setInt(2,tid);
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
			log.debug("generating new TagDefinition " + tid);
		}

		if (tag == null){
			tag = "";
		}
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.tag_definition(tid,tag) values (?,?)");
		stmt.setInt(1,tid);
		stmt.setString(2,tag);
		stmt.executeUpdate();
		stmt.close();
		freeConnection();
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

	public String getTag () {
		if (commitNeeded)
			return "";
		else
			return tag;
	}

	public void setTag (String tag) {
		this.tag = tag;
		commitNeeded = true;
	}

	public String getActualTag () {
		return tag;
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

	public static Integer tidValue() throws JspException {
		try {
			return currentInstance.getTid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function tidValue()");
		}
	}

	public static String tagValue() throws JspException {
		try {
			return currentInstance.getTag();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function tagValue()");
		}
	}

	private void clearServiceState () {
		tid = 0;
		tag = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
