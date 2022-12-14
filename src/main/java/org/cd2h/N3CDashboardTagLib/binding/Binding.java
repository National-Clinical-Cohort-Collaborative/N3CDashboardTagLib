package org.cd2h.N3CDashboardTagLib.binding;

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
import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.Sequence;

@SuppressWarnings("serial")
public class Binding extends N3CDashboardTagLibTagSupport {

	static Binding currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(Binding.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int cid = 0;
	int did = 0;
	int seqnum = 0;

	private String var = null;

	private Binding cachedBinding = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			if (theCategory!= null)
				parentEntities.addElement(theCategory);
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (theDashboard!= null)
				parentEntities.addElement(theDashboard);

			if (theCategory == null) {
			} else {
				cid = theCategory.getCid();
			}
			if (theDashboard == null) {
			} else {
				did = theDashboard.getDid();
			}

			BindingIterator theBindingIterator = (BindingIterator)findAncestorWithClass(this, BindingIterator.class);

			if (theBindingIterator != null) {
				cid = theBindingIterator.getCid();
				did = theBindingIterator.getDid();
			}

			if (theBindingIterator == null && theCategory == null && theDashboard == null && cid == 0) {
				// no cid was provided - the default is to assume that it is a new Binding and to generate a new cid
				cid = Sequence.generateID();
				insertEntity();
			} else if (theBindingIterator == null && theCategory != null && theDashboard == null) {
				// an cid was provided as an attribute - we need to load a Binding from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select did,seqnum from n3c_dashboard.binding where cid = ?");
				stmt.setInt(1,cid);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (did == 0)
						did = rs.getInt(1);
					if (seqnum == 0)
						seqnum = rs.getInt(2);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			} else if (theBindingIterator == null && theCategory == null && theDashboard != null) {
				// an cid was provided as an attribute - we need to load a Binding from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select cid,seqnum from n3c_dashboard.binding where did = ?");
				stmt.setInt(1,did);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (cid == 0)
						cid = rs.getInt(1);
					if (seqnum == 0)
						seqnum = rs.getInt(2);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			} else {
				// an iterator or cid was provided as an attribute - we need to load a Binding from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select seqnum from n3c_dashboard.binding where cid = ? and did = ?");
				stmt.setInt(1,cid);
				stmt.setInt(2,did);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (seqnum == 0)
						seqnum = rs.getInt(1);
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
			Binding currentBinding = (Binding) pageContext.getAttribute("tag_binding");
			if(currentBinding != null){
				cachedBinding = currentBinding;
			}
			currentBinding = this;
			pageContext.setAttribute((var == null ? "tag_binding" : var), currentBinding);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedBinding != null){
				pageContext.setAttribute((var == null ? "tag_binding" : var), this.cachedBinding);
			}else{
				pageContext.removeAttribute((var == null ? "tag_binding" : var));
				this.cachedBinding = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.binding set seqnum = ? where cid = ?  and did = ? ");
				stmt.setInt( 1, seqnum );
				stmt.setInt(2,cid);
				stmt.setInt(3,did);
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
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.binding(cid,did,seqnum) values (?,?,?)");
		stmt.setInt(1,cid);
		stmt.setInt(2,did);
		stmt.setInt(3,seqnum);
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

	public int getDid () {
		return did;
	}

	public void setDid (int did) {
		this.did = did;
	}

	public int getActualDid () {
		return did;
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

	public static Integer didValue() throws JspException {
		try {
			return currentInstance.getDid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function didValue()");
		}
	}

	public static Integer seqnumValue() throws JspException {
		try {
			return currentInstance.getSeqnum();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function seqnumValue()");
		}
	}

	private void clearServiceState () {
		cid = 0;
		did = 0;
		seqnum = 0;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
