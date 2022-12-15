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
									<th>Did</th>
									<th>Title</th>
									<th>Description</th>
									<th>Path</th>
									<th>ThumbnailPath</th>
									<th>Thumbnail</th>
									<th>ThumbnailName</th>
									<th>Blurb</th>
									<th>Limitations</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachDashboard var="didIter">
									<dashboard:dashboard>
										<tr>
											<td><a href="../dashboard/show.jsp?did=<dashboard:dashboardDid />"><dashboard:dashboardDid /></a></td>
											<td><dashboard:dashboardTitle /></td>
											<td><dashboard:dashboardDescription /></td>
											<td><dashboard:dashboardPath /></td>
											<td><dashboard:dashboardThumbnailPath /></td>
											<td><img src="../dashboard/displayDashboardThumbnail.jsp?&size=120&did=<dashboard:dashboardDid />"></td>
											<td><dashboard:dashboardThumbnailName /></td>
											<td><dashboard:dashboardBlurb /></td>
											<td><dashboard:dashboardLimitations /></td>
											<td><a href="../dashboard/edit.jsp?did=<dashboard:dashboardDid />">edit</a></td>
											<td><a href="../dashboard/delete.jsp?did=<dashboard:dashboardDid />">delete</a></td>
										</tr>
									</dashboard:dashboard>
								</dashboard:foreachDashboard>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../dashboard/add.jsp">add Dashboard</a>
						<br/><br/>

		<dashboard:foreachDashboard var="didIter">
			<dashboard:dashboard>
						<a href="../../n3cDashboard/dashboard/dashboard.jsp?did=<dashboard:dashboardDid />"><dashboard:dashboardDid /></a>
		<dashboard:dashboardTitle />
		<dashboard:dashboardDescription />
		<dashboard:dashboardPath />
		<dashboard:dashboardThumbnailPath />
		<img src="../dashboard/displayDashboardThumbnail.jsp?&size=120&did=<dashboard:dashboardDid />">
		<dashboard:dashboardThumbnailName />
		<dashboard:dashboardBlurb />
		<dashboard:dashboardLimitations />
			<c:if test="${ ! didIter.isLast() }" >, </c:if>					</dashboard:dashboard>
			</dashboard:foreachDashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>