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
								<dashboard:topic did="${did}" tid="${tid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>Topic</legend>
											<label for="seqnum">Seqnum</label>
											<input type="text" id="seqnum" name="seqnum" size="40" value="<dashboard:topicSeqnum />">
											<br>

											<label for="title">Title</label>
											<input type="text" id="title" name="title" size="40" value="<dashboard:topicTitle />">
											<br>

											<label for="path">Path</label>
											<input type="text" id="path" name="path" size="40" value="<dashboard:topicPath />">
											<br>

											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="did" value="${param.did}">
											<input type="hidden" name="tid" value="${param.tid}">
										</fieldset>
									</form>
								</dashboard:topic>
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
		<fmt:parseNumber var="seqnum" value="${param.seqnum}" />
		<dashboard:topic did="${did}" tid="${tid}">
			<dashboard:topicSeqnum seqnum = "${param.seqnum}" />
			<dashboard:topicTitle title = "${param.title}" />
			<dashboard:topicPath path = "${param.path}" />
		</dashboard:topic>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>