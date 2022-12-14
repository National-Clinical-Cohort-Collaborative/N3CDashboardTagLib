package org.cd2h.N3CDashboardTagLib.binding;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class BindingDid extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(BindingDid.class);

	public int doStartTag() throws JspException {
		try {
			Binding theBinding = (Binding)findAncestorWithClass(this, Binding.class);
			if (!theBinding.commitNeeded) {
				pageContext.getOut().print(theBinding.getDid());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Binding for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Binding for did tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Binding for did tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getDid() throws JspException {
		try {
			Binding theBinding = (Binding)findAncestorWithClass(this, Binding.class);
			return theBinding.getDid();
		} catch (Exception e) {
			log.error("Can't find enclosing Binding for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Binding for did tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Binding for did tag ");
			}
		}
	}

	public void setDid(int did) throws JspException {
		try {
			Binding theBinding = (Binding)findAncestorWithClass(this, Binding.class);
			theBinding.setDid(did);
		} catch (Exception e) {
			log.error("Can't find enclosing Binding for did tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Binding for did tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Binding for did tag ");
			}
		}
	}

}
