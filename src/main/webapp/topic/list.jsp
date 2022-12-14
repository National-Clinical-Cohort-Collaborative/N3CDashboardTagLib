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
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>