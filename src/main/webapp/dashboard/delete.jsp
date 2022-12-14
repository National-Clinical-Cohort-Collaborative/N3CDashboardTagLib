<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.did }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="did" value="${param.did}" />

<dashboard:deleteDashboard did="${did}"/>

<c:redirect url="list.jsp"/>