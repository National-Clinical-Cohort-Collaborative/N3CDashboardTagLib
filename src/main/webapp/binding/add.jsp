<%@ include file="../_include.jsp" %>

<c:choose>
	<c:when test="${empty param.submit and not empty param.cid}">
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
										<legend>Binding</legend>
										<label for="Dashboard">Dashboard</label>
										<br>
											<dashboard:foreachDashboard var="dashboardIter">
												<dashboard:dashboard>
												<input type="radio" name="did" value="<dashboard:dashboardDid />"><label for="did"><dashboard:dashboardDid /></label>
												<br>
												</dashboard:dashboard>
											</dashboard:foreachDashboard>
										<label for="seqnum">Seqnum</label>
										<input type="text" id="seqnum" name="seqnum" size="40" value="">
										<br>

										<input type="submit" name="submit" value="Save">
										<input type="submit" name="submit" value="Cancel">
										<input type="hidden" name="cid" value="${param.cid}">
									</fieldset>
								</form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../footer.jsp" />
            </body>
        </html>
	</c:when>
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
								<form action="add.jsp" method="post" >
									<fieldset>
										<legend>Binding</legend>
										<label for="Category">Category</label>
										<br>
											<dashboard:foreachCategory var="categoryIter">
												<dashboard:category>
												<input type="radio" name="cid" value="<dashboard:categoryCid />"><label for="cid"><dashboard:categoryCid /></label>
												<br>
												</dashboard:category>
											</dashboard:foreachCategory>
										<label for="seqnum">Seqnum</label>
										<input type="text" id="seqnum" name="seqnum" size="40" value="">
										<br>

										<input type="submit" name="submit" value="Save">
										<input type="submit" name="submit" value="Cancel">
										<input type="hidden" name="did" value="${param.did}">
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
		<dashboard:category cid = "${param.cid}">
		<dashboard:dashboard did = "${param.did}">
			<dashboard:binding>
				<dashboard:bindingSeqnum seqnum = "${param.seqnum}" />
			</dashboard:binding>
		</dashboard:dashboard>
		</dashboard:category>
		<c:redirect url="list.jsp" />
	</c:when>
	<c:otherwise>
		A task is required for this function.
	</c:otherwise>
</c:choose>