package org.cd2h.N3CDashboardTagLib.topic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TopicID extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TopicID.class);

	public int doStartTag() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			if (!theTopic.commitNeeded) {
				pageContext.getOut().print(theTopic.getID());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for ID tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for ID tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for ID tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getID() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			return theTopic.getID();
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for ID tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for ID tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for ID tag ");
			}
		}
	}

	public void setID(int ID) throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			theTopic.setID(ID);
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for ID tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for ID tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for ID tag ");
			}
		}
	}

}
