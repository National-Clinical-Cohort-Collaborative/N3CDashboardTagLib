package org.cd2h.N3CDashboardTagLib.dashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardThumbnailPath extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardThumbnailPath.class);

	public int doStartTag() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (!theDashboard.commitNeeded) {
				pageContext.getOut().print(theDashboard.getThumbnailPath());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for thumbnailPath tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for thumbnailPath tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for thumbnailPath tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getThumbnailPath() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			return theDashboard.getThumbnailPath();
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for thumbnailPath tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for thumbnailPath tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for thumbnailPath tag ");
			}
		}
	}

	public void setThumbnailPath(String thumbnailPath) throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			theDashboard.setThumbnailPath(thumbnailPath);
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for thumbnailPath tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for thumbnailPath tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for thumbnailPath tag ");
			}
		}
	}

}
