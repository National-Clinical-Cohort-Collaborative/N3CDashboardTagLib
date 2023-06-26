package org.cd2h.N3CDashboardTagLib.binding;


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
import org.cd2h.N3CDashboardTagLib.category.Category;
import org.cd2h.N3CDashboardTagLib.dashboard.Dashboard;

@SuppressWarnings("serial")
public class BindingIterator extends N3CDashboardTagLibBodyTagSupport {
    int cid = 0;
    int did = 0;
	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	private static final Logger log = LogManager.getLogger(BindingIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String fromList = null;
    String joinCriteria = null;
    String filterCriteria = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

   boolean useCategory = false;
   boolean useDashboard = false;

	public static String bindingCountByCategory(String cid) throws JspTagException {
		int count = 0;
		BindingIterator theIterator = new BindingIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.binding where 1=1"
						+ " and cid = ?"
						);

			stat.setInt(1,Integer.parseInt(cid));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating Binding iterator", e);
			throw new JspTagException("Error: JDBC error generating Binding iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean categoryHasBinding(String cid) throws JspTagException {
		return ! bindingCountByCategory(cid).equals("0");
	}

	public static String bindingCountByDashboard(String did) throws JspTagException {
		int count = 0;
		BindingIterator theIterator = new BindingIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.binding where 1=1"
						+ " and did = ?"
						);

			stat.setInt(1,Integer.parseInt(did));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating Binding iterator", e);
			throw new JspTagException("Error: JDBC error generating Binding iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean dashboardHasBinding(String did) throws JspTagException {
		return ! bindingCountByDashboard(did).equals("0");
	}

	public static Boolean bindingExists (String cid, String did) throws JspTagException {
		int count = 0;
		BindingIterator theIterator = new BindingIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.binding where 1=1"
						+ " and cid = ?"
						+ " and did = ?"
						);

			stat.setInt(1,Integer.parseInt(cid));
			stat.setInt(2,Integer.parseInt(did));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating Binding iterator", e);
			throw new JspTagException("Error: JDBC error generating Binding iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

	public static Boolean categoryDashboardExists (String cid, String did) throws JspTagException {
		int count = 0;
		BindingIterator theIterator = new BindingIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.binding where 1=1"
						+ " and cid = ?"
						+ " and did = ?"
						);

			stat.setInt(1,Integer.parseInt(cid));
			stat.setInt(2,Integer.parseInt(did));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating Binding iterator", e);
			throw new JspTagException("Error: JDBC error generating Binding iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		Category theCategory = (Category)findAncestorWithClass(this, Category.class);
		if (theCategory!= null)
			parentEntities.addElement(theCategory);
		Dashboard theDashboard = (Dashboard)findAncestorWithClass(this, Dashboard.class);
		if (theDashboard!= null)
			parentEntities.addElement(theDashboard);

		if (theCategory == null) {
		} else {
			cid = theCategory.getCid();
		}
		if (theDashboard == null) {
		} else {
			did = theDashboard.getDid();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + generateFilterCriteria()
                                                        + (cid == 0 ? "" : " and cid = ?")
                                                        + (did == 0 ? "" : " and did = ?")
                                                        + generateLimitCriteria());
            if (cid != 0) stat.setInt(webapp_keySeq++, cid);
            if (did != 0) stat.setInt(webapp_keySeq++, did);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT n3c_dashboard.binding.cid, n3c_dashboard.binding.did from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + generateFilterCriteria()
                                                        + (cid == 0 ? "" : " and cid = ?")
                                                        + (did == 0 ? "" : " and did = ?")
                                                        + " order by " + generateSortCriteria()  +  generateLimitCriteria());
            if (cid != 0) stat.setInt(webapp_keySeq++, cid);
            if (did != 0) stat.setInt(webapp_keySeq++, did);
            rs = stat.executeQuery();

            if ( rs != null && rs.next() ) {
                cid = rs.getInt(1);
                did = rs.getInt(2);
                if (var != null)
                    pageContext.setAttribute(var, this);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating Binding iterator: " + stat, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: JDBC error generating Binding iterator: " + stat);
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error generating Binding iterator: " + stat,e);
			}

        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("n3c_dashboard.binding");
       if (useCategory)
          theBuffer.append(", n3c_dashboard.category");
       if (useDashboard)
          theBuffer.append(", n3c_dashboard.dashboard");

      return theBuffer.toString();
    }

    private String generateJoinCriteria() {
       StringBuffer theBuffer = new StringBuffer();
       if (useCategory)
          theBuffer.append(" and n3c_dashboard.category.cid = n3c_dashboard.binding.cid");
       if (useDashboard)
          theBuffer.append(" and n3c_dashboard.dashboard.did = n3c_dashboard.binding.did");

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
            return "cid,did";
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
                cid = rs.getInt(1);
                did = rs.getInt(2);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across Binding", e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error iterating across Binding" + stat.toString());
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error iterating across Binding",e);
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
            log.error("JDBC error ending Binding iterator",e);
			freeConnection();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving cid " + cid);
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error ending Binding iterator",e);
			}

        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        cid = 0;
        did = 0;
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


   public boolean getUseCategory() {
        return useCategory;
    }

    public void setUseCategory(boolean useCategory) {
        this.useCategory = useCategory;
    }

   public boolean getUseDashboard() {
        return useDashboard;
    }

    public void setUseDashboard(boolean useDashboard) {
        this.useDashboard = useDashboard;
    }



	public int getCid () {
		return cid;
	}

	public void setCid (int cid) {
		this.cid = cid;
	}

	public int getActualCid () {
		return cid;
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
}
