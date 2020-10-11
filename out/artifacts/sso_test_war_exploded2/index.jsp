<%--
  Created by IntelliJ IDEA.
  User: LiuWenze
  Date: 2020/9/28
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>login WebService</title>
</head>


<body>
<form method='post' action='login'>
  <input type='text' name='username'><br/>
  <input type='password' name='password'><br/>
  <input type="button" value="xxx" id="btn" onclick="javascript:window.location.href='http://www.baidu.com'">
  <input type="submit" value="login">
  <script>
    function test(){
      var url = "要跳转的路径";
      window.location.href= url;
    }
  </script>
</form>
</body>
</html>
