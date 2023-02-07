<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.did }">
	<c:redirect url="list.jsp"/>
</c:if>

<c:if test="${ empty param.rid }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="did" value="${param.did}" />
<fmt:parseNumber var="rid" value="${param.rid}" />

<dashboard:deleteRelatedDashboard did="${did}" rid="${rid}"/>

<c:redirect url="list.jsp"/>