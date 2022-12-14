<%@ include file="../_include.jsp" %>
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
<fmt:parseNumber var="cid" value="${param.cid}" />
<fmt:parseNumber var="did" value="${param.did}" />
<dashboard:binding cid="${cid}" did="${did}">
	<h2>Binding: <dashboard:bindingCid /> <dashboard:bindingDid /></h2>
<ul>
	<li><a href="../category/show.jsp?cid=<dashboard:bindingCid />">Category</a>
	<li><a href="../dashboard/show.jsp?did=<dashboard:bindingDid />">Dashboard</a>
</ul>
		<table border=1>
			<tr>
			<th>Cid</th>
			<th>Did</th>
			<th>Seqnum</th>
			</tr>
			<tr>
				<td><a href="edit.jsp?cid=<dashboard:bindingCid />&did=<dashboard:bindingDid />"><dashboard:bindingCid /></a></td>
				<td><a href="../dashboard/dashboard.jsp?did=<dashboard:bindingDid />"><dashboard:bindingDid /></a></td>
				<td><dashboard:bindingSeqnum /></td>
			</tr>
		</table>
</dashboard:binding>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>