package org.cd2h.N3CDashboardTagLib.typeDefinition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TypeDefinitionTid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TypeDefinitionTid.class);

	public int doStartTag() throws JspException {
		try {
			TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
			if (!theTypeDefinition.commitNeeded) {
				pageContext.getOut().print(theTypeDefinition.getTid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing TypeDefinition for tid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TypeDefinition for tid tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing TypeDefinition for tid tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getTid() throws JspException {
		try {
			TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
			return theTypeDefinition.getTid();
		} catch (Exception e) {
			log.error("Can't find enclosing TypeDefinition for tid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TypeDefinition for tid tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing TypeDefinition for tid tag ");
			}
		}
	}

	public void setTid(int tid) throws JspException {
		try {
			TypeDefinition theTypeDefinition = (TypeDefinition)findAncestorWithClass(this, TypeDefinition.class);
			theTypeDefinition.setTid(tid);
		} catch (Exception e) {
			log.error("Can't find enclosing TypeDefinition for tid tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing TypeDefinition for tid tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing TypeDefinition for tid tag ");
			}
		}
	}

}
