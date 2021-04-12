/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvnt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import longvnt.tblMarks.tblMarksDAO;
import longvnt.tblQuestion.tblQuestionDTO;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProcessServlet", urlPatterns = {"/ProcessServlet"})
public class ProcessServlet extends HttpServlet {

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
        String ans = request.getParameter("answer");
        String button = request.getParameter("btAction");
        String url = "showquiz.jsp";
        try {
            HttpSession session = request.getSession();
            ArrayList anss = (ArrayList) session.getAttribute("ANSWERLIST");

            int page = (int) session.getAttribute("PAGE");
            anss.set(page - 1, ans);
            if ("Next".equals(button)) {
                page++;
            } else if ("Previous".equals(button)) {
                page--;
            } else {
                int i = 0;
                int dem = 0;
                ArrayList<tblQuestionDTO> da = (ArrayList<tblQuestionDTO>) session.getAttribute("QUIZLIST");
                for (tblQuestionDTO dto : da) {
                    if (dto.getAnswer_correct().trim().equals(anss.get(i))) {
                        dem++;
                    }
                    
                    i++;
                    

                }
                float mark =(float) Math.round(dem * 1000 / da.size())/100 ;
               
                session.setAttribute("MARK",mark);
                tblMarksDAO mdao = new tblMarksDAO();
                String email = (String) session.getAttribute("EMAIL");
                String subid = (String) session.getAttribute("SUBID");
                mdao.createHis(email, subid, mark);
                url = "mark.jsp";
            }
            session.setAttribute("PAGE", page);

        } catch (SQLException ex) {
            Logger.getLogger(ProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ProcessServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
