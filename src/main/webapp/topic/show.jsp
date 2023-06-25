<%@ include file="../_include.jsp"  %>

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
	<h2>Topic: <dashboard:topicDid /> <dashboard:topicTid /></h2>
<ul>
	<li><a href="../dashboard/show.jsp?did=<dashboard:topicDid />">Dashboard</a>
</ul>
		<table border=1>
			<tr>
			<th>Did</th>
			<th>Tid</th>
			<th>Seqnum</th>
			<th>Title</th>
			<th>Path</th>
			</tr>
			<tr>
				<td><a href="../dashboard/dashboard.jsp?did=<dashboard:topicDid />"><dashboard:topicDid /></a></td>
				<td><a href="edit.jsp?tid=<dashboard:topicTid />&did=<dashboard:topicDid />"><dashboard:topicTid /></a></td>
				<td><dashboard:topicSeqnum /></td>
				<td><dashboard:topicTitle /></td>
				<td><dashboard:topicPath /></td>
			</tr>
		</table>
</dashboard:topic>
                </div>
            </div>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>