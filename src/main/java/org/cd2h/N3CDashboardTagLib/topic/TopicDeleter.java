package org.cd2h.N3CDashboardTagLib.topic;


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

@SuppressWarnings("serial")
public class TopicDeleter extends N3CDashboardTagLibBodyTagSupport {
    int ID = 0;
    int id2 = 0;
    int id3 = 0;
    int seqnum = 0;
    String title = null;
    String path = null;
	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	private static final Logger log = LogManager.getLogger(TopicDeleter.class);


    ResultSet rs = null;
    String var = null;
    int rsCount = 0;

    public int doStartTag() throws JspException {
		Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
		if (theDashboard!= null)
			parentEntities.addElement(theDashboard);

		if (theDashboard == null) {
		} else {
			ID = theDashboard.getID();
			id2 = theDashboard.getId2();
		}


        PreparedStatement stat;
        try {
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("DELETE from n3c_dashboard.topic where 1=1"
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (id2 == 0 ? "" : " and id2 = ? ")
                                                        + (id3 == 0 ? "" : " and id3 = ? ")
                                                        + (ID == 0 ? "" : " and id = ? ")
                                                        + (id2 == 0 ? "" : " and id2 = ? "));
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            if (id2 != 0) stat.setInt(webapp_keySeq++, id2);
            if (id3 != 0) stat.setInt(webapp_keySeq++, id3);
			if (ID != 0) stat.setInt(webapp_keySeq++, ID);
			if (id2 != 0) stat.setInt(webapp_keySeq++, id2);
            stat.execute();

			webapp_keySeq = 1;
        } catch (SQLException e) {
            log.error("JDBC error generating Topic deleter", e);

			clearServiceState();
			freeConnection();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: JDBC error generating Topic deleter");
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error generating Topic deleter",e);
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
        ID = 0;
        id2 = 0;
        id3 = 0;
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
}
