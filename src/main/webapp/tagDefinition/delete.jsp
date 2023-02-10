<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.tid }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="tid" value="${param.tid}" />

<dashboard:deleteTagDefinition tid="${tid}"/>

<c:redirect url="list.jsp"/>