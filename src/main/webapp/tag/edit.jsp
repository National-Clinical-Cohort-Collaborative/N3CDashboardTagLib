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
								<dashboard:tag tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>Tag</legend>
											<label for="tag">Tag</label>
											<input type="text" id="tag" name="tag" size="40" value="<dashboard:tagTag />">
											<br>

											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:tag>
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
		<dashboard:tag tid="${tid}">
			<dashboard:tagTag tag = "${param.tag}" />
		</dashboard:tag>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>