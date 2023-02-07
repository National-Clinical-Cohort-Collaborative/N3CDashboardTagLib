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
<fmt:parseNumber var="did" value="${param.did}" />
<dashboard:dashboard did="${did}">
	<h2>Dashboard: <dashboard:dashboardDid /></h2>
		<table border=1>
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
			</tr>
			<tr>
				<td><a href="edit.jsp?did=<dashboard:dashboardDid />"><dashboard:dashboardDid /></a></td>
				<td><dashboard:dashboardTitle /></td>
				<td><dashboard:dashboardDescription /></td>
				<td><dashboard:dashboardPath /></td>
				<td><dashboard:dashboardThumbnailPath /></td>
				<td><img src="../dashboard/displayDashboardThumbnail.jsp?&size=120&did=<dashboard:dashboardDid />"></td>
				<td><dashboard:dashboardThumbnailName /></td>
				<td><dashboard:dashboardBlurb /></td>
				<td><dashboard:dashboardLimitations /></td>
			</tr>
		</table>

						<h2>Topic List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Did</th>
									<th>Tid</th>
									<th>Seqnum</th>
									<th>Title</th>
									<th>Path</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachTopic var="tidIter">
									<dashboard:topic>
										<tr>
											<td><a href="../dashboard/dashboard.jsp?did=<dashboard:topicDid />"><dashboard:topicDid /></a></td>
											<td><a href="../topic/show.jsp?tid=<dashboard:topicTid />&did=<dashboard:topicDid />"><dashboard:topicTid /></a></td>
											<td><dashboard:topicSeqnum /></td>
											<td><dashboard:topicTitle /></td>
											<td><dashboard:topicPath /></td>
											<td><a href="../topic/edit.jsp?tid=<dashboard:topicTid />&did=<dashboard:topicDid />">edit</a></td>
											<td><a href="../topic/delete.jsp?tid=<dashboard:topicTid />&did=<dashboard:topicDid />">delete</a></td>
										</tr>
									</dashboard:topic>
								</dashboard:foreachTopic>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../topic/add.jsp?did=${param.did}">add Topic</a>
						<br/><br/>

		<dashboard:foreachTopic var="tidIter">
			<dashboard:topic>
		<a href="../dashboard/dashboard.jsp?did=<dashboard:topicDid />"><dashboard:topicDid /></a>
						<a href="../../n3cDashboard/topic/topic.jsp?tid=<dashboard:topicTid />&did=<dashboard:topicDid />"><dashboard:topicTid /></a>
		<dashboard:topicSeqnum />
		<dashboard:topicTitle />
		<dashboard:topicPath />
			<c:if test="${ ! tidIter.isLast() }" >, </c:if>					</dashboard:topic>
			</dashboard:foreachTopic>

						<h2>Binding List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Cid</th>
									<th>Did</th>
									<th>Seqnum</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachBinding var="cidIter">
									<dashboard:binding>
										<tr>
											<td><a href="../binding/show.jsp?cid=<dashboard:bindingCid />&did=<dashboard:bindingDid />"><dashboard:bindingCid /></a></td>
											<td><a href="../dashboard/dashboard.jsp?did=<dashboard:bindingDid />"><dashboard:bindingDid /></a></td>
											<td><dashboard:bindingSeqnum /></td>
											<td><a href="../binding/edit.jsp?cid=<dashboard:bindingCid />&did=<dashboard:bindingDid />">edit</a></td>
											<td><a href="../binding/delete.jsp?cid=<dashboard:bindingCid />&did=<dashboard:bindingDid />">delete</a></td>
										</tr>
									</dashboard:binding>
								</dashboard:foreachBinding>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../binding/add.jsp?cid=${param.cid}&did=${param.did}">add Binding</a>
						<br/><br/>

		<dashboard:foreachBinding var="cidIter">
			<dashboard:binding>
						<a href="../../n3cDashboard/binding/binding.jsp?cid=<dashboard:bindingCid />&did=<dashboard:bindingDid />"><dashboard:bindingCid /></a>
		<a href="../dashboard/dashboard.jsp?did=<dashboard:bindingDid />"><dashboard:bindingDid /></a>
		<dashboard:bindingSeqnum />
			<c:if test="${ ! cidIter.isLast() }" >, </c:if>					</dashboard:binding>
			</dashboard:foreachBinding>

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
</dashboard:dashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>