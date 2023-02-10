package org.cd2h.N3CDashboardTagLib.typeDefinition;

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
public class TypeDefinition extends N3CDashboardTagLibTagSupport {

	static TypeDefinition currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(TypeDefinition.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int tid = 0;
	String type = null;

	private String var = null;

	private TypeDefinition cachedTypeDefinition = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {


			TypeDefinitionIterator theTypeDefinitionIterator = (TypeDefinitionIterator)findAncestorWithClass(this, TypeDefinitionIterator.class);

			if (theTypeDefinitionIterator != null) {
				tid = theTypeDefinitionIterator.getTid();
			}

			if (theTypeDefinitionIterator == null && tid == 0) {
				// no tid was provided - the default is to assume that it is a new TypeDefinition and to generate a new tid
				tid = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or tid was provided as an attribute - we need to load a TypeDefinition from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select type from n3c_dashboard.type_definition where tid = ?");
				stmt.setInt(1,tid);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (type == null)
						type = rs.getString(1);
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
			TypeDefinition currentTypeDefinition = (TypeDefinition) pageContext.getAttribute("tag_typeDefinition");
			if(currentTypeDefinition != null){
				cachedTypeDefinition = currentTypeDefinition;
			}
			currentTypeDefinition = this;
			pageContext.setAttribute((var == null ? "tag_typeDefinition" : var), currentTypeDefinition);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedTypeDefinition != null){
				pageContext.setAttribute((var == null ? "tag_typeDefinition" : var), this.cachedTypeDefinition);
			}else{
				pageContext.removeAttribute((var == null ? "tag_typeDefinition" : var));
				this.cachedTypeDefinition = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.type_definition set type = ? where tid = ? ");
				stmt.setString( 1, type );
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
			log.debug("generating new TypeDefinition " + tid);
		}

		if (type == null){
			type = "";
		}
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.type_definition(tid,type) values (?,?)");
		stmt.setInt(1,tid);
		stmt.setString(2,type);
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

	public String getType () {
		if (commitNeeded)
			return "";
		else
			return type;
	}

	public void setType (String type) {
		this.type = type;
		commitNeeded = true;
	}

	public String getActualType () {
		return type;
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

	public static String typeValue() throws JspException {
		try {
			return currentInstance.getType();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function typeValue()");
		}
	}

	private void clearServiceState () {
		tid = 0;
		type = null;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
