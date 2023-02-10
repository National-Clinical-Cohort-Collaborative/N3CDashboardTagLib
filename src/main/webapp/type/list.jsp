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

						<h2>Type List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Tid</th>
									<th>Type</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachType var="tidIter">
									<dashboard:type>
										<tr>
											<td><a href="../type/show.jsp?tid=<dashboard:typeTid />"><dashboard:typeTid /></a></td>
											<td><dashboard:typeType /></td>
											<td><a href="../type/edit.jsp?tid=<dashboard:typeTid />">edit</a></td>
											<td><a href="../type/delete.jsp?tid=<dashboard:typeTid />">delete</a></td>
										</tr>
									</dashboard:type>
								</dashboard:foreachType>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../type/add.jsp">add Type</a>
						<br/><br/>

		<dashboard:foreachType var="tidIter">
			<dashboard:type>
						<a href="../../n3cDashboard/type/type.jsp?tid=<dashboard:typeTid />"><dashboard:typeTid /></a>
		<dashboard:typeType />
			<c:if test="${ ! tidIter.isLast() }" >, </c:if>					</dashboard:type>
			</dashboard:foreachType>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>