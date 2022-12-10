package org.cd2h.N3CDashboardTagLib.topic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TopicId3 extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TopicId3.class);

	public int doStartTag() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			if (!theTopic.commitNeeded) {
				pageContext.getOut().print(theTopic.getId3());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for id3 tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for id3 tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for id3 tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getId3() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			return theTopic.getId3();
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for id3 tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for id3 tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for id3 tag ");
			}
		}
	}

	public void setId3(int id3) throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			theTopic.setId3(id3);
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for id3 tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for id3 tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for id3 tag ");
			}
		}
	}

}
