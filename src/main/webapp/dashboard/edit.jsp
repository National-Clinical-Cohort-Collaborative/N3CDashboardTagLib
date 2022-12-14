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
								<fmt:parseNumber var="id2" value="${param.id2}" />
								<dashboard:dashboard ID="${ID}" id2="${id2}">
									<form name="dashboard" method="post" action="/n3c-dashboard-admin/DashboardUploadServlet"enctype="multipart/form-data">
										<fieldset>
											<legend>Dashboard</legend>
											<label for="seqnum">Seqnum</label>
											<input type="text" id="seqnum" name="seqnum" size="40" value="<dashboard:dashboardSeqnum />">
											<br>

											<label for="title">Title</label>
											<input type="text" id="title" name="title" size="40" value="<dashboard:dashboardTitle />">
											<br>

											<label for="description">Description</label>
											<input type="text" id="description" name="description" size="40" value="<dashboard:dashboardDescription />">
											<br>

											<label for="path">Path</label>
											<input type="text" id="path" name="path" size="40" value="<dashboard:dashboardPath />">
											<br>

											<label for="thumbnailPath">ThumbnailPath</label>
											<input type="text" id="thumbnailPath" name="thumbnailPath" size="40" value="<dashboard:dashboardThumbnailPath />">
											<br>

											<label for="thumbnail">Thumbnail</label>
											<input type="file" id="thumbnail" name="thumbnail">
											<br>

											<label for="thumbnailName">ThumbnailName</label>
											<input type="text" id="thumbnailName" name="thumbnailName" size="40" value="<dashboard:dashboardThumbnailName />">
											<br>

											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="ID" value="${param.ID}">
											<input type="hidden" name="id2" value="${param.id2}">
										</fieldset>
									</form>
								</dashboard:dashboard>
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
		<fmt:parseNumber var="id2" value="${param.id2}" />
		<fmt:parseNumber var="seqnum" value="${param.seqnum}" />
		<dashboard:dashboard ID="${ID}" id2="${id2}">
			<dashboard:dashboardSeqnum seqnum = "${param.seqnum}" />
			<dashboard:dashboardTitle title = "${param.title}" />
			<dashboard:dashboardDescription description = "${param.description}" />
			<dashboard:dashboardPath path = "${param.path}" />
			<dashboard:dashboardThumbnailPath thumbnailPath = "${param.thumbnailPath}" />
			<dashboard:dashboardThumbnailName thumbnailName = "${param.thumbnailName}" />
		</dashboard:dashboard>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>