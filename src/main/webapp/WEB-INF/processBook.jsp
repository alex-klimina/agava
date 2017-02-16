<%--
  Created by IntelliJ IDEA.
  User: alkli
  Date: 30/01/2017
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Processor</title>
</head>
<body>
    ${greeting}

    <form id="uploadBook" enctype="multipart/form-data" method="post" action="#">
        <input name="username" placeholder="alkli">
        <input name="fileupload" type="file" />
        <input type="submit" value="submit"/>
    </form>

    ${result}

</body>
</html>
