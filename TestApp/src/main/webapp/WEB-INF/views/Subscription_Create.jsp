<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Create Subscription</title>
<style>
input.create_input {
  width: 300%;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/jquery.min.js"/>"></script>
</head>
<body>

<h1>Create a New Subscription</h1>
<form id="ajaxCreateform" action="<c:url value="/Subscription/Create"/>" method="post">
    <table>
        <tr>
            <td><label>Subscription Topic</label></td>
            <td>
                <select name="topicId">
                    <c:forEach var="topic" items="${TopicsList}">
                        <option value="${topic.topicId}">${topic.topicName}</option>
                    </c:forEach>
                </select>
                <div style="color:#FF0000">${GetTopicsListError}</div>
            </td>
        </tr>
        <tr>
            <td><label>Notification Push Url</label></td>
            <td><input class="create_input" name="notificationPushUrl" value="http://localhost:8080/TestApp/ClientSimulator/{topicName}"/></td>
        </tr>
        <tr>
            <td><label>Delivery Type</label></td>
            <td><input class="create_input" name="deliveryType" value="APIPush"/></td>
        </tr>
        <tr>
            <td><label>Delivery Timing</label></td>
            <td><input class="create_input" name="deliveryTiming" value="1"/></td>
        </tr>
        <tr>
            <td><label>Max Retry Count</label></td>
            <td><input class="create_input" name="maxRetryCount" value="3"/></td>
        </tr>
    </table>
</form>
<input type="button" id="createButton" value="Create"/>
<div id="response"></div>
<h2><a href="<c:url value="/Subscription"/>">Subscriptions List</a></h2>
</body>
</html>

<script type="text/javascript">
$(document).ready(function()
{
    $("#createButton").click(function()
    {
        $("#ajaxCreateform").submit(function(e)
        {
            $("#response").html("Creating...");
            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");
            $.ajax(
            {
                url : formURL,
                type : "POST",
                data : postData,
                success:function(data)
                {
                    $("#response").html(data);
                },
                error: function(jqXHR, textStatus, exception, errorThrown)
                {
                    $("#response").html(jqXHR+'<br>'+textStatus+'<br>'+exception+'<br>'+errorThrown);
                }
            });
            e.preventDefault();    //STOP default action
            e.unbind();
        });
        $("#ajaxCreateform").submit(); //SUBMIT FORM
    });
});
</script>