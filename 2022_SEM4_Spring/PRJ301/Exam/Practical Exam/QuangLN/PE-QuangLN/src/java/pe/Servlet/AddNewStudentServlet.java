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
public class AddNewStudentServlet extends HttpServlet {

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
            String fullname = request.getParameter("txtfullname");
            String city = request.getParameter("txtcity");
            int gender = Integer.parseInt(request.getParameter("gender"));
            
            String pattern = "";
            if (major.equals("Engineering")) {
                pattern = "(SE)(\\d{5})";
                System.out.println("SE");
            } else if (major.equals("Business")) {
                pattern = "(SB)(\\d{5})";
                System.out.println("SB");
            } 
            
            if (!rollNo.matches(pattern)) {
                request.setAttribute("Error", "Engineering rollno starts with SE, Business rollno starts with SB");
                request.setAttribute("majorTest", major);
                request.getRequestDispatcher("add.jsp").forward(request, response);
            } 
            if (StudentDAO.getStudentByRollNo(rollNo) != null) {
                request.setAttribute("Error", "Duplicate roll no!");
                request.setAttribute("majorTest", major);
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
            boolean res = StudentDAO.insertStudent(rollNo, major, fullname, city, gender);
            System.out.println(res);
            if (res == false) {
                response.sendRedirect("error.jsp");
            } else {
                response.sendRedirect("index.jsp");
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
