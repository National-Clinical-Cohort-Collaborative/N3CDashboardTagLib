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
<fmt:parseNumber var="ID" value="${param.ID}" />
<fmt:parseNumber var="id2" value="${param.id2}" />
<fmt:parseNumber var="id3" value="${param.id3}" />
<dashboard:topic ID="${ID}" id2="${id2}" id3="${id3}">
	<h2>Topic: <dashboard:topicID /> <dashboard:topicId2 /> <dashboard:topicId3 /></h2>
<ul>
	<li><a href="../dashboard<util:applicationRoot/>/show.jsp?id2=<dashboard:topicId2 />?ID=<dashboard:topicID />">Dashboard</a>
</ul>
		<table border=1>
			<tr>
			<th>ID</th>
			<th>Id2</th>
			<th>Id3</th>
			<th>Seqnum</th>
			<th>Title</th>
			<th>Path</th>
			</tr>
			<tr>
				<td><a href="../dashboard/dashboard.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicID />"><dashboard:topicID /></a></td>
				<td><a href="../dashboard/dashboard.jsp?ID=<dashboard:topicId2 />&id2=<dashboard:topicId2 />"><dashboard:topicId2 /></a></td>
				<td><a href="../../n3cDashboard/topic/editTopic.jsp?ID=<dashboard:topicID />&id2=<dashboard:topicId2 />&id3=<dashboard:topicId3 />"><dashboard:topicId3 /></a></td>
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