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

						<h2>Dashboard List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>ID</th>
									<th>Id2</th>
									<th>Seqnum</th>
									<th>Title</th>
									<th>Description</th>
									<th>Path</th>
									<th>ThumbnailPath</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachDashboard var="id2Iter">
									<dashboard:dashboard>
										<tr>
											<td><a href="../category/category.jsp?ID=<dashboard:dashboardID />"><dashboard:dashboardID /></a></td>
											<td><a href="../dashboard/show.jsp?ID=<dashboard:dashboardID />&id2=<dashboard:dashboardId2 />"><dashboard:dashboardId2 /></a></td>
											<td><dashboard:dashboardSeqnum /></td>
											<td><dashboard:dashboardTitle /></td>
											<td><dashboard:dashboardDescription /></td>
											<td><dashboard:dashboardPath /></td>
											<td><dashboard:dashboardThumbnailPath /></td>
											<td><a href="../dashboard/edit.jsp?ID=<dashboard:dashboardID />&id2=<dashboard:dashboardId2 />">edit</a></td>
											<td><a href="../dashboard/delete.jsp?ID=<dashboard:dashboardID />&id2=<dashboard:dashboardId2 />">delete</a></td>
										</tr>
									</dashboard:dashboard>
								</dashboard:foreachDashboard>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../dashboard/add.jsp?ID=${param.ID}">add Dashboard</a>
						<br/><br/>

		<dashboard:foreachDashboard var="id2Iter">
			<dashboard:dashboard>
		<a href="../category/category.jsp?ID=<dashboard:dashboardID />"><dashboard:dashboardID /></a>
						<a href="../../n3cDashboard/dashboard/dashboard.jsp?ID=<dashboard:dashboardID />&id2=<dashboard:dashboardId2 />"><dashboard:dashboardId2 /></a>
		<dashboard:dashboardSeqnum />
		<dashboard:dashboardTitle />
		<dashboard:dashboardDescription />
		<dashboard:dashboardPath />
		<dashboard:dashboardThumbnailPath />
			<c:if test="${ ! id2Iter.isLast() }" >, </c:if>					</dashboard:dashboard>
			</dashboard:foreachDashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>