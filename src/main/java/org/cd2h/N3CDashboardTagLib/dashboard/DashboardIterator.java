package org.cd2h.N3CDashboardTagLib.dashboard;


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

@SuppressWarnings("serial")
public class DashboardIterator extends N3CDashboardTagLibBodyTagSupport {
    int ID = 0;
    int id2 = 0;
	Vector<N3CDashboardTagLibTagSupport> parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

	private static final Logger log = LogManager.getLogger(DashboardIterator.class);


    PreparedStatement stat = null;
    ResultSet rs = null;
    String sortCriteria = null;
    int limitCriteria = 0;
    String var = null;
    int rsCount = 0;

	public static String dashboardCountByCategory(String ID) throws JspTagException {
		int count = 0;
		DashboardIterator theIterator = new DashboardIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.dashboard where 1=1"
						+ " and id = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating Dashboard iterator", e);
			throw new JspTagException("Error: JDBC error generating Dashboard iterator");
		} finally {
			theIterator.freeConnection();
		}
		return "" + count;
	}

	public static Boolean categoryHasDashboard(String ID) throws JspTagException {
		return ! dashboardCountByCategory(ID).equals("0");
	}

	public static Boolean dashboardExists (String ID, String id2) throws JspTagException {
		int count = 0;
		DashboardIterator theIterator = new DashboardIterator();
		try {
			PreparedStatement stat = theIterator.getConnection().prepareStatement("SELECT count(*) from n3c_dashboard.dashboard where 1=1"
						+ " and id = ?"
						+ " and id2 = ?"
						);

			stat.setInt(1,Integer.parseInt(ID));
			stat.setInt(2,Integer.parseInt(id2));
			ResultSet crs = stat.executeQuery();

			if (crs.next()) {
				count = crs.getInt(1);
			}
			stat.close();
		} catch (SQLException e) {
			log.error("JDBC error generating Dashboard iterator", e);
			throw new JspTagException("Error: JDBC error generating Dashboard iterator");
		} finally {
			theIterator.freeConnection();
		}
		return count > 0;
	}

    public int doStartTag() throws JspException {
		Category theCategory = (Category)findAncestorWithClass(this, Category.class);
		if (theCategory!= null)
			parentEntities.addElement(theCategory);

		if (theCategory == null) {
		} else {
			ID = theCategory.getID();
		}


      try {
            //run count query  
            int webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT count(*) from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            rs = stat.executeQuery();

            if (rs.next()) {
                pageContext.setAttribute(var+"Total", rs.getInt(1));
            }


            //run select id query  
            webapp_keySeq = 1;
            stat = getConnection().prepareStatement("SELECT n3c_dashboard.dashboard.id, n3c_dashboard.dashboard.id2 from " + generateFromClause() + " where 1=1"
                                                        + generateJoinCriteria()
                                                        + (ID == 0 ? "" : " and id = ?")
                                                        + " order by " + generateSortCriteria()  +  generateLimitCriteria());
            if (ID != 0) stat.setInt(webapp_keySeq++, ID);
            rs = stat.executeQuery();

            if ( rs != null && rs.next() ) {
                ID = rs.getInt(1);
                id2 = rs.getInt(2);
                if (var != null)
                    pageContext.setAttribute(var, this);
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            log.error("JDBC error generating Dashboard iterator: " + stat, e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "Error: JDBC error generating Dashboard iterator: " + stat);
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error generating Dashboard iterator: " + stat,e);
			}

        }

        return SKIP_BODY;
    }

    private String generateFromClause() {
       StringBuffer theBuffer = new StringBuffer("n3c_dashboard.dashboard");
      return theBuffer.toString();
    }

    private String generateJoinCriteria() {
       StringBuffer theBuffer = new StringBuffer();
      return theBuffer.toString();
    }

    private String generateSortCriteria() {
        if (sortCriteria != null) {
            return sortCriteria;
        } else {
            return "id,id2";
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
                ID = rs.getInt(1);
                id2 = rs.getInt(2);
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            log.error("JDBC error iterating across Dashboard", e);

			freeConnection();
			clearServiceState();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error iterating across Dashboard" + stat.toString());
				return parent.doEndTag();
			}else{
				throw new JspException("JDBC error iterating across Dashboard",e);
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
            log.error("JDBC error ending Dashboard iterator",e);
			freeConnection();

			Tag parent = getParent();
			if(parent != null){
				pageContext.setAttribute("tagError", true);
				pageContext.setAttribute("tagErrorException", e);
				pageContext.setAttribute("tagErrorMessage", "JDBC error retrieving id2 " + id2);
				return parent.doEndTag();
			}else{
				throw new JspException("Error: JDBC error ending Dashboard iterator",e);
			}

        } finally {
            clearServiceState();
            freeConnection();
        }
        return super.doEndTag();
    }

    private void clearServiceState() {
        ID = 0;
        id2 = 0;
        parentEntities = new Vector<N3CDashboardTagLibTagSupport>();

        this.rs = null;
        this.stat = null;
        this.sortCriteria = null;
        this.var = null;
        this.rsCount = 0;
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



	public int getID () {
		return ID;
	}

	public void setID (int ID) {
		this.ID = ID;
	}

	public int getActualID () {
		return ID;
	}

	public int getId2 () {
		return id2;
	}

	public void setId2 (int id2) {
		this.id2 = id2;
	}

	public int getActualId2 () {
		return id2;
	}
}
