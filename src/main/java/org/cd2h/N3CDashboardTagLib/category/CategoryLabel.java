package org.cd2h.N3CDashboardTagLib.category;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class CategoryLabel extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(CategoryLabel.class);

	public int doStartTag() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			if (!theCategory.commitNeeded) {
				pageContext.getOut().print(theCategory.getLabel());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Category for label tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for label tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for label tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getLabel() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			return theCategory.getLabel();
		} catch (Exception e) {
			log.error("Can't find enclosing Category for label tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for label tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for label tag ");
			}
		}
	}

	public void setLabel(String label) throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			theCategory.setLabel(label);
		} catch (Exception e) {
			log.error("Can't find enclosing Category for label tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for label tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for label tag ");
			}
		}
	}

}
