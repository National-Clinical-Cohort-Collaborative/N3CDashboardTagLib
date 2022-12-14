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
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>