<%-- 
    Document   : choosesubject
    Created on : Mar 18, 2021, 7:53:51 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Subject Page</title>
         <style> 
            h1{
                color: red;
                     text-align: center;
            }
            
            .timee{
                display: inline;
            }
            body{
                background:  url(https://wallpaperaccess.com/full/2384075.jpg);
            }
           .form{
                text-align: center;
                
            }
            
            
        </style>
    </head>
    <body>
        <c:import url="header.jsp">

        </c:import>
        <a href="historyS">Click here to view marks</a>
        <h1>Take Quiz!</h1>
        <form class="form" action="doquiz">
            Choose subject: 
            <select name="cbosubid">
                <c:forEach var="dto" items="${sessionScope.SUBLIST}">

                    <option  value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                </c:forEach>
            </select>
            <input type="submit" value="Start" name="btAction" />

        </form>



    </body>
</html>
