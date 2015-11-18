<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<style>
table, td, th {
    border: 1px solid black;
}
</style>
<title>ClientSimulator</title>
<script type="text/javascript" src="<c:url value="/resources/jquery.min.js"/>"></script>
</head>
<body>

<h1>Notification Messages Board</h1>

<button onclick="clearInterval(autoreload)">Break</button>
<button id="clear">Clear</button>
<br>
<br>

<table>
    <tr>
        <th>Received Time</th>
        <th>Message Text</th>
        <th>Time Lapse sec</th>
    </tr>
    <c:forEach var="message" items="${messagesList}">
    <tr>
        <td>${message.receivedTimeString}</td>
        <td>${message.messageString}</td>
        <td>${message.timeLapse}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>

<script>var autoreload = setInterval(function(){location.reload();}, 3000);</script>

<script type="text/javascript">
$(document).ready(function()
{
    $("#clear").click(function()
    {
        if (!confirm("Clear All Messages?")){
            return false;
        };
        $.ajax(
        {
            url : window.location.pathname,
            type: "DELETE"
        }).always(function()
        {
            location.reload();
        })
    });
});
</script>
