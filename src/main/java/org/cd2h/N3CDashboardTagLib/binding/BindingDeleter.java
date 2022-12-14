package org.cd2h.N3CDashboardTagLib.binding;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibBodyTagSupport;
import org.cd2h.N3CDashboardTagLib.category.Category;
import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;

@SuppressWarnings("serial")
public class BindingDeleter extends N3CDashboardTagLibBodyTagSupport {
    int cid = 0;
    int did = 0;
    int seqnum = 0;
	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	private static final Logger log = LogManager.getLogger(BindingDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
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


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from n3c_dashboard.binding where 1=1"
                                                        + (cid == 0 ? "" : " and cid = ? ")
                                                        + (did == 0 ? "" : " and did = ? ")
                                                        + (cid == 0 ? "" : " and cid = ? ")
                                                        + (did == 0 ? "" : " and did = ? "));
            if (cid != 0) stat.setInt(webapp_keySeq++, cid);
            if (did != 0) stat.setInt(webapp_keySeq++, did);
			if (cid != 0) stat.setInt(webapp_keySeq++, cid);
			if (did != 0) stat.setInt(webapp_keySeq++, did);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating Binding deleter", e);

			clearServiceState();
			freeConnection();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: JDBC error generating Binding deleter");
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error generating Binding deleter",e);
			}

        } finally {
            freeConnection();
        }

        return SKIP_BODY;
    }

	public int doEndTag() throws JspException {

		clearServiceState();
		Boolean error = (Boolean) pageContext.getAttribute("tagError");
		if(error != null && error){

			freeConnection();

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
		return super.doEndTag();
	}

    private void clearServiceState() {
        cid = 0;
        did = 0;
        parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

        this.rs = null;
        this.var = null;
        this.rsCount = 0;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
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
}
