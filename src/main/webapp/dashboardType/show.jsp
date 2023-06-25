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
<dashboard:dashboardType did="${did}" tid="${tid}">
	<h2>DashboardType: <dashboard:dashboardTypeDid /> <dashboard:dashboardTypeTid /></h2>
<ul>
	<li><a href="../dashboard/show.jsp?did=<dashboard:dashboardTypeDid />">Dashboard</a>
	<li><a href="../typeDefinition/show.jsp?tid=<dashboard:dashboardTypeTid />">TypeDefinition</a>
</ul>
		<table border=1>
			<tr>
			<th>Did</th>
			<th>Tid</th>
			</tr>
			<tr>
				<td><a href="edit.jsp?did=<dashboard:dashboardTypeDid />&tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeDid /></a></td>
				<td><a href="../typeDefinition/typeDefinition.jsp?tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeTid /></a></td>
			</tr>
		</table>
</dashboard:dashboardType>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>