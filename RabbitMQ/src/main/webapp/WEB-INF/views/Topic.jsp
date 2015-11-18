<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Topic Messages</title>

<style>
input[type=text],input[type=password] {
  width: 500%;
}
</style>

<script type="text/javascript" src="<c:url value="/resources/jquery.min.js"/>"></script>
</head>

<body>
<h1>Topic Messages</h1>
<form class="ajaxPublishform" name="ajaxPublishform" id="ajaxPublishform" action="<c:url value="/Topic"/>" method="post">
<table>
    <tr>
        <td><label>UserName</label></td>
        <td><input type="text" name="username" value="guest"/></td>
    </tr>
    <tr>
        <td><label>Password</label></td>
        <td><input type="password" name="password" value=""/></td>
    </tr>
    <tr>
        <td><label>Host</label></td>
        <td><input type="text" name="host" value=""/></td>
    </tr>
    <tr>
        <td><label>Port</label></td>
        <td><input type="text" name="port" value=""/></td>
    </tr>
    <tr>
        <td><label>Vhost</label></td>
        <td><input type="text" name="vhost" value="%2F"/></td>
    </tr>
    <tr>
        <td><label>Exchange_Name</label></td>
        <td><input type="text" name="exchange_name" value=""/></td>
    </tr>
    <tr>
        <td><label>Routing_Key</label></td>
        <td><input type="text" name="routing_key" value=""/></td>
    </tr>
    <tr><td><br><b>Message</b></td></tr>
    <tr>
        <td><label>Topic</label></td>
        <td><input type="text" name="topic" value="Airings"/></td>
    </tr>
    <tr>
        <td><label>Description</label></td>
        <td><input type="text" name="description" placeholder="description"/></td>
    </tr>
    <tr>
        <td><label>NameSpace</label></td>
        <td><input type="text" name="namespace" value=""/></td>
    </tr>
    <tr>
        <td><label>EventPayloadSchema</label></td>
        
        <td><input type="text" name="eventPayloadSchema" value="Test Schema"/></td>
    </tr>
    <tr>
        <td><label>EventAggregationTemplate</label></td>
        <td><input type="text" name="eventAggregationTemplate" value="Test Template"/></td>
    </tr>
</table>
</form>

<input type="button" id="publishButton" value="Publish Messages"/>

<script type="text/javascript">
var stop = false;

$(document).ready(function()
{
    $("#publishButton").click(function()
    {
        stop = false;
        $("#ajaxPublishform").submit(function(e)
        {
            $("#response").append("<h3>Publishing...</h3>");
            var postData = $(this).serializeArray();
            var formURL = $(this).attr("action");
            publish(postData,formURL);
        });
        $("#ajaxPublishform").submit(); //SUBMIT FORM
    });
});

function publish(postData,formURL)
{
    $.ajax(
    {
        url : formURL,
        type: "POST",
        data : postData,
        success:function(postResponse)
        {
            $("#response").append(postResponse);
            $("#response").append("<h3>Published</h3>");
        },
        error: function(jqXHR, textStatus, exception, errorThrown)
        {
            $("#response").html(jqXHR+'<br>'+textStatus+'<br>'+exception+'<br>'+errorThrown);
        }
    });
    e.preventDefault();    //STOP default action
    e.unbind();
}
</script>
<div id="response"></div>
</body>
</html>