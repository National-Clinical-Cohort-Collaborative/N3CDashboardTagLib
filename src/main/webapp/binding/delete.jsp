<%@ include file="../_include.jsp"  %>

<c:if test="${ empty param.cid }">
	<c:redirect url="list.jsp"/>
</c:if>

<c:if test="${ empty param.did }">
	<c:redirect url="list.jsp"/>
</c:if>

<fmt:parseNumber var="cid" value="${param.cid}" />
<fmt:parseNumber var="did" value="${param.did}" />

<dashboard:deleteBinding cid="${cid}" did="${did}"/>

<c:redirect url="list.jsp"/>