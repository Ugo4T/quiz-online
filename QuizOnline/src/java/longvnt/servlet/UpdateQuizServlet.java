/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longvnt.tblQuestion.tblQuestionDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateQuizServlet", urlPatterns = {"/UpdateQuizServlet"})
public class UpdateQuizServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = null;
        boolean finderr = false;
        try {
            String id = request.getParameter("id");
            String question_content = request.getParameter("question_content");
            String a = request.getParameter("a");
            String b = request.getParameter("b");
            String c = request.getParameter("c");
            String d = request.getParameter("d");
            String answer_correct = request.getParameter("answer_correct");

            String[] day = request.getParameter("date").split("/");
            Date date = null;

            if (day[0].length() == 0) {
                request.setAttribute("QUIZDATEERR", "not blank");
                finderr = true;
            } else {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            }
            String subjectID = request.getParameter("subjectID");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            tblQuestionDAO dao = new tblQuestionDAO();

            if (id.length() == 0) {
                request.setAttribute("QUIZIDERR", "not blank");
                finderr = true;

            }
            if (question_content.length() == 0) {
                request.setAttribute("QUIZCONTENTERR", "not blank");
                finderr = true;

            }
            if (a.length() == 0) {
                request.setAttribute("QUIZAERR", "not blank");
                finderr = true;

            }
            if (b.length() == 0) {
                request.setAttribute("QUIZBERR", "not blank");
                finderr = true;

            }
            if (c.length() == 0) {
                request.setAttribute("QUIZCERR", "not blank");
                finderr = true;

            }
            if (d.length() == 0) {
                request.setAttribute("QUIZDERR", "not blank");
                finderr = true;

            }
            if (answer_correct.length() == 0) {
                request.setAttribute("QUIZANSWERERR", "not blank");
                finderr = true;

            }
            if (finderr == false) {

                boolean result = dao.update(id, question_content, a, b, c, d, answer_correct, date, subjectID, status);
                String lastsearch = request.getParameter("lastSearchValue");
                String lastsubid = request.getParameter("lastcbosubjectID");
                String laststatus = request.getParameter("lastcbostatus");
                if (result) {
                    url = "search?txtSearchValue=" + lastsearch
                            + "&cbosubjectID=" + lastsubid
                            + "&cbostatus=" + laststatus;
                }

            } else {
                url = "search.jsp";
            }

        } catch (ParseException ex) {
            Logger.getLogger(AddQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(AddQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
//          RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
