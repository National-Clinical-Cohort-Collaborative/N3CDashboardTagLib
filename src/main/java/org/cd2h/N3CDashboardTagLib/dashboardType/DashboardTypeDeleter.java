package org.cd2h.N3CDashboardTagLib.dashboardType;


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
import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;
import org.cd2h.N3CDashboardTagLib.typeDefinition.TypeDefinition;

@SuppressWarnings("serial")
public class DashboardTypeDeleter extends N3CDashboardTagLibBodyTagSupport {
    int did = 0;
    int tid = 0;
	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	private static final Logger log = LogManager.getLogger(DashboardTypeDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
		if (theDashboard!= null)
			parentEntities.addElement(theDashboard);
		TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
		if (theTypeDefinition!= null)
			parentEntities.addElement(theTypeDefinition);

		if (theDashboard == null) {
		} else {
			did = theDashboard.getDid();
		}
		if (theTypeDefinition == null) {
		} else {
			tid = theTypeDefinition.getTid();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from n3c_dashboard.dashboard_type where 1=1"
                                                        + (did == 0 ? "" : " and did = ? ")
                                                        + (tid == 0 ? "" : " and tid = ? ")
                                                        + (did == 0 ? "" : " and did = ? ")
                                                        + (tid == 0 ? "" : " and tid = ? "));
            if (did != 0) stat.setInt(webapp_keySeq++, did);
            if (tid != 0) stat.setInt(webapp_keySeq++, tid);
			if (did != 0) stat.setInt(webapp_keySeq++, did);
			if (tid != 0) stat.setInt(webapp_keySeq++, tid);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating DashboardType deleter", e);

			clearServiceState();
			freeConnection();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: JDBC error generating DashboardType deleter");
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error generating DashboardType deleter",e);
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
        did = 0;
        tid = 0;
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
}
