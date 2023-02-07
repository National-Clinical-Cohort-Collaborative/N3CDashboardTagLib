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

						<h2>RelatedDashboard List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Did</th>
									<th>Rid</th>
									<th>Seqnum</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachRelatedDashboard var="ridIter">
									<dashboard:relatedDashboard>
										<tr>
											<td><a href="../dashboard/dashboard.jsp?did=<dashboard:relatedDashboardDid />"><dashboard:relatedDashboardDid /></a></td>
											<td><a href="../relatedDashboard/show.jsp?did=<dashboard:relatedDashboardDid />&rid=<dashboard:relatedDashboardRid />"><dashboard:relatedDashboardRid /></a></td>
											<td><dashboard:relatedDashboardSeqnum /></td>
											<td><a href="../relatedDashboard/edit.jsp?did=<dashboard:relatedDashboardDid />&rid=<dashboard:relatedDashboardRid />">edit</a></td>
											<td><a href="../relatedDashboard/delete.jsp?did=<dashboard:relatedDashboardDid />&rid=<dashboard:relatedDashboardRid />">delete</a></td>
										</tr>
									</dashboard:relatedDashboard>
								</dashboard:foreachRelatedDashboard>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../relatedDashboard/add.jsp?did=${param.did}">add RelatedDashboard</a>
						<br/><br/>

		<dashboard:foreachRelatedDashboard var="ridIter">
			<dashboard:relatedDashboard>
		<a href="../dashboard/dashboard.jsp?did=<dashboard:relatedDashboardDid />"><dashboard:relatedDashboardDid /></a>
						<a href="../../n3cDashboard/relatedDashboard/relatedDashboard.jsp?did=<dashboard:relatedDashboardDid />&rid=<dashboard:relatedDashboardRid />"><dashboard:relatedDashboardRid /></a>
		<dashboard:relatedDashboardSeqnum />
			<c:if test="${ ! ridIter.isLast() }" >, </c:if>					</dashboard:relatedDashboard>
			</dashboard:foreachRelatedDashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>