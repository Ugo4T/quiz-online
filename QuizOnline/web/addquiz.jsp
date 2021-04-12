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
        <h1>Create Quiz</h1>
        <div id="content">
            <form action="addQuiz">
                Id: <input type="text" name="id" value="${param.id}" /> <br/>
                <c:if test="${requestScope.QUIZIDERR ne null}">
                    <font color="red">  ${requestScope.QUIZIDERR}  </font>  <br/>
                </c:if>
                    
                    Question content: <input type="text" name="question_content" value="${param.question_content}" size="100%"/> <br/>
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

                Answer correct 
                <select name="answer_correct">
                     <c:forEach var="dto" items="${sessionScope.ANSLIST}">

                        <c:if test="${dto eq param.answer_correct}">
                            <option selected="selected" value="${dto}"> ${dto} </option>
                        </c:if>
                        <c:if test="${dto ne param.answer_correct}">
                            <option  value="${dto}"> ${dto} </option>
                        </c:if>



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

                    <c:if test="${'true' eq param.status}">
                        <option selected="selected" value="true">true</option>
                    </c:if>
                    <c:if test="${'true' ne param.status}">
                        <option value="true">true</option>
                    </c:if>

                    <c:if test="${'false' eq param.status}">
                        <option selected="selected" value="false">false</option>
                    </c:if>
                    <c:if test="${'false' ne param.status}">
                        <option value="false">false</option>
                    </c:if>

                  

                </select> <br/>
                <input type="submit" value="Add" name="btAction" />
            </form>


        </div>
    </body>
</html>
