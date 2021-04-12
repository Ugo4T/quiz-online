<%-- 
    Document   : addquiz
    Created on : Mar 14, 2021, 9:50:34 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create User</h1>

        <form action="signup">
            Email: <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            <c:if test="${requestScope.EMAILERROR ne null}">
                <font color="red">  ${requestScope.EMAILERROR}  </font>  <br/>
            </c:if>

            Password: <input type="text" name="txtPassword" value="${param.txtPassword}" /><br/>
            <c:if test="${requestScope.PASSWORDERROR ne null}">
                <font color="red">  ${requestScope.PASSWORDERROR}  </font>  <br/>
            </c:if>
                
            Name: <input type="text" name="txtName" value="${param.txtName}" /><br/>
            <c:if test="${requestScope.NAMEERROR ne null}">
                <font color="red">  ${requestScope.NAMEERROR}  </font>  <br/>
            </c:if>
            <input type="submit" value="Create" name="btAction" />
            <input type="reset" value="Reset" />
        </form>

    </body>
</html>
