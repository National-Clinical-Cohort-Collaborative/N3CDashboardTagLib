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
			<th>Jsp</th>
			<th>Active</th>
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
				<td><dashboard:dashboardJsp /></td>
				<td><dashboard:dashboardActive /></td>
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
											<td><a href="../typeDefinition/typeDefinition.jsp?tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeTid /></a></td>
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
		<a href="../typeDefinition/typeDefinition.jsp?tid=<dashboard:dashboardTypeTid />"><dashboard:dashboardTypeTid /></a>
			<c:if test="${ ! didIter.isLast() }" >, </c:if>					</dashboard:dashboardType>
			</dashboard:foreachDashboardType>
</dashboard:dashboard>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>