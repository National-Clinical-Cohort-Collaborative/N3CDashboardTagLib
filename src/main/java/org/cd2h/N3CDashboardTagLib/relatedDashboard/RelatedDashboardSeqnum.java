package org.cd2h.N3CDashboardTagLib.relatedDashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class RelatedDashboardSeqnum extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(RelatedDashboardSeqnum.class);

	public int doStartTag() throws JspException {
		try {
			RelatedDashboard theRelatedDashboard = (RelatedDashboard)findAncestorWithClass(this, RelatedDashboard.class);
			if (!theRelatedDashboard.commitNeeded) {
				pageContext.getOut().print(theRelatedDashboard.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing RelatedDashboard for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing RelatedDashboard for seqnum tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing RelatedDashboard for seqnum tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspException {
		try {
			RelatedDashboard theRelatedDashboard = (RelatedDashboard)findAncestorWithClass(this, RelatedDashboard.class);
			return theRelatedDashboard.getSeqnum();
		} catch (Exception e) {
			log.error("Can't find enclosing RelatedDashboard for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing RelatedDashboard for seqnum tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing RelatedDashboard for seqnum tag ");
			}
		}
	}

	public void setSeqnum(int seqnum) throws JspException {
		try {
			RelatedDashboard theRelatedDashboard = (RelatedDashboard)findAncestorWithClass(this, RelatedDashboard.class);
			theRelatedDashboard.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing RelatedDashboard for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing RelatedDashboard for seqnum tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing RelatedDashboard for seqnum tag ");
			}
		}
	}

}
