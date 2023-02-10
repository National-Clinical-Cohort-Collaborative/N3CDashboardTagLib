<%@ include file="../_include.jsp" %>

<c:choose>
	<c:when test="${empty param.submit}">
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
								<fmt:parseNumber var="tid" value="${param.tid}" />
								<dashboard:typeDefinition tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>TypeDefinition</legend>
											<label for="type">Type</label>
											<input type="text" id="type" name="type" size="40" value="<dashboard:typeDefinitionType />">
											<br>

											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:typeDefinition>
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
		<fmt:parseNumber var="tid" value="${param.tid}" />
		<dashboard:typeDefinition tid="${tid}">
			<dashboard:typeDefinitionType type = "${param.type}" />
		</dashboard:typeDefinition>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>