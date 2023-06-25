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

						<h2>DashboardTag List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Did</th>
									<th>Tid</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachDashboardTag var="didIter">
									<dashboard:dashboardTag>
										<tr>
											<td><a href="../dashboardTag/show.jsp?did=<dashboard:dashboardTagDid />&tid=<dashboard:dashboardTagTid />"><dashboard:dashboardTagDid /></a></td>
											<td><a href="../tagDefinition/tagDefinition.jsp?tid=<dashboard:dashboardTagTid />"><dashboard:dashboardTagTid /></a></td>
											<td><a href="../dashboardTag/edit.jsp?did=<dashboard:dashboardTagDid />&tid=<dashboard:dashboardTagTid />">edit</a></td>
											<td><a href="../dashboardTag/delete.jsp?did=<dashboard:dashboardTagDid />&tid=<dashboard:dashboardTagTid />">delete</a></td>
										</tr>
									</dashboard:dashboardTag>
								</dashboard:foreachDashboardTag>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../dashboardTag/add.jsp?did=${param.did}&tid=${param.tid}">add DashboardTag</a>
						<br/><br/>

		<dashboard:foreachDashboardTag var="didIter">
			<dashboard:dashboardTag>
						<a href="../../n3cDashboard/dashboardTag/dashboardTag.jsp?did=<dashboard:dashboardTagDid />&tid=<dashboard:dashboardTagTid />"><dashboard:dashboardTagDid /></a>
		<a href="../tagDefinition/tagDefinition.jsp?tid=<dashboard:dashboardTagTid />"><dashboard:dashboardTagTid /></a>
			<c:if test="${ ! didIter.isLast() }" >, </c:if>					</dashboard:dashboardTag>
			</dashboard:foreachDashboardTag>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>