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
								<fmt:parseNumber var="ID" value="${param.ID}" />
								<dashboard:category ID="${ID}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>Category</legend>
											<label for="seqnum">Seqnum</label>
											<input type="text" id="seqnum" name="seqnum" size="40" value="<dashboard:categorySeqnum />">
											<br>

											<label for="label">Label</label>
											<input type="text" id="label" name="label" size="40" value="<dashboard:categoryLabel />">
											<br>

											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="ID" value="${param.ID}">
										</fieldset>
									</form>
								</dashboard:category>
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
		<fmt:parseNumber var="ID" value="${param.ID}" />
		<fmt:parseNumber var="seqnum" value="${param.seqnum}" />
		<dashboard:category ID="${ID}">
			<dashboard:categorySeqnum seqnum = "${param.seqnum}" />
			<dashboard:categoryLabel label = "${param.label}" />
		</dashboard:category>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>