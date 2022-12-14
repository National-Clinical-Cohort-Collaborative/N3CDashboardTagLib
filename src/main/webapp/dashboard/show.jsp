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
<fmt:parseNumber var="ID" value="${param.ID}" />
<fmt:parseNumber var="id2" value="${param.id2}" />
<dashboard:dashboard ID="${ID}" id2="${id2}">
	<h2>Dashboard: <dashboard:dashboardID /> <dashboard:dashboardId2 /></h2>
<ul>
	<li><a href="../category<util:applicationRoot/>/show.jsp?ID=<dashboard:dashboardID />">Category</a>
</ul>
		<table border=1>
			<tr>
			<th>ID</th>
			<th>Id2</th>
			<th>Seqnum</th>
			<th>Title</th>
			<th>Description</th>
			<th>Path</th>
			<th>ThumbnailPath</th>
			<th>Thumbnail</th>
			<th>ThumbnailName</th>
			</tr>
			<tr>
				<td><a href="../category/category.jsp?ID=<dashboard:dashboardID />"><dashboard:dashboardID /></a></td>
				<td><a href="../../n3cDashboard/dashboard/editDashboard.jsp?ID=<dashboard:dashboardID />&id2=<dashboard:dashboardId2 />"><dashboard:dashboardId2 /></a></td>
				<td><dashboard:dashboardSeqnum /></td>
				<td><dashboard:dashboardTitle /></td>
				<td><dashboard:dashboardDescription /></td>
				<td><dashboard:dashboardPath /></td>
				<td><dashboard:dashboardThumbnailPath /></td>
				<td><img src="../dashboard/displayDashboardThumbnail.jsp?&size=120&ID=<dashboard:dashboardID />&id2=<dashboard:dashboardId2 />"></td>
				<td><dashboard:dashboardThumbnailName /></td>
			</tr>
		</table>

						<h2>Topic List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>ID</th>
									<th>Id2</th>
									<th>Id3</th>
									<th>Seqnum</th>
									<th>Title</th>
									<th>Path</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachTopic var="id3Iter">
									<dashboard:topic>
										<tr>
											<td><a href="../dashboard/dashboard.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicID />"><dashboard:topicID /></a></td>
											<td><a href="../dashboard/dashboard.jsp?ID=<dashboard:topicId2 />&id2=<dashboard:topicId2 />"><dashboard:topicId2 /></a></td>
											<td><a href="../topic/show.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicId2 />&id3=<dashboard:topicId3 />"><dashboard:topicId3 /></a></td>
											<td><dashboard:topicSeqnum /></td>
											<td><dashboard:topicTitle /></td>
											<td><dashboard:topicPath /></td>
											<td><a href="../topic/edit.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicId2 />&id3=<dashboard:topicId3 />">edit</a></td>
											<td><a href="../topic/delete.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicId2 />&id3=<dashboard:topicId3 />">delete</a></td>
										</tr>
									</dashboard:topic>
								</dashboard:foreachTopic>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../topic/add.jsp?ID=${param.ID}&id2=${param.id2}">add Topic</a>
						<br/><br/>

		<dashboard:foreachTopic var="id3Iter">
			<dashboard:topic>
		<a href="../dashboard/dashboard.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicID />"><dashboard:topicID /></a>
		<a href="../dashboard/dashboard.jsp?ID=<dashboard:topicId2 />&id2=<dashboard:topicId2 />"><dashboard:topicId2 /></a>
						<a href="../../n3cDashboard/topic/topic.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicId2 />&id3=<dashboard:topicId3 />"><dashboard:topicId3 /></a>
		<dashboard:topicSeqnum />
		<dashboard:topicTitle />
		<dashboard:topicPath />
			<c:if test="${ ! id3Iter.isLast() }" >, </c:if>					</dashboard:topic>
			</dashboard:foreachTopic>
</dashboard:dashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>