<%@ include file="../_include.jsp"  %>

        <html>
            <jsp:include page="../head.jsp" />
            <body>
                <div class="container-fluid">
                    <jsp:include page="../header.jsp" />
                    <div class="row flex-nowrap">
                        <div class="col-xs-3">
                            <jsp:include page="../menu.jsp" />
                        </div>
                        <div class="col-xs-8">
<fmt:parseNumber var="did" value="${param.did}" />
<fmt:parseNumber var="tid" value="${param.tid}" />
<dashboard:dashboardTag did="${did}" tid="${tid}">
	<h2>DashboardTag: <dashboard:dashboardTagDid /> <dashboard:dashboardTagTid /></h2>
<ul>
	<li><a href="../dashboard/show.jsp?did=<dashboard:dashboardTagDid />">Dashboard</a>
	<li><a href="../tagDefinition/show.jsp?tid=<dashboard:dashboardTagTid />">TagDefinition</a>
</ul>
		<table border=1>
			<tr>
			<th>Did</th>
			<th>Tid</th>
			</tr>
			<tr>
				<td><a href="edit.jsp?did=<dashboard:dashboardTagDid />&tid=<dashboard:dashboardTagTid />"><dashboard:dashboardTagDid /></a></td>
				<td><a href="../tagDefinition/tagDefinition.jsp?tid=<dashboard:dashboardTagTid />"><dashboard:dashboardTagTid /></a></td>
			</tr>
		</table>
</dashboard:dashboardTag>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>