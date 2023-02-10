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

						<h2>Tag List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Tid</th>
									<th>Tag</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachTag var="tidIter">
									<dashboard:tag>
										<tr>
											<td><a href="../tag/show.jsp?tid=<dashboard:tagTid />"><dashboard:tagTid /></a></td>
											<td><dashboard:tagTag /></td>
											<td><a href="../tag/edit.jsp?tid=<dashboard:tagTid />">edit</a></td>
											<td><a href="../tag/delete.jsp?tid=<dashboard:tagTid />">delete</a></td>
										</tr>
									</dashboard:tag>
								</dashboard:foreachTag>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../tag/add.jsp">add Tag</a>
						<br/><br/>

		<dashboard:foreachTag var="tidIter">
			<dashboard:tag>
						<a href="../../n3cDashboard/tag/tag.jsp?tid=<dashboard:tagTid />"><dashboard:tagTid /></a>
		<dashboard:tagTag />
			<c:if test="${ ! tidIter.isLast() }" >, </c:if>					</dashboard:tag>
			</dashboard:foreachTag>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>