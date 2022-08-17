/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.DAO.StudentDAO;

/**
 *
 * @author Admin
 */
public class SaveStudentServlet extends HttpServlet {

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
            String rollNo = request.getParameter("txtrollno");
            String major = request.getParameter("major");
            String fullName = request.getParameter("txtfullname");
            String city = request.getParameter("txtcity");
            int male = Integer.parseInt(request.getParameter("gender"));
            String patt = "";
            boolean check = true;

            if (StudentDAO.getStudentByID(rollNo) != null) {
                check = false;
                request.setAttribute("ERROR", "Duplicate ID");
            }
            
            if (fullName.matches("[0-9]+")) {
                check = false;
                request.setAttribute("ERROR", "Name can't contain number!");
            }

            if (major.equals("Engineering")) {
                if (rollNo.matches("(SE)(\\d{5})") == false) {
                    check = false;
                    request.setAttribute("ERROR", "Engineering rollno starts with SE (EX: SE00001");
                }
            } else if (major.equals("Business")) {
                if (rollNo.matches("(SB)(\\d{5})") == false) {
                    check = false;
                    request.setAttribute("ERROR", "Business rollno starts with SB (EX: SB00001)");
                }
            }
            
            

            if (check == false) {
                request.getRequestDispatcher("add.jsp").forward(request, response);
            } else {
                boolean res = StudentDAO.insertStudent(rollNo, major, fullName, city, male);
                if (res == true) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("error.jsp");
                }
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
