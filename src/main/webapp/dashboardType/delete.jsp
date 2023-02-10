<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.did }">
	<c:redirect url="list.jsp"/>
</c:if>

<c:if test="${ empty param.tid }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="did" value="${param.did}" />
<fmt:parseNumber var="tid" value="${param.tid}" />

<dashboard:deleteDashboardType did="${did}" tid="${tid}"/>

<c:redirect url="list.jsp"/>