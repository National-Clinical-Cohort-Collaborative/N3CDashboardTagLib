package org.cd2h.N3CDashboardTagLib.relatedDashboard;

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
public class RelatedDashboard extends N3CDashboardTagLibTagSupport {

	static RelatedDashboard currentInstance = null;
	boolean commitNeeded = false;
	boolean newRecord = false;

	private static final Logger log = LogManager.getLogger(RelatedDashboard.class);

	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	int did = 0;
	int rid = 0;
	int seqnum = 0;

	private String var = null;

	private RelatedDashboard cachedRelatedDashboard = null;

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

			RelatedDashboardIterator theRelatedDashboardIterator = (RelatedDashboardIterator)findAncestorWithClass(this, RelatedDashboardIterator.class);

			if (theRelatedDashboardIterator != null) {
				did = theRelatedDashboardIterator.getDid();
				rid = theRelatedDashboardIterator.getRid();
			}

			if (theRelatedDashboardIterator == null && theDashboard == null && rid == 0) {
				// no rid was provided - the default is to assume that it is a new RelatedDashboard and to generate a new rid
				rid = Sequence.generateID();
				insertEntity();
			} else {
				// an iterator or rid was provided as an attribute - we need to load a RelatedDashboard from the database
				boolean found = false;
				PreparedStatement stmt = getConnection().prepareStatement("select seqnum from n3c_dashboard.related_dashboard where did = ? and rid = ?");
				stmt.setInt(1,did);
				stmt.setInt(2,rid);
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
			log.error("JDBC error retrieving rid " + rid, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving rid " + rid);
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error retrieving rid " + rid,e);
			}

		} finally {
			freeConnection();
		}

		if(pageContext != null){
			RelatedDashboard currentRelatedDashboard = (RelatedDashboard) pageContext.getAttribute("tag_relatedDashboard");
			if(currentRelatedDashboard != null){
				cachedRelatedDashboard = currentRelatedDashboard;
			}
			currentRelatedDashboard = this;
			pageContext.setAttribute((var == null ? "tag_relatedDashboard" : var), currentRelatedDashboard);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException {
		currentInstance = null;

		if(pageContext != null){
			if(this.cachedRelatedDashboard != null){
				pageContext.setAttribute((var == null ? "tag_relatedDashboard" : var), this.cachedRelatedDashboard);
			}else{
				pageContext.removeAttribute((var == null ? "tag_relatedDashboard" : var));
				this.cachedRelatedDashboard = null;
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
				PreparedStatement stmt = getConnection().prepareStatement("update n3c_dashboard.related_dashboard set seqnum = ? where did = ?  and rid = ? ");
				stmt.setInt( 1, seqnum );
				stmt.setInt(2,did);
				stmt.setInt(3,rid);
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
		if (rid == 0) {
			rid = Sequence.generateID();
			log.debug("generating new RelatedDashboard " + rid);
		}

		PreparedStatement stmt = getConnection().prepareStatement("insert into n3c_dashboard.related_dashboard(did,rid,seqnum) values (?,?,?)");
		stmt.setInt(1,did);
		stmt.setInt(2,rid);
		stmt.setInt(3,seqnum);
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

	public int getRid () {
		return rid;
	}

	public void setRid (int rid) {
		this.rid = rid;
	}

	public int getActualRid () {
		return rid;
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

	public static Integer didValue() throws JspException {
		try {
			return currentInstance.getDid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function didValue()");
		}
	}

	public static Integer ridValue() throws JspException {
		try {
			return currentInstance.getRid();
		} catch (Exception e) {
			 throw new JspTagException("Error in tag function ridValue()");
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
		did = 0;
		rid = 0;
		seqnum = 0;
		newRecord = false;
		commitNeeded = false;
		parentEntities = new Vector<N3CDashboardTagLibTagSupport>();
		this.var = null;

	}

}
