package org.cd2h.N3CDashboardTagLib.dashboardType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardTypeDid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardTypeDid.class);

	public int doStartTag() throws JspException {
		try {
			DashboardType theDashboardType = (DashboardType)findAncestorWithClass(this, DashboardType.class);
			if (!theDashboardType.commitNeeded) {
				pageContext.getOut().print(theDashboardType.getDid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing DashboardType for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing DashboardType for did tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing DashboardType for did tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getDid() throws JspException {
		try {
			DashboardType theDashboardType = (DashboardType)findAncestorWithClass(this, DashboardType.class);
			return theDashboardType.getDid();
		} catch (Exception e) {
			log.error("Can't find enclosing DashboardType for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing DashboardType for did tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing DashboardType for did tag ");
			}
		}
	}

	public void setDid(int did) throws JspException {
		try {
			DashboardType theDashboardType = (DashboardType)findAncestorWithClass(this, DashboardType.class);
			theDashboardType.setDid(did);
		} catch (Exception e) {
			log.error("Can't find enclosing DashboardType for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing DashboardType for did tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing DashboardType for did tag ");
			}
		}
	}

}
