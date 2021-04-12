<%-- 
    Document   : history
    Created on : Mar 19, 2021, 9:35:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <style> 
            h1{
                color: red;
            }
            
            .timee{
                display: inline;
            }
            body{
                background:  url(https://wallpaperaccess.com/full/2384075.jpg);
            }          
            .page{
                text-align: center;
            }
            .pageform{
                text-align: center;
            }
            .tblhis{
                text-align: center;
                width: 100%;
                background: white;
            }
            .tbl{
                text-align: center;
            }
        </style>
    </head>
    <body>

        <c:import url="header.jsp">

        </c:import>
        <div class="page">
            <h1>History Page</h1>
        <a href="choosesubject">Click here to return Home page</a>
        </div>
        

        <form class="pageform" action="searchhistory">
            Choose subject: 
            <select name="cbosubid">
                <c:forEach var="dto" items="${sessionScope.SUBLIST}">
                    <c:if test="${dto.getSubjectID() eq param.cbosubid}">
                        <option selected="selected" value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                    </c:if>
                    <c:if test="${dto.getSubjectID() ne param.cbosubid}">
                        <option  value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                    </c:if>

                </c:forEach>
            </select>


            <input type="submit" value="Search" name="btAction"/> <br/>
        </form>
        <c:set var="searchValue" value="${param.cbosubid}"/>

        <div class="tbl">
              <c:if test="${not empty searchValue}">
            <c:set var="result" value="${sessionScope.SEARCHHISTORYLIST}"/>
            <c:if test="${not empty result}">
                <table class="tblhis" border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Subject</th>
                            <th>Mark</th>


                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">

                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.getSubjectID()}</td>
                                <td>${dto.getMark()}</td>


                            </tr>

                        </c:forEach>

                    </tbody>
                </table>

            </c:if>
            <c:if test="${empty result}">
                <h2>no record is matched!!! 
                </h2>
            </c:if>
        </c:if>
        </div>
      

    </body>
</html>
