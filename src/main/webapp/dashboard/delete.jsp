<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.ID }">
	<c:redirect url="list.jsp"/>
</c:if>

<c:if test="${ empty param.id2 }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="ID" value="${param.ID}" />
<fmt:parseNumber var="id2" value="${param.id2}" />

<dashboard:deleteDashboard ID="${ID}" id2="${id2}"/>

<c:redirect url="list.jsp"/>