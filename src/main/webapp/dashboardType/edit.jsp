<%@ include file="../_include.jsp"  %>


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
								<dashboard:dashboardType did="${did}" tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>DashboardType</legend>
											<label for="Dashboard">Dashboard</label>
											<br>
												<dashboard:foreachDashboard var="dashboardIter">
													<dashboard:dashboard>
													<input type="radio" name="did" value="<dashboard:dashboardDid />"><label for="did"><dashboard:dashboardDid /></label>
													<br>
													</dashboard:dashboard>
												</dashboard:foreachDashboard>
											<label for="TypeDefinition">TypeDefinition</label>
											<br>
												<dashboard:foreachTypeDefinition var="typeDefinitionIter">
													<dashboard:typeDefinition>
													<input type="radio" name="tid" value="<dashboard:typeDefinitionTid />"><label for="tid"><dashboard:typeDefinitionTid /></label>
													<br>
													</dashboard:typeDefinition>
												</dashboard:foreachTypeDefinition>
											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="did" value="${param.did}">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:dashboardType>
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
								<dashboard:dashboardType did="${did}" tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>DashboardType</legend>
											<label for="Dashboard">Dashboard</label>
											<br>
												<dashboard:foreachDashboard var="dashboardIter">
													<dashboard:dashboard>
													<input type="radio" name="did" value="<dashboard:dashboardDid />"><label for="did"><dashboard:dashboardDid /></label>
													<br>
													</dashboard:dashboard>
												</dashboard:foreachDashboard>
											<label for="TypeDefinition">TypeDefinition</label>
											<br>
												<dashboard:foreachTypeDefinition var="typeDefinitionIter">
													<dashboard:typeDefinition>
													<input type="radio" name="tid" value="<dashboard:typeDefinitionTid />"><label for="tid"><dashboard:typeDefinitionTid /></label>
													<br>
													</dashboard:typeDefinition>
												</dashboard:foreachTypeDefinition>
											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="did" value="${param.did}">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:dashboardType>
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
		<dashboard:dashboardType did="${did}" tid="${tid}">
		</dashboard:dashboardType>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>