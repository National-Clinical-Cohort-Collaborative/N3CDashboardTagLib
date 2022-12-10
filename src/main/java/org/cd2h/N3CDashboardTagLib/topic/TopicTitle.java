package org.cd2h.N3CDashboardTagLib.topic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TopicTitle extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TopicTitle.class);

	public int doStartTag() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			if (!theTopic.commitNeeded) {
				pageContext.getOut().print(theTopic.getTitle());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for title tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for title tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for title tag ");
			}

		}
		return SKIP_BODY;
	}

	public String getTitle() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			return theTopic.getTitle();
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for title tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for title tag ");
				parent.doEndTag();
				return null;
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for title tag ");
			}
		}
	}

	public void setTitle(String title) throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			theTopic.setTitle(title);
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for title tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for title tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for title tag ");
			}
		}
	}

}
