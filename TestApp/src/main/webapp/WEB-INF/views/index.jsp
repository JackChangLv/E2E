<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Home</title>
<style>
input:not([type='submit']) {
  width: 300%;
}
</style>
</head>
<body>
<br>
<h1>Notification Services E2E Test App</h1>
<br>
<form action="<c:url value="/"/>" method="post">
    <table>
        <tr>
              <td>Subscription URI</td>
              <td><input type="text" name="Uri" placeholder="localhost:8282"></td>
          </tr>
          <tr>
              <td>Consumer Key</td>
              <td><input type="text" name="Key"></td>
          </tr>
          <tr>
              <td>Consumer Secret</td>
              <td><input type="password" name="Secret"></td>
          </tr>
    </table>
    <input type="submit" value="Enter">
</form>
</body>
</html>
