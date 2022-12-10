package org.cd2h.N3CDashboardTagLib.category;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class CategoryID extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(CategoryID.class);

	public int doStartTag() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			if (!theCategory.commitNeeded) {
				pageContext.getOut().print(theCategory.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Category for ID tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for ID tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for ID tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getID() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			return theCategory.getID();
		} catch (Exception e) {
			log.error("Can't find enclosing Category for ID tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for ID tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for ID tag ");
			}
		}
	}

	public void setID(int ID) throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			theCategory.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Category for ID tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for ID tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for ID tag ");
			}
		}
	}

}
