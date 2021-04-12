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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longvnt.tblQuestion.tblQuestionDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    private final String UPDATE_ERROR="index.html";
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
       boolean finderr=false;
        try {
            String id = request.getParameter("id");
            String question_content = request.getParameter("question_content");
            String a = request.getParameter("a");
            String b = request.getParameter("b");
            String c = request.getParameter("c");
            String d = request.getParameter("d");
            String answer_correct = request.getParameter("answer_correct");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
            String subjectID = request.getParameter("subjectID");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            
            HttpSession session = request.getSession();
            
            if (id.length()==0){
                session.setAttribute("ERRID", "not blank");
            }
            
            String searchValue = request.getParameter("lastSearchValue");
            tblQuestionDAO dao = new tblQuestionDAO();
            boolean result = dao.update(id, question_content, a, b, c, d, answer_correct, date, subjectID, status);
            String url = UPDATE_ERROR;
            if (result) {
                url = "search"
                        
                        + "?txtSearchValue=" + searchValue;
            }
            response.sendRedirect(url);

        } catch (SQLException ex) {
            log("SQL Exception at UpdateServlet: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Naming Exception at UpdateServlet: " + ex.getMessage());

        } catch (ParseException ex) {
            Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
