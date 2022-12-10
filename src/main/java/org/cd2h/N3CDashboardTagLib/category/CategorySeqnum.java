package org.cd2h.N3CDashboardTagLib.category;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class CategorySeqnum extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(CategorySeqnum.class);

	public int doStartTag() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			if (!theCategory.commitNeeded) {
				pageContext.getOut().print(theCategory.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Category for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for seqnum tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for seqnum tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			return theCategory.getSeqnum();
		} catch (Exception e) {
			log.error("Can't find enclosing Category for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for seqnum tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for seqnum tag ");
			}
		}
	}

	public void setSeqnum(int seqnum) throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			theCategory.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Category for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for seqnum tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for seqnum tag ");
			}
		}
	}

}
