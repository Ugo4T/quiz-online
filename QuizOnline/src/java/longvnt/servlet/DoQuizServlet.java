/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longvnt.tblQuestion.tblQuestionDAO;
import longvnt.tblQuestion.tblQuestionDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "DoQuizServlet", urlPatterns = {"/DoQuizServlet"})
public class DoQuizServlet extends HttpServlet {

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
       
        String subid = request.getParameter("cbosubid");
        try {
            tblQuestionDAO dao = new tblQuestionDAO();
            dao.searchName();
            ArrayList<tblQuestionDTO> startlist = (ArrayList<tblQuestionDTO>) dao.getList();
            ArrayList<tblQuestionDTO> list = new ArrayList<>();
            for (tblQuestionDTO dto : startlist) {
                if (dto.isStatus() == true) {
                    list.add(dto);
                }
            }

            ArrayList<tblQuestionDTO> Quizlist = new ArrayList<>();
            ArrayList<tblQuestionDTO> Quizlist1 = new ArrayList<>();
           
            ArrayList Anss = new ArrayList();
            Random random = new Random();
            for (tblQuestionDTO dto : list) {
                if (dto.getSubjectID().equals(subid)) {
                    Quizlist.add(dto);
                }
            }

            int n = Quizlist.size();

            if (n > 10) {
                n = 10;
            }

            for (int i = 0; i < n; i++) {
                int value = random.nextInt(Quizlist.size());
                Quizlist1.add(Quizlist.get(value));
                Quizlist.remove(value);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("QUIZLIST", Quizlist1);
            for (int i=0; i< n ;i++){
                Anss.add("E");
            }
            
            session.setAttribute("ANSWERLIST", Anss);
            session.setAttribute("SUBID", subid);
            session.setAttribute("PAGE", 1);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = now.plusSeconds(60);
            session.setAttribute("TIMEDO", end);
           

        } catch (SQLException ex) {
            Logger.getLogger(DoQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(DoQuizServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("showquiz.jsp");
            rd.forward(request, response);
          
        
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
