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
        <title>Update Quiz Page</title>
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
           
            
            
        </style>
    </head>
    <body>
        <h1>Update Quiz</h1>
        <div id="content">
            <form action="updateQuiz">

                Id: <input readonly="readonly" type="text" name="id" value="${param.id}" /> <br/>
                <c:if test="${requestScope.QUIZIDERR ne null}">
                    <font color="red">  ${requestScope.QUIZIDERR}  </font>  <br/>
                </c:if>

                Question content: <input type="text" name="question_content" value="${param.question_content}" size="100%" /> <br/>
                <c:if test="${requestScope.QUIZCONTENTERR ne null}">
                    <font color="red">  ${requestScope.QUIZCONTENTERR}  </font>  <br/>
                </c:if>

                A: <input type="text" name="a" value="${param.a}"  /> <br/>
                <c:if test="${requestScope.QUIZAERR ne null}">
                    <font color="red">  ${requestScope.QUIZAERR}  </font>  <br/>
                </c:if>

                B: <input type="text" name="b" value="${param.b}" /> <br/>
                <c:if test="${requestScope.QUIZBERR ne null}">
                    <font color="red">  ${requestScope.QUIZBERR}  </font>  <br/>
                </c:if>

                C: <input type="text" name="c" value="${param.c}"   /> <br/> 
                <c:if test="${requestScope.QUIZCERR ne null}">
                    <font color="red">  ${requestScope.QUIZCERR}  </font>  <br/>
                </c:if>

                D: <input type="text" name="d" value="${param.d}"   /> <br/> 
                <c:if test="${requestScope.QUIZDERR ne null}">
                    <font color="red">  ${requestScope.QUIZDERR}  </font>  <br/>
                </c:if>

                Answer correct:
                <select name="answer_correct">
                    <c:forEach var="dto" items="${sessionScope.ANSLIST}">

                        <c:if test="${dto.trim().toLowerCase() eq param.answer_correct.trim().toLowerCase()}">
                            <option selected="selected" value="${dto}"> ${dto} </option>
                            <c:set var="check" value="${dto}"/>
                            <c:set var="check2" value="${param.answer_correct}"/>
                        </c:if>
                        <c:if test="${dto ne param.answer_correct}">
                            <option  value="${dto}"> ${dto} </option>
                            <c:set var="check1" value="${dto}"/>
                        </c:if>
                        <c:set var="ooo" value="${dto}"/>
                        <c:set var="ooo1" value="${param.answer_correct}"/>


                    </c:forEach>
                </select> <br/>
                <c:if test="${requestScope.QUIZANSWERERR ne null}">
                    <font color="red">  ${requestScope.QUIZANSWERERR}  </font>  <br/>
                </c:if>
              
                Date create: <input type="date" name="date" value="${param.date}" /> <br/>
                <c:if test="${requestScope.QUIZDATEERR ne null}">
                    <font color="red">  ${requestScope.QUIZDATEERR}  </font>  <br/>
                </c:if>

                Subject ID: <select name="subjectID">

                    <c:forEach var="dto" items="${sessionScope.SUBLIST}">

                        <c:if test="${dto.getSubjectID() eq param.subjectID}">
                            <option selected="selected" value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                        </c:if>
                        <c:if test="${dto.getSubjectID() ne param.subjectID}">
                            <option  value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                        </c:if>



                    </c:forEach>

                </select> <br/>
                status  <select name="status">

                    <c:if test="${'true'.trim().toLowerCase() eq param.status.trim().toLowerCase()}">
                        <option selected="selected" value="true">true</option>
                    </c:if>
                    <c:if test="${'true'.trim().toLowerCase() ne param.status.trim().toLowerCase()}">
                        <option value="true">true</option>
                    </c:if>

                    <c:if test="${'false'.trim().toLowerCase() eq param.status.trim().toLowerCase()}">
                        <option selected="selected" value="false">false</option>
                    </c:if>
                    <c:if test="${'false'.trim().toLowerCase() ne param.status.trim().toLowerCase()}">
                        <option value="false">false</option>
                    </c:if>



                </select> <br/>
                <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
                <input type="hidden" name="lastcbosubjectID" value="${param.lastcbosubjectID}" />
                <input type="hidden" name="lastcbostatus" value="${param.lastcbostatus}" />
                <input type="submit" value="Update" name="btAction" />
            </form>


        </div>
    </body>
</html>
