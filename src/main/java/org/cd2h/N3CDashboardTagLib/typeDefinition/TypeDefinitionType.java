package org.cd2h.N3CDashboardTagLib.typeDefinition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TypeDefinitionType extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TypeDefinitionType.class);

	public int doStartTag() throws JspException {
		try {
			TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
			if (!theTypeDefinition.commitNeeded) {
				pageContext.getOut().print(theTypeDefinition.getType());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing TypeDefinition for type tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TypeDefinition for type tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing TypeDefinition for type tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getType() throws JspException {
		try {
			TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
			return theTypeDefinition.getType();
		} catch (Exception e) {
			log.error("Can't find enclosing TypeDefinition for type tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TypeDefinition for type tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing TypeDefinition for type tag ");
			}
		}
	}

	public void setType(String type) throws JspException {
		try {
			TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
			theTypeDefinition.setType(type);
		} catch (Exception e) {
			log.error("Can't find enclosing TypeDefinition for type tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TypeDefinition for type tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing TypeDefinition for type tag ");
			}
		}
	}

}
