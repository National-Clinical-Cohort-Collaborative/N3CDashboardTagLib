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
								<form action="add.jsp" method="post" >
									<fieldset>
										<legend>Topic</legend>
										<label for="seqnum">Seqnum</label>
										<input type="text" id="seqnum" name="seqnum" size="40" value="">
										<br>

										<label for="title">Title</label>
										<input type="text" id="title" name="title" size="40" value="">
										<br>

										<label for="path">Path</label>
										<input type="text" id="path" name="path" size="40" value="">
										<br>

										<input type="submit" name="submit" value="Save">
										<input type="submit" name="submit" value="Cancel">
										<input type="hidden" name="ID" value="${param.ID}">
										<input type="hidden" name="id2" value="${param.id2}">
									</fieldset>
								</form>
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
		<dashboard:dashboard ID = "${param.ID}" id2 = "${param.id2}">
			<dashboard:topic>
				<dashboard:topicSeqnum seqnum = "${param.seqnum}" />
				<dashboard:topicTitle title = "${param.title}" />
				<dashboard:topicPath path = "${param.path}" />
			</dashboard:topic>
		</dashboard:dashboard>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>