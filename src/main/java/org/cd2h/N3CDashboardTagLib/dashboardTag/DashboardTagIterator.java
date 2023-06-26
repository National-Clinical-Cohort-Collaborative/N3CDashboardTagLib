package org.cd2h.N3CDashboardTagLib.dashboardTag;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibTagSupport;
import org.cd2h.N3CDashboardTagLib.N3CDashboardTagLibBodyTagSupport;
import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;
import org.cd2h.N3CDashboardTagLib.tagDefinition.TagDefinition;

@SuppressWarnings("serial")
public class DashboardTagIterator extends N3CDashboardTagLibBodyTagSupport {
    int did = 0;
    int tid = 0;
	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	private static final Logger log = LogManager.getLogger(DashboardTagIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String fromList = null;
    String joinCriteria = null;
    String filterCriteria = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

   boolean useDashboard = false;
   boolean useTagDefinition = false;

	public static String dashboardTagCountByDashboard(String did) throws JspTagException {
		int count = 0;
		DashboardTagIterator theIterator = new DashboardTagIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.dashboard_tag where 1=1"
						+ " and did = ?"
						);

			stat.setInt(1,Integer.parseInt(did));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating DashboardTag iterator", e);
			throw new JspTagException("Error: JDBC error generating DashboardTag iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean dashboardHasDashboardTag(String did) throws JspTagException {
		return ! dashboardTagCountByDashboard(did).equals("0");
	}

	public static String dashboardTagCountByTagDefinition(String tid) throws JspTagException {
		int count = 0;
		DashboardTagIterator theIterator = new DashboardTagIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.dashboard_tag where 1=1"
						+ " and tid = ?"
						);

			stat.setInt(1,Integer.parseInt(tid));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating DashboardTag iterator", e);
			throw new JspTagException("Error: JDBC error generating DashboardTag iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean tagDefinitionHasDashboardTag(String tid) throws JspTagException {
		return ! dashboardTagCountByTagDefinition(tid).equals("0");
	}

	public static Boolean dashboardTagExists (String did, String tid) throws JspTagException {
		int count = 0;
		DashboardTagIterator theIterator = new DashboardTagIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.dashboard_tag where 1=1"
						+ " and did = ?"
						+ " and tid = ?"
						);

			stat.setInt(1,Integer.parseInt(did));
			stat.setInt(2,Integer.parseInt(tid));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating DashboardTag iterator", e);
			throw new JspTagException("Error: JDBC error generating DashboardTag iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

	public static Boolean dashboardTagDefinitionExists (String did, String tid) throws JspTagException {
		int count = 0;
		DashboardTagIterator theIterator = new DashboardTagIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.dashboard_tag where 1=1"
						+ " and did = ?"
						+ " and tid = ?"
						);

			stat.setInt(1,Integer.parseInt(did));
			stat.setInt(2,Integer.parseInt(tid));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating DashboardTag iterator", e);
			throw new JspTagException("Error: JDBC error generating DashboardTag iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
		if (theDashboard!= null)
			parentEntities.addElement(theDashboard);
		TagDefinition theTagDefinition = (TagDefinition)findAncestorWithClass(this, TagDefinition.class);
		if (theTagDefinition!= null)
			parentEntities.addElement(theTagDefinition);

		if (theDashboard == null) {
		} else {
			did = theDashboard.getDid();
		}
		if (theTagDefinition == null) {
		} else {
			tid = theTagDefinition.getTid();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + generateFilterCriteria()
                                                        + (did == 0 ? "" : " and did = ?")
                                                        + (tid == 0 ? "" : " and tid = ?")
                                                        + generateLimitCriteria());
            if (did != 0) stat.setInt(webapp_keySeq++, did);
            if (tid != 0) stat.setInt(webapp_keySeq++, tid);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT n3c_dashboard.dashboard_tag.did, n3c_dashboard.dashboard_tag.tid from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + generateFilterCriteria()
                                                        + (did == 0 ? "" : " and did = ?")
                                                        + (tid == 0 ? "" : " and tid = ?")
                                                        + " order by " + generateSortCriteria()  +  generateLimitCriteria());
            if (did != 0) stat.setInt(webapp_keySeq++, did);
            if (tid != 0) stat.setInt(webapp_keySeq++, tid);
            rs = stat.executeQuery();

            if ( rs != null && rs.next() ) {
                did = rs.getInt(1);
                tid = rs.getInt(2);
                if (var != null)
                    pageContext.setAttribute(var, this);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating DashboardTag iterator: " + stat, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: JDBC error generating DashboardTag iterator: " + stat);
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error generating DashboardTag iterator: " + stat,e);
			}

        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("n3c_dashboard.dashboard_tag");
       if (useDashboard)
          theBuffer.append(", n3c_dashboard.dashboard");
       if (useTagDefinition)
          theBuffer.append(", n3c_dashboard.tag_definition");

      return theBuffer.toString();
    }

    private String generateJoinCriteria() {
       StringBuffer theBuffer = new StringBuffer();
       if (useDashboard)
          theBuffer.append(" and n3c_dashboard.dashboard.did = n3c_dashboard.dashboard_tag.did");
       if (useTagDefinition)
          theBuffer.append(" and n3c_dashboard.tag_definition.tid = n3c_dashboard.dashboard_tag.tid");

      return theBuffer.toString();
    }

    private String generateFilterCriteria() {
        if (filterCriteria != null) {
            return " and " + filterCriteria;
        } else {
            return "";
        }
    }

    private String generateSortCriteria() {
        if (sortCriteria != null) {
            return sortCriteria;
        } else {
            return "did,tid";
        }
    }

    private String generateLimitCriteria() {
        if (limitCriteria > 0) {
            return " limit " + limitCriteria;
        } else {
            return "";
        }
    }

    public int doAfterBody() throws JspException {
        try {
            if ( rs != null && rs.next() ) {
                did = rs.getInt(1);
                tid = rs.getInt(2);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across DashboardTag", e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error iterating across DashboardTag" + stat.toString());
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error iterating across DashboardTag",e);
			}

        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException, JspException {
        try {
			if( var != null )
				pageContext.removeAttribute(var);
			if( pageContext != null ){
				Boolean error = (Boolean) pageContext.getAttribute("tagError");
				if( error != null && error ){

					freeConnection();
					clearServiceState();

					Exception e = null; // (Exception) pageContext.getAttribute("tagErrorException");
					String message = null; // (String) pageContext.getAttribute("tagErrorMessage");

					if(pageContext != null){
						e = (Exception) pageContext.getAttribute("tagErrorException");
						message = (String) pageContext.getAttribute("tagErrorMessage");

					}
					Tag parent = getParent();
					if(parent != null){
						return parent.doEndTag();
					}else if(e != null && message != null){
						throw new JspException(message,e);
					}else if(parent == null && pageContext != null){
						pageContext.removeAttribute("tagError");
						pageContext.removeAttribute("tagErrorException");
						pageContext.removeAttribute("tagErrorMessage");
					}
				}
			}

            if( rs != null ){
                rs.close();
            }

            if( stat != null ){
                stat.close();
            }

        } catch ( SQLException e ) {
            log.error("JDBC error ending DashboardTag iterator",e);
			freeConnection();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving did " + did);
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error ending DashboardTag iterator",e);
			}

        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        did = 0;
        tid = 0;
        parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

        this.rs = null;
        this.stat = null;
        this.sortCriteria = null;
        this.var = null;
        this.rsCount = 0;
    }

    public String getFromList() {
        return fromList;
    }

    public void setFromList(String fromList) {
        this.fromList = fromList;
    }

    public String getJoinCriteria() {
        return joinCriteria;
    }

    public void setJoinCriteria(String joinCriteria) {
        this.joinCriteria = joinCriteria;
    }

    public String getFilterCriteria() {
        return filterCriteria;
    }

    public void setFilterCriteria(String filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    public int getLimitCriteria() {
        return limitCriteria;
    }

    public void setLimitCriteria(int limitCriteria) {
        this.limitCriteria = limitCriteria;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Boolean isFirst() throws SQLException {
        return rs.isFirst();
    }

    public Boolean isLast() throws SQLException {
        return rs.isLast();
    }


   public boolean getUseDashboard() {
        return useDashboard;
    }

    public void setUseDashboard(boolean useDashboard) {
        this.useDashboard = useDashboard;
    }

   public boolean getUseTagDefinition() {
        return useTagDefinition;
    }

    public void setUseTagDefinition(boolean useTagDefinition) {
        this.useTagDefinition = useTagDefinition;
    }



	public int getDid () {
		return did;
	}

	public void setDid (int did) {
		this.did = did;
	}

	public int getActualDid () {
		return did;
	}

	public int getTid () {
		return tid;
	}

	public void setTid (int tid) {
		this.tid = tid;
	}

	public int getActualTid () {
		return tid;
	}
}
