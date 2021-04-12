<%-- 
    Document   : showquiz
    Created on : Mar 18, 2021, 8:16:41 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <title>Do Quiz Page</title>
        <style> 
            h1{
                color: red;
                text-align: center;
                font-size: 75px
            }

            .timee{
                display: inline;
            }
            .subid{
                text-align: center;
                font-size: 50px
            }
            body{
                background:  url(https://wallpaperaccess.com/full/2384075.jpg);
            }
        </style>
    </head>
    <body>
        <c:import url="header.jsp">

        </c:import>
        <h1>Quiz Online</h1>
        <c:set var="time" value="${sessionScope.TIMEDO}" />
        <div class="subid">
            Subject: ${sessionScope.SUBID}
        </div>

        <form id="quiz"  action="process">

            <div id="gio">
                Time remaining: 
                <div class="timee" display="inline" id="minutes"></div>:
                <div class="timee" display="inline" id="seconds"></div>
            </div>
            <p>Question ${sessionScope.PAGE}</p>
            ${sessionScope.QUIZLIST.get(sessionScope.PAGE-1).getQuestioncontent()} <br/>
            A: ${sessionScope.QUIZLIST.get(sessionScope.PAGE-1).getA()} <br/>
            B: ${sessionScope.QUIZLIST.get(sessionScope.PAGE-1).getB()} <br/>
            C: ${sessionScope.QUIZLIST.get(sessionScope.PAGE-1).getC()} <br/>
            D: ${sessionScope.QUIZLIST.get(sessionScope.PAGE-1).getD()} <br/>
            Answer: 
            <select name="answer">
                <c:forEach var="dto" items="${sessionScope.ANSLIST}">
                    <c:if test="${sessionScope.ANSWERLIST.get(sessionScope.PAGE-1) eq dto}">
                        <option selected="selected" value="${dto}"> ${dto} </option>
                    </c:if>
                    <c:if test="${sessionScope.ANSWERLIST.get(sessionScope.PAGE-1) ne dto}">
                        <option  value="${dto}"> ${dto} </option>
                    </c:if>

                </c:forEach>

            </select> <br/>
            <c:set var="page" value="${sessionScope.PAGE}" />
            <c:if test="${(page > 1) }" >
                <input  type="submit" value="Previous" name="btAction" />
            </c:if>

            <input type="text" name="txtPage" value=" ${sessionScope.PAGE}" readonly="readonly" />
            <c:if test="${ (page < sessionScope.QUIZLIST.size())}" >
                <input  type="submit" value="Next" name="btAction"/>
            </c:if>


            <input type="submit" value="Submit" name="btAction" />

        </form>


    </body>
    <script>
        (function () {
            const second = 1000,
                    minute = second * 60,
                    hour = minute * 60,
                    day = hour * 24;
            let end = "${time}",
                    countDown = new Date(end).getTime();
            setInterval(function () {
                let now = new Date().getTime(),
                        distance = countDown - now;

                document.getElementById("minutes").innerText = Math.floor(distance / (minute)),
                        document.getElementById("seconds").innerText = Math.floor((distance % (minute)) / second);

                if (distance < 1) {
                    var form = document.getElementById("quiz");
                    form.submit();
                    return;
                }

            }, 100);
        }());
    </script>  
</html>
