<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Event Messages</title>

<style>
input[type=text],input[type=password] {
  width: 100%;
}
td.payload {
    width: 80%;
}
</style>

<script type="text/javascript" src="<c:url value="/resources/jquery.min.js"/>"></script>
</head>

<body>
<h1>Event Messages</h1>
<form id="ajaxPublishform" action="<c:url value="/Event"/>" method="post">
<table>
    <tr>
        <td><label>UserName</label></td>
        <td><input type="text" name="username" value=""/></td>
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
</table>
<br>
<br>
<table id="eventsList">
    <tr>
        <th>Topic</th>
        <th>Payload</th>
    </tr>
    <tr>
        <td><input type="text" name="events[0].topic" value="Airings"/></td>
        <td class="payload"><input type="text" name="events[0].payload" value="TomCruise in Top Gun on air"/></td>
    </tr>
    <!--
    <tr>
        <td><input type="text" name="events[1].topic" value="What's Now"/></td>
        <td class="payload"><input type="text" name="events[1].payload" value="Game of Thrones @ 9 PM on HBO"/></td>
    </tr>
    -->
</table>

<input type="button" id="addeventButton" value="New Row"/><br><br><br>
<input type="button" id="publishButton" value="Publish Messages"/>
<input type="button" id="stopButton" value="Stop"/>
</form>

<script type="text/javascript">
var counter = 1;
var stop = false;

$(document).ready(function()
{

    $("#addeventButton").click(function()
    {
        $("#eventsList").append('<tr><td><input type="text" name="events['+counter+'].topic" placeholder="topic name"/></td>'
            +'<td class="payload"><input type="text" name="events['+counter+'].payload" placeholder="payload text"/></td></tr>');
        counter++;
    });

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

    $("#stopButton").click(function()
    {
        stop = true;
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
            $("#response").append(postResponse).append("<b>Published</b><br>");
            if (stop == false)
            {
                publish(postData,formURL);
            }
            else
            {
                $("#response").append("<h3>Stopped!</h3>");
            }
                
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