<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Notification Subscription Tool</title>
<style>
table, th, td {
    border: 1px solid black;
}
td.Url {
    border: 1px solid black;
    width: 40%;
}
input {
    width: 100%;
}
</style>
<script type="text/javascript" src="<c:url value="/resources/jquery.min.js"/>"></script>
</head>
<body>


<!-- GET Subscriptions List -->
<h1>Subscriptions</h1>
<table>
    <tr>
        <th>Subscription_Id</th>
        <th>TopicId</th>
        <th>Topic</th>
        <th>DeliveryType</th>
        <th>NotificationPushUrl</th>
        <th>DeliveryTiming</th>
        <th>MaxRetryCount</th>
    </tr>
    <c:forEach var="Subscription" items="${SubscriptionsList}">
        <tr>
            <td>${Subscription.subscriptionId}</td>
            <td>${Subscription.topicId}</td>
            <td>${Subscription.topic}</td>
            <td>${Subscription.deliveryType}</td>
            <td class="Url"><input id="notificationPushUrl_${Subscription.subscriptionId}" value="${Subscription.notificationPushUrl}"/></td>
            <td>
                <select id="deliveryTiming_${Subscription.subscriptionId}">
                    <option value="${Subscription.deliveryTiming}" selected>${Subscription.deliveryTiming}</option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="3">3</option>
                    <option value="6">6</option>
                    <option value="12">12</option>
                    <option value="24">24</option>
                </select>
            </td>
            <td>
                <select id="maxRetryCount_${Subscription.subscriptionId}">
                    <option value="${Subscription.maxRetryCount}" selected>${Subscription.maxRetryCount}</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </td>
            <td><button id="updatesubsciption_${Subscription.subscriptionId}">Update</button></td>
            <td><button id="deletesubsciption_${Subscription.subscriptionId}">Delete</button></td>
        </tr>
    </c:forEach>
</table>
<div style="color:#FF0000">${GetSubscriptionsError}</div>
<h2><a href="<c:url value="/Subscription/Create"/>">New Subscription</a></h2>
</body>
</html>

<script type="text/javascript">
$(document).ready(function()
{
    $("button[id^='updatesubsciption_']").click(function()
    {
        var SubsriptionId = this.id.split('updatesubsciption_')[1];
        if (!confirm("Save changes to Subsription #"+SubsriptionId+"?")){
            return false;
        };
        $.ajax(
        {
            url : "<c:url value="/Subscription/"/>"+SubsriptionId,
            type : "PUT",
            data : "notificationPushUrl="+encodeURIComponent($("#notificationPushUrl_"+SubsriptionId).val())
                  +"&deliveryTiming="+$("#deliveryTiming_"+SubsriptionId).val()
                  +"&maxRetryCount="+$("#maxRetryCount_"+SubsriptionId).val()
        }).always(function()
        {
            location.reload();
        })
    });

    $("button[id^='deletesubsciption_']").click(function()
    {
        var SubsriptionId = this.id.split('deletesubsciption_')[1];
        if (!confirm("Delete Subsription #"+SubsriptionId+"?")){
            return false;
        };
        $.ajax(
        {
            url : "<c:url value="/Subscription/"/>"+SubsriptionId,
            type : "DELETE"
        }).always(function()
        {
            location.reload();
        })
    });
});
</script>