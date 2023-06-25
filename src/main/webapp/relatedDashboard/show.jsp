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
<fmt:parseNumber var="rid" value="${param.rid}" />
<dashboard:relatedDashboard did="${did}" rid="${rid}">
	<h2>RelatedDashboard: <dashboard:relatedDashboardDid /> <dashboard:relatedDashboardRid /></h2>
<ul>
	<li><a href="../dashboard/show.jsp?did=<dashboard:relatedDashboardDid />">Dashboard</a>
</ul>
		<table border=1>
			<tr>
			<th>Did</th>
			<th>Rid</th>
			<th>Seqnum</th>
			</tr>
			<tr>
				<td><a href="../dashboard/dashboard.jsp?did=<dashboard:relatedDashboardDid />"><dashboard:relatedDashboardDid /></a></td>
				<td><a href="edit.jsp?did=<dashboard:relatedDashboardDid />&rid=<dashboard:relatedDashboardRid />"><dashboard:relatedDashboardRid /></a></td>
				<td><dashboard:relatedDashboardSeqnum /></td>
			</tr>
		</table>
</dashboard:relatedDashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>