package org.cd2h.N3CDashboardTagLib.dashboardTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardTagDid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardTagDid.class);

	public int doStartTag() throws JspException {
		try {
			DashboardTag theDashboardTag = (DashboardTag)findAncestorWithClass(this, DashboardTag.class);
			if (!theDashboardTag.commitNeeded) {
				pageContext.getOut().print(theDashboardTag.getDid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing DashboardTag for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing DashboardTag for did tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing DashboardTag for did tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getDid() throws JspException {
		try {
			DashboardTag theDashboardTag = (DashboardTag)findAncestorWithClass(this, DashboardTag.class);
			return theDashboardTag.getDid();
		} catch (Exception e) {
			log.error("Can't find enclosing DashboardTag for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing DashboardTag for did tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing DashboardTag for did tag ");
			}
		}
	}

	public void setDid(int did) throws JspException {
		try {
			DashboardTag theDashboardTag = (DashboardTag)findAncestorWithClass(this, DashboardTag.class);
			theDashboardTag.setDid(did);
		} catch (Exception e) {
			log.error("Can't find enclosing DashboardTag for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing DashboardTag for did tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing DashboardTag for did tag ");
			}
		}
	}

}
