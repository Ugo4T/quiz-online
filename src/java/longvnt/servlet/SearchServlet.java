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
import java.util.List;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    private final String SEARCH_PAGE = "search.jsp";
    private final String SHOW_PAGE = "search.jsp";
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
         String url = SEARCH_PAGE;
            PrintWriter out = response.getWriter();
        
        String searchValue = request.getParameter("txtSearchValue");
        String subjectID = request.getParameter("cbosubjectID");
        String status = request.getParameter("cbostatus");
        try {
           
           
                tblQuestionDAO dao = new tblQuestionDAO();
                dao.searchName();
                List<tblQuestionDTO> result = dao.getList();
         //         System.out.println(result.size());
                List<tblQuestionDTO> result1 = new ArrayList<>();
                if (searchValue.trim().length()==0){
                    result1=result;
                }
                else
                for (tblQuestionDTO dto : result){
                    if (dto.getQuestioncontent().contains(searchValue))
                        result1.add(dto);
                }
        //        System.out.println(result1.size());
                //////
                 List<tblQuestionDTO> result2 = new ArrayList<>();
                if (subjectID.equalsIgnoreCase("all")){
                    result2=result1;
                }
                else
                for (tblQuestionDTO dto : result1){
                    if (dto.getSubjectID().equalsIgnoreCase(subjectID))
                        result2.add(dto);
                }
           //       System.out.println(result2.size());
                ////
                 List<tblQuestionDTO> result3 = new ArrayList<>();
                if (status.equalsIgnoreCase("all")){
                    result3=result2;
                }
                else
                for (tblQuestionDTO dto : result2){
                    if ((dto.isStatus()+"").equalsIgnoreCase(status))
                        result3.add(dto);
                }
               //   System.out.println(result3.size());
                ///
                request.setAttribute("SEARCHRESULT", result3);
                
                
                
                
                url = SHOW_PAGE;
          
        } catch (SQLException ex) {
            log("SQLException in SearchServlet " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException in SearchServlet " + ex.getMessage());
        }  finally {
            
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
