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

						<h2>TagDefinition List</h2>
						<table class="table table-bordered table-striped table-hover table-datatable">
							<thead>
								<tr>
									<th>Tid</th>
									<th>Tag</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<dashboard:foreachTagDefinition var="tidIter">
									<dashboard:tagDefinition>
										<tr>
											<td><a href="../tagDefinition/show.jsp?tid=<dashboard:tagDefinitionTid />"><dashboard:tagDefinitionTid /></a></td>
											<td><dashboard:tagDefinitionTag /></td>
											<td><a href="../tagDefinition/edit.jsp?tid=<dashboard:tagDefinitionTid />">edit</a></td>
											<td><a href="../tagDefinition/delete.jsp?tid=<dashboard:tagDefinitionTid />">delete</a></td>
										</tr>
									</dashboard:tagDefinition>
								</dashboard:foreachTagDefinition>
							</tbody>
							<tfoot>
							</tfoot>
						</table>

						<br/>

						<a class="btn" href="../tagDefinition/add.jsp">add TagDefinition</a>
						<br/><br/>

		<dashboard:foreachTagDefinition var="tidIter">
			<dashboard:tagDefinition>
						<a href="../../n3cDashboard/tagDefinition/tagDefinition.jsp?tid=<dashboard:tagDefinitionTid />"><dashboard:tagDefinitionTid /></a>
		<dashboard:tagDefinitionTag />
			<c:if test="${ ! tidIter.isLast() }" >, </c:if>					</dashboard:tagDefinition>
			</dashboard:foreachTagDefinition>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>