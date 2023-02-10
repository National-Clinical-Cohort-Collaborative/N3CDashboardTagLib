package org.cd2h.N3CDashboardTagLib.tagDefinition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TagDefinitionTag extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TagDefinitionTag.class);

	public int doStartTag() throws JspException {
		try {
			TagDefinition theTagDefinition = (TagDefinition)findAncestorWithClass(this, TagDefinition.class);
			if (!theTagDefinition.commitNeeded) {
				pageContext.getOut().print(theTagDefinition.getTag());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing TagDefinition for tag tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TagDefinition for tag tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing TagDefinition for tag tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getTag() throws JspException {
		try {
			TagDefinition theTagDefinition = (TagDefinition)findAncestorWithClass(this, TagDefinition.class);
			return theTagDefinition.getTag();
		} catch (Exception e) {
			log.error("Can't find enclosing TagDefinition for tag tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TagDefinition for tag tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing TagDefinition for tag tag ");
			}
		}
	}

	public void setTag(String tag) throws JspException {
		try {
			TagDefinition theTagDefinition = (TagDefinition)findAncestorWithClass(this, TagDefinition.class);
			theTagDefinition.setTag(tag);
		} catch (Exception e) {
			log.error("Can't find enclosing TagDefinition for tag tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TagDefinition for tag tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing TagDefinition for tag tag ");
			}
		}
	}

}
