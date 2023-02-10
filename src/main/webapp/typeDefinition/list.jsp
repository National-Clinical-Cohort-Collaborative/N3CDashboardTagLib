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

						<h2>TypeDefinition List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Tid</th>
									<th>Type</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachTypeDefinition var="tidIter">
									<dashboard:typeDefinition>
										<tr>
											<td><a href="../typeDefinition/show.jsp?tid=<dashboard:typeDefinitionTid />"><dashboard:typeDefinitionTid /></a></td>
											<td><dashboard:typeDefinitionType /></td>
											<td><a href="../typeDefinition/edit.jsp?tid=<dashboard:typeDefinitionTid />">edit</a></td>
											<td><a href="../typeDefinition/delete.jsp?tid=<dashboard:typeDefinitionTid />">delete</a></td>
										</tr>
									</dashboard:typeDefinition>
								</dashboard:foreachTypeDefinition>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../typeDefinition/add.jsp">add TypeDefinition</a>
						<br/><br/>

		<dashboard:foreachTypeDefinition var="tidIter">
			<dashboard:typeDefinition>
						<a href="../../n3cDashboard/typeDefinition/typeDefinition.jsp?tid=<dashboard:typeDefinitionTid />"><dashboard:typeDefinitionTid /></a>
		<dashboard:typeDefinitionType />
			<c:if test="${ ! tidIter.isLast() }" >, </c:if>					</dashboard:typeDefinition>
			</dashboard:foreachTypeDefinition>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>