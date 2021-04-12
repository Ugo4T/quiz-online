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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longvnt.tblAccount.tblAccountDAO;
import longvnt.tblSubject.tblSubjectDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "login.jsp";
    private final String SEARCH_PAGE = "search.jsp";
    private final String CHOOSESUBJECT_PAGE = "choosesubject.jsp";

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
        String url = INVALID_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            tblAccountDAO dao = new tblAccountDAO();
            boolean result = dao.checkLogin(username, password);
            tblSubjectDAO subdao= new tblSubjectDAO();
            subdao.getSubject();
            if (result) {
                url = SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("ISADMIN", dao.getRole(username, password));
                session.setAttribute("FULLNAME", dao.getFullname(username, password));
                session.setAttribute("EMAIL",username);
                session.setAttribute("SUBLIST", subdao.getList());
                List<String> list = new ArrayList<String>();
                list.add("A");
                list.add("B");
                list.add("C");
                list.add("D");
                session.setAttribute("ANSLIST", list);
                if (dao.getRole(username, password)==false){
                    url=CHOOSESUBJECT_PAGE;
                }
            } else {
                request.setAttribute("INVALID", "incorrect username or password");
            }
            
        } catch (SQLException ex) {
            log("SQL ERROR in LoginServlet :" + ex.getMessage());
        } catch (NamingException ex) {
            log("Naming ERROR in LoginServlet :" + ex.getMessage());
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
