package org.cd2h.N3CDashboardTagLib.topic;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;

@SuppressWarnings("serial")
public class TopicSeqnum extends N3CDashboardTagLibTagSupport {

	private static final Logger log = LogManager.getLogger(TopicSeqnum.class);

	public int doStartTag() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			if (!theTopic.commitNeeded) {
				pageContext.getOut().print(theTopic.getSeqnum());
			}
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for seqnum tag ");
				return parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for seqnum tag ");
			}

		}
		return SKIP_BODY;
	}

	public int getSeqnum() throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			return theTopic.getSeqnum();
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for seqnum tag ");
				parent.doEndTag();
				return 0;
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for seqnum tag ");
			}
		}
	}

	public void setSeqnum(int seqnum) throws JspException {
		try {
			Topic theTopic = (Topic)findAncestorWithClass(this, Topic.class);
			theTopic.setSeqnum(seqnum);
		} catch (Exception e) {
			log.error("Can't find enclosing Topic for seqnum tag ", e);
			freeConnection();
			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: Can't find enclosing Topic for seqnum tag ");
				parent.doEndTag();
			}else{
				throw new JspTagException("Error: Can't find enclosing Topic for seqnum tag ");
			}
		}
	}

}
