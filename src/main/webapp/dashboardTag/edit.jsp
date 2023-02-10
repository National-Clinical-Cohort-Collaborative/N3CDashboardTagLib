<%@ include file="../_include.jsp" %>

<c:choose>
	<c:when test="${empty param.submit and not empty param.did}">
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
								<fmt:parseNumber var="tid" value="${param.tid}" />
								<dashboard:dashboardTag did="${did}" tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>DashboardTag</legend>
											<label for="Dashboard">Dashboard</label>
											<br>
												<dashboard:foreachDashboard var="dashboardIter">
													<dashboard:dashboard>
													<input type="radio" name="did" value="<dashboard:dashboardDid />"><label for="did"><dashboard:dashboardDid /></label>
													<br>
													</dashboard:dashboard>
												</dashboard:foreachDashboard>
											<label for="TagDefinition">TagDefinition</label>
											<br>
												<dashboard:foreachTagDefinition var="tagDefinitionIter">
													<dashboard:tagDefinition>
													<input type="radio" name="tid" value="<dashboard:tagDefinitionTid />"><label for="tid"><dashboard:tagDefinitionTid /></label>
													<br>
													</dashboard:tagDefinition>
												</dashboard:foreachTagDefinition>
											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="did" value="${param.did}">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:dashboardTag>
                        </div>
                    </div>
                </div>
                <jsp:include page="../footer.jsp" />
            </body>
        </html>
	</c:when>
	<c:when test="${empty param.submit and not empty param.tid}">
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
								<fmt:parseNumber var="tid" value="${param.tid}" />
								<dashboard:dashboardTag did="${did}" tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>DashboardTag</legend>
											<label for="Dashboard">Dashboard</label>
											<br>
												<dashboard:foreachDashboard var="dashboardIter">
													<dashboard:dashboard>
													<input type="radio" name="did" value="<dashboard:dashboardDid />"><label for="did"><dashboard:dashboardDid /></label>
													<br>
													</dashboard:dashboard>
												</dashboard:foreachDashboard>
											<label for="TagDefinition">TagDefinition</label>
											<br>
												<dashboard:foreachTagDefinition var="tagDefinitionIter">
													<dashboard:tagDefinition>
													<input type="radio" name="tid" value="<dashboard:tagDefinitionTid />"><label for="tid"><dashboard:tagDefinitionTid /></label>
													<br>
													</dashboard:tagDefinition>
												</dashboard:foreachTagDefinition>
											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="did" value="${param.did}">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:dashboardTag>
                        </div>
                    </div>
                </div>
                <jsp:include page="../footer.jsp" />
            </body>
        </html>
	</c:when>
	<c:when test="${param.submit eq 'Cancel'}">
		<c:redirect url="list.jsp" />
	</c:when>
	<c:when test="${param.submit eq 'Save'}">
		<fmt:parseNumber var="did" value="${param.did}" />
		<fmt:parseNumber var="tid" value="${param.tid}" />
		<dashboard:dashboardTag did="${did}" tid="${tid}">
		</dashboard:dashboardTag>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>