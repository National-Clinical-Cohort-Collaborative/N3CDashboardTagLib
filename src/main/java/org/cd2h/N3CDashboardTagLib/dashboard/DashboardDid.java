package org.cd2h.N3CDashboardTagLib.dashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardDid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardDid.class);

	public int doStartTag() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (!theDashboard.commitNeeded) {
				pageContext.getOut().print(theDashboard.getDid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for did tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for did tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getDid() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			return theDashboard.getDid();
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for did tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for did tag ");
			}
		}
	}

	public void setDid(int did) throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			theDashboard.setDid(did);
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for did tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for did tag ");
			}
		}
	}

}
