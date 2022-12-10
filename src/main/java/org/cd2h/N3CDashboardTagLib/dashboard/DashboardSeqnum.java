package org.cd2h.N3CDashboardTagLib.dashboard;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class DashboardSeqnum extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(DashboardSeqnum.class);

	public int doStartTag() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			if (!theDashboard.commitNeeded) {
				pageContext.getOut().print(theDashboard.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for seqnum tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for seqnum tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			return theDashboard.getSeqnum();
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for seqnum tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for seqnum tag ");
			}
		}
	}

	public void setSeqnum(int seqnum) throws JspException {
		try {
			Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
			theDashboard.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Dashboard for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Dashboard for seqnum tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Dashboard for seqnum tag ");
			}
		}
	}

}
