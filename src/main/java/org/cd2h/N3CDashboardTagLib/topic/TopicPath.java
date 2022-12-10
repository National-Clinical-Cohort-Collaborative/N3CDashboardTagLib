package org.cd2h.N3CDashboardTagLib.topic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TopicPath extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TopicPath.class);

	public int doStartTag() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			if (!theTopic.commitNeeded) {
				pageContext.getOut().print(theTopic.getPath());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for path tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for path tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for path tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getPath() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			return theTopic.getPath();
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for path tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for path tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for path tag ");
			}
		}
	}

	public void setPath(String path) throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			theTopic.setPath(path);
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for path tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for path tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for path tag ");
			}
		}
	}

}
