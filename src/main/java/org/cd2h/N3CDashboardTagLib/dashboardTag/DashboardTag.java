package org.cd2h.N3CDashboardTagLib.dashboardTag;

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
import org.cd2h.N3CDashboardTagLib.tagDefinition.TagDefinition;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.Sequence;

@SuppressWarnings("serial")
public class DashboardTag extends N3CDashboardTagLibTagSupport {

	static DashboardTag currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(DashboardTag.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int did = 0;
	int tid = 0;

	private String var = null;

	private DashboardTag cachedDashboardTag = null;

	public int doStartTag() throws JspException {
		currentInstance = this;
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (theDashboard!= null)
				parentEntities.addElement(theDashboard);
			TagDefinition theTagDefinition = (TagDefinition)findAncestorWithClass(this, TagDefinition.class);
			if (theTagDefinition!= null)
				parentEntities.addElement(theTagDefinition);

			if (theDashboard == null) {
			} else {
				did = theDashboard.getDid();
			}
			if (theTagDefinition == null) {
			} else {
				tid = theTagDefinition.getTid();
			}

			DashboardTagIterator theDashboardTagIterator = (DashboardTagIterator)findAncestorWithClass(this, DashboardTagIterator.class);

			if (theDashboardTagIterator != null) {
				did = theDashboardTagIterator.getDid();
				tid = theDashboardTagIterator.getTid();
			}

			if (theDashboardTagIterator == null && theDashboard == null && theTagDefinition == null && did == 0) {
				// no did was provided - the default is to assume that it is a new DashboardTag and to generate a new did
				did = Sequence.generateID();
				insertEntity();
			} else if (theDashboardTagIterator == null && theDashboard != null && theTagDefinition == null) {
				// an did was provided as an attribute - we need to load a DashboardTag from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select tid from n3c_dashboard.dashboard_tag where did = ?");
				stmt.setInt(1,did);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (tid == 0)
						tid = rs.getInt(1);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			} else if (theDashboardTagIterator == null && theDashboard == null && theTagDefinition != null) {
				// an did was provided as an attribute - we need to load a DashboardTag from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select did from n3c_dashboard.dashboard_tag where tid = ?");
				stmt.setInt(1,tid);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					if (did == 0)
						did = rs.getInt(1);
					found = true;
				}
				stmt.close();

				if (!found) {
					insertEntity();
				}
			} else {
				// an iterator or did was provided as an attribute - we need to load a DashboardTag from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select 1 from n3c_dashboard.dashboard_tag where did = ? and tid = ?");
				stmt.setInt(1,did);
				stmt.setInt(2,tid);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
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
			DashboardTag currentDashboardTag = (DashboardTag) pageContext.getAttribute("tag_dashboardTag");
			if(currentDashboardTag != null){
				cachedDashboardTag = currentDashboardTag;
			}
			currentDashboardTag = this;
			pageContext.setAttribute((var == null ? "tag_dashboardTag" : var), currentDashboardTag);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedDashboardTag != null){
				pageContext.setAttribute((var == null ? "tag_dashboardTag" : var), this.cachedDashboardTag);
			}else{
				pageContext.removeAttribute((var == null ? "tag_dashboardTag" : var));
				this.cachedDashboardTag = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.dashboard_tag set where did = ?  and tid = ? ");
				stmt.setInt(1,did);
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
		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.dashboard_tag(did,tid) values (?,?)");
		stmt.setInt(1,did);
		stmt.setInt(2,tid);
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

	private void clearServiceState () {
		did = 0;
		tid = 0;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
