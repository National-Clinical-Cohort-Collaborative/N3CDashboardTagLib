package org.cd2h.N3CDashboardTagLib.category;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class CategoryCid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(CategoryCid.class);

	public int doStartTag() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			if (!theCategory.commitNeeded) {
				pageContext.getOut().print(theCategory.getCid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Category for cid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for cid tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for cid tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getCid() throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			return theCategory.getCid();
		} catch (Exception e) {
			log.error("Can't find enclosing Category for cid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for cid tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for cid tag ");
			}
		}
	}

	public void setCid(int cid) throws JspException {
		try {
			Category theCategory = (Category)findAncestorWithClass(this, Category.class);
			theCategory.setCid(cid);
		} catch (Exception e) {
			log.error("Can't find enclosing Category for cid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Category for cid tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Category for cid tag ");
			}
		}
	}

}
