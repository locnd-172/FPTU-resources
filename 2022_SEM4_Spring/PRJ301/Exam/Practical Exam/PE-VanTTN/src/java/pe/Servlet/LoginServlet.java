/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.DAO.UserDAO;
import pe.DTO.User;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String fullname = request.getParameter("txtfullname");
            String password = request.getParameter("txtpassword");

            User user = null;
            try {
                user = UserDAO.getUser(fullname, password);
                out.println(user);
                if (user != null) {
                    if (user.getRoleID() == 1) {
                        // chuyen qua admin page
                        HttpSession session = request.getSession(true);
                        if (session != null) {
                            session.setAttribute("fullname", user.getFullname());
                            session.setAttribute("userID", user.getUserID());
                            response.sendRedirect("admin.jsp");
                        }
                    } else {
                        // chuyen qua welcome homepage
                        HttpSession session = request.getSession(true);
                        if (session != null) {
                            session.setAttribute("fullname", user.getFullname());
                            session.setAttribute("userID", user.getUserID());
                            response.sendRedirect("index.jsp");
                        }
                    }
                } else {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("fullname", fullname);
                    session.setAttribute("userID", user.getUserID());
                    request.setAttribute("ERROR", "Wrong fullname or password!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
