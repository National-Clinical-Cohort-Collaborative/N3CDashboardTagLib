package org.cd2h.N3CDashboardTagLib.category;

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
public class Category extends N3CDashboardTagLibTagSupport {

	static Category currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(Category.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int cid = 0;
	int seqnum = 0;
	String label = null;

	private String var = null;

	private Category cachedCategory = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {


			CategoryIterator theCategoryIterator = (CategoryIterator)findAncestorWithClass(this, CategoryIterator.class);

			if (theCategoryIterator != null) {
				cid = theCategoryIterator.getCid();
			}

			if (theCategoryIterator == null && cid == 0) {
				// no cid was provided - the default is to assume that it is a new Category and to generate a new cid
				cid = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or cid was provided as an attribute - we need to load a Category from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select seqnum,label from n3c_dashboard.category where cid = ?");
				stmt.setInt(1,cid);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (seqnum == 0)
						seqnum = rs.getInt(1);
					if (label == null)
						label = rs.getString(2);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			}
		} catch (SQLException e) {
			log.error("JDBC error retrieving cid " + cid, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving cid " + cid);
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error retrieving cid " + cid,e);
			}

		} finally {
			freeConnection();
		}

		if(pageContext != null){
			Category currentCategory = (Category) pageContext.getAttribute("tag_category");
			if(currentCategory != null){
				cachedCategory = currentCategory;
			}
			currentCategory = this;
			pageContext.setAttribute((var == null ? "tag_category" : var), currentCategory);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedCategory != null){
				pageContext.setAttribute((var == null ? "tag_category" : var), this.cachedCategory);
			}else{
				pageContext.removeAttribute((var == null ? "tag_category" : var));
				this.cachedCategory = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.category set seqnum = ?, label = ? where cid = ? ");
				stmt.setInt( 1, seqnum );
				stmt.setString( 2, label );
				stmt.setInt(3,cid);
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
		if (cid == 0) {
			cid = Sequence.generateID();
			log.debug("generating new Category " + cid);
		}

		if (label == null){
			label = "";
		}
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.category(cid,seqnum,label) values (?,?,?)");
		stmt.setInt(1,cid);
		stmt.setInt(2,seqnum);
		stmt.setString(3,label);
		stmt.executeUpdate();
		stmt.close();
		freeConnection();
	}

	public int getCid () {
		return cid;
	}

	public void setCid (int cid) {
		this.cid = cid;
	}

	public int getActualCid () {
		return cid;
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

	public String getLabel () {
		if (commitNeeded)
			return "";
		else
			return label;
	}

	public void setLabel (String label) {
		this.label = label;
		commitNeeded = true;
	}

	public String getActualLabel () {
		return label;
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

	public static Integer cidValue() throws JspException {
		try {
			return currentInstance.getCid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function cidValue()");
		}
	}

	public static Integer seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
		}
	}

	public static String labelValue() throws JspException {
		try {
			return currentInstance.getLabel();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function labelValue()");
		}
	}

	private void clearServiceState () {
		cid = 0;
		seqnum = 0;
		label = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
