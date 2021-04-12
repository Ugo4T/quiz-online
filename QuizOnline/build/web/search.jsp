<%-- 
    Document   : search
    Created on : Mar 13, 2021, 4:28:21 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Online Page</title>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#keywords').dataTable({
                    "ordering": false,
                    "searching": false,
                    "aLengthMenu": [[5], [5]], // danh sách số trang trên 1 lần hiển thị bảng
                });

            });
        </script>
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <h1>QUIZ MANAGEMENT</h1> 
            <br/>

            <form action="search">
                <p>Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /></p> <br/>


            Subject ID: <select name="cbosubjectID">
                <option  value="all">All </option>
                <c:forEach var="dto" items="${sessionScope.SUBLIST}">
                    <c:if test="${dto.getSubjectID() eq param.cbosubjectID}">
                        <option selected="selected" value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                    </c:if>

                    <c:if test="${dto.getSubjectID() ne param.cbosubjectID}">
                        <option  value="${dto.getSubjectID()}"> ${dto.getSubjectID()} </option>
                    </c:if>


                </c:forEach>

            </select> <br/>


            Status  <select name="cbostatus">
                <c:if test="${'all' eq param.cbostatus}">
                    <option selected="selected" value="all">All</option>
                </c:if>
                <c:if test="${'all' ne param.cbostatus}">
                    <option  value="all">All</option>
                </c:if>

                <c:if test="${'true' eq param.cbostatus}">
                    <option selected="selected"  value="true">true</option>
                </c:if>
                <c:if test="${'true' ne param.cbostatus}">
                    <option  value="true">true</option>
                </c:if>

                <c:if test="${'false' eq param.cbostatus}">
                    <option selected="selected"  value="false">false</option>
                </c:if>
                <c:if test="${'false' ne param.cbostatus}">
                    <option  value="false">false</option>
                </c:if>



            </select> <br/>







            <input type="submit" value="Search" name="btAction" /><br/>


        </form> <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>

        <c:set var="result" value="${requestScope.SEARCHRESULT}"/>

        <c:if test="${not empty result}">
            <table id="keywords" border="1" style="width: 100%">
                <thead>
                    <tr>
                        <th>ID.</th>
                        <th>Question Content</th>
                        <th>A</th>
                        <th>B</th>
                        <th>C</th>
                        <th>D</th>
                        <th>Correct Answer</th>
                        <th>Date create</th>
                        <th>Subject</th>

                        <th>status</th>
                        <th>Delete</th>
                        <th>Update</th>



                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" >
                    <form action="update">
                        <tr>

                            <td>${dto.id}</td>
                        <input type="hidden" name="id" value="${dto.id}" /> 
                        <td><input type="text" name="question_content" value="${dto.questioncontent}" /> </td>
                        <td><input type="text" name="a" value="${dto.a}" /> </td>
                        <td><input type="text" name="b" value="${dto.b}" /> </td>
                        <td><input type="text" name="c" value="${dto.c}" /> </td>
                        <td><input type="text" name="d" value="${dto.d}" /> </td>
                        <td><input type="text" name="answer_correct" value="${dto.answer_correct}" /> </td>
                        <td><input type="text" name="date" value="${dto.date}" /> </td>
                        <td><input type="text" name="subjectID" value="${dto.subjectID}" /> </td>
                        <td>
                            <input type="text" name="status" value=" ${dto.status}" />
                        </td>



                        <c:if test="${sessionScope.ISADMIN}">

                            <td>

                                <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                <input type="hidden" name="lastcbosubjectID" value="${param.cbosubjectID}" />
                                <input type="hidden" name="lastcbostatus" value="${param.cbostatus}" />

                                <input type="submit" value="Update" name="btAction"  />




                            </td>

                            <td>



                                <c:url var="urlRewriting" value="delete">

                                    <c:param name="pk" value="${dto.id}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>                                        
                                    <c:param name="lastcbostatus" value="${param.cbostatus}"/>                                        
                                    <c:param name="lastcbosubjectID" value="${param.cbosubjectID}"/>                                        
                                </c:url>

                                <a href="${urlRewriting}" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>



                            </td>

                        </c:if>


                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>


        

    </c:if>
    <form action="addQ">
        <input type="submit" value="Add Quiz" name="btAction" />
    </form>
    <c:if test="${param.cboCategory != null}">
        <c:if test="${empty result}">
            <h2>no record is matched!!! 
            </h2>
        </c:if>

    </c:if>  

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    
</body>
</html>
