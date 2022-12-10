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
										<legend>Dashboard</legend>
										<label for="seqnum">Seqnum</label>
										<input type="text" id="seqnum" name="seqnum" size="40" value="">
										<br>

										<label for="title">Title</label>
										<input type="text" id="title" name="title" size="40" value="">
										<br>

										<label for="description">Description</label>
										<input type="text" id="description" name="description" size="40" value="">
										<br>

										<label for="path">Path</label>
										<input type="text" id="path" name="path" size="40" value="">
										<br>

										<label for="thumbnailPath">ThumbnailPath</label>
										<input type="text" id="thumbnailPath" name="thumbnailPath" size="40" value="">
										<br>

										<input type="submit" name="submit" value="Save">
										<input type="submit" name="submit" value="Cancel">
										<input type="hidden" name="ID" value="${param.ID}">
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
		<dashboard:category ID = "${param.ID}">
			<dashboard:dashboard>
				<dashboard:dashboardSeqnum seqnum = "${param.seqnum}" />
				<dashboard:dashboardTitle title = "${param.title}" />
				<dashboard:dashboardDescription description = "${param.description}" />
				<dashboard:dashboardPath path = "${param.path}" />
				<dashboard:dashboardThumbnailPath thumbnailPath = "${param.thumbnailPath}" />
			</dashboard:dashboard>
		</dashboard:category>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>