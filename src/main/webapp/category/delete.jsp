<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.cid }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="cid" value="${param.cid}" />

<dashboard:deleteCategory cid="${cid}"/>

<c:redirect url="list.jsp"/>