<%--
  Created by IntelliJ IDEA.
  User: alkli
  Date: 30/01/2017
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book Processor</title>
</head>
<body>

<p align="center">AGAVA</p>
<br>

<form id="uploadBook" enctype="multipart/form-data" method="post" action="#">
    <table align="center">
        <tr>
            <td>User:</td>
            <td>
                <input name="username" placeholder="alkli">
            </td>
        </tr>
        <tr>
            <td>File witn text:</td>
            <td><input name="fileupload" type="file"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="submit"/></td>
        </tr>
    </table>
</form>

${resultText}
<br>
<br>
<c:forEach items="${resultHints}" var="entry">
    <b>${entry.key}&nbsp;</b>
    ${entry.value}<br>
</c:forEach>

</body>
</html>
