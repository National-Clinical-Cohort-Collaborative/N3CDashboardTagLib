package org.cd2h.N3CDashboardTagLib.relatedDashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class RelatedDashboardRid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(RelatedDashboardRid.class);

	public int doStartTag() throws JspException {
		try {
			RelatedDashboard theRelatedDashboard = (RelatedDashboard)findAncestorWithClass(this, RelatedDashboard.class);
			if (!theRelatedDashboard.commitNeeded) {
				pageContext.getOut().print(theRelatedDashboard.getRid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing RelatedDashboard for rid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing RelatedDashboard for rid tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing RelatedDashboard for rid tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getRid() throws JspException {
		try {
			RelatedDashboard theRelatedDashboard = (RelatedDashboard)findAncestorWithClass(this, RelatedDashboard.class);
			return theRelatedDashboard.getRid();
		} catch (Exception e) {
			log.error("Can't find enclosing RelatedDashboard for rid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing RelatedDashboard for rid tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing RelatedDashboard for rid tag ");
			}
		}
	}

	public void setRid(int rid) throws JspException {
		try {
			RelatedDashboard theRelatedDashboard = (RelatedDashboard)findAncestorWithClass(this, RelatedDashboard.class);
			theRelatedDashboard.setRid(rid);
		} catch (Exception e) {
			log.error("Can't find enclosing RelatedDashboard for rid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing RelatedDashboard for rid tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing RelatedDashboard for rid tag ");
			}
		}
	}

}
