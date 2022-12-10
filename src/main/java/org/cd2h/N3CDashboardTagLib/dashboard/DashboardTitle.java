package org.cd2h.N3CDashboardTagLib.dashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardTitle extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardTitle.class);

	public int doStartTag() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (!theDashboard.commitNeeded) {
				pageContext.getOut().print(theDashboard.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for title tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for title tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for title tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			return theDashboard.getTitle();
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for title tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for title tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for title tag ");
			}
		}
	}

	public void setTitle(String title) throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			theDashboard.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for title tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for title tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for title tag ");
			}
		}
	}

}
