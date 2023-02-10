<%@ include file="../_include.jsp" %>
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
<fmt:parseNumber var="tid" value="${param.tid}" />
<dashboard:type tid="${tid}">
	<h2>Type: <dashboard:typeTid /></h2>
		<table border=1>
			<tr>
			<th>Tid</th>
			<th>Type</th>
			</tr>
			<tr>
				<td><a href="edit.jsp?tid=<dashboard:typeTid />"><dashboard:typeTid /></a></td>
				<td><dashboard:typeType /></td>
			</tr>
		</table>

						<h2>DashboardType List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Did</th>
									<th>Tid</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachDashboardType var="didIter">
									<dashboard:dashboardType>
										<tr>
											<td><a href="../dashboardType/show.jsp?did=<dashboard:dashboardTypeDid />&tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeDid /></a></td>
											<td><a href="../type/type.jsp?tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeTid /></a></td>
											<td><a href="../dashboardType/edit.jsp?did=<dashboard:dashboardTypeDid />&tid=<dashboard:dashboardTypeTid />">edit</a></td>
											<td><a href="../dashboardType/delete.jsp?did=<dashboard:dashboardTypeDid />&tid=<dashboard:dashboardTypeTid />">delete</a></td>
										</tr>
									</dashboard:dashboardType>
								</dashboard:foreachDashboardType>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../dashboardType/add.jsp?did=${param.did}&tid=${param.tid}">add DashboardType</a>
						<br/><br/>

		<dashboard:foreachDashboardType var="didIter">
			<dashboard:dashboardType>
						<a href="../../n3cDashboard/dashboardType/dashboardType.jsp?did=<dashboard:dashboardTypeDid />&tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeDid /></a>
		<a href="../type/type.jsp?tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeTid /></a>
			<c:if test="${ ! didIter.isLast() }" >, </c:if>					</dashboard:dashboardType>
			</dashboard:foreachDashboardType>
</dashboard:type>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>