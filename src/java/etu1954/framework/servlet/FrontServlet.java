/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1954.framework.servlet;

import etu1954.framework.annotation.MyUrl.MyURL;
import etu1954.framework.Mapping;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

/**
 *
 * @author ITU
 */
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;
    
 public void init(ServletConfig config) throws ServletException {
        super.init(config);
        HashMap<String,Mapping> mappingUrls = this.getMappings();
        this.setMappingUrls(mappingUrls);
    }
    
    public void setMappingUrls(HashMap<String, Mapping> mappingUrls) {
        this.mappingUrls = mappingUrls;
    }

    public HashMap<String, Mapping> getMappingUrls() {
        return this.mappingUrls;
    }


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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + request.getContextPath() +request.getServletPath()+ "?" +request.getQueryString()+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
          response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Liste des mappings :</h1>");
            for (Map.Entry<String, Mapping> entry : mappingUrls.entrySet()) {
               out.println("<p>URL: " + entry.getKey() + "</p>");
               out.println("<p>Class: " + entry.getValue().getClassName() + "</p>");
               out.println("<p>Method: " + entry.getValue().getMethod() + "</p><br>");
            }
            out.println("</body></html>");
    }
    
    public static HashMap<String, Mapping> getMappings() {
       HashMap<String, Mapping> mappingUrls = new HashMap<>();
       Reflections reflections = new Reflections("test_models.Models", new MethodAnnotationsScanner());
       Set<Method> methods = reflections.getMethodsAnnotatedWith(MyURL.class);
       for (Method method : methods) {
           Mapping tempMapping = new Mapping();
           tempMapping.setClassName(method.getDeclaringClass().getSimpleName());
           tempMapping.setMethod(method.getName());

           MyURL url = method.getAnnotation(MyURL.class);
           String key = url.url();
           mappingUrls.put(key, tempMapping);
       }
       return mappingUrls;
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
