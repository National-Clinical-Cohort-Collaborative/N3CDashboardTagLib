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
								<fmt:parseNumber var="rid" value="${param.rid}" />
								<dashboard:relatedDashboard did="${did}" rid="${rid}">
									<form action="edit.jsp" method="post" >
										<fieldset>
											<legend>RelatedDashboard</legend>
											<label for="seqnum">Seqnum</label>
											<input type="text" id="seqnum" name="seqnum" size="40" value="<dashboard:relatedDashboardSeqnum />">
											<br>

											<input type="submit" name="submit" value="Save">
											<input type="submit" name="submit" value="Cancel">
											<input type="hidden" name="did" value="${param.did}">
											<input type="hidden" name="rid" value="${param.rid}">
										</fieldset>
									</form>
								</dashboard:relatedDashboard>
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
		<fmt:parseNumber var="rid" value="${param.rid}" />
		<fmt:parseNumber var="seqnum" value="${param.seqnum}" />
		<dashboard:relatedDashboard did="${did}" rid="${rid}">
			<dashboard:relatedDashboardSeqnum seqnum = "${param.seqnum}" />
		</dashboard:relatedDashboard>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>