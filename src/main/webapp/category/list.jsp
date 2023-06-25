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

						<h2>Category List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Cid</th>
									<th>Seqnum</th>
									<th>Label</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachCategory var="cidIter">
									<dashboard:category>
										<tr>
											<td><a href="../category/show.jsp?cid=<dashboard:categoryCid />"><dashboard:categoryCid /></a></td>
											<td><dashboard:categorySeqnum /></td>
											<td><dashboard:categoryLabel /></td>
											<td><a href="../category/edit.jsp?cid=<dashboard:categoryCid />">edit</a></td>
											<td><a href="../category/delete.jsp?cid=<dashboard:categoryCid />">delete</a></td>
										</tr>
									</dashboard:category>
								</dashboard:foreachCategory>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../category/add.jsp">add Category</a>
						<br/><br/>

		<dashboard:foreachCategory var="cidIter">
			<dashboard:category>
						<a href="../../n3cDashboard/category/category.jsp?cid=<dashboard:categoryCid />"><dashboard:categoryCid /></a>
		<dashboard:categorySeqnum />
		<dashboard:categoryLabel />
			<c:if test="${ ! cidIter.isLast() }" >, </c:if>					</dashboard:category>
			</dashboard:foreachCategory>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>