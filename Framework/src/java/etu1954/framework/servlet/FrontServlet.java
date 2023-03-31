/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1954.framework.servlet;

import etu1954.framework.annotation.MyUrl.MyURL;
import etu1954.framework.Mapping;
import etu1954.framework.Modelview;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
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
        HashMap<String, Mapping> mappingUrls = this.getMappings();
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
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String current = request.getRequestURI().replace(request.getContextPath(), "");
            response.getWriter().println("Current URI: " + current); // Débogage

            if (mappingUrls.containsKey(current)) {
                Mapping mapp = mappingUrls.get(current);
                String className = mapp.getClassName();
                String packageName = "etu1954.framework.controller";
                String fullClassName = packageName + "." + className;

                response.getWriter().println("Class name: " + className); // Débogage

                Object obj = Class.forName(fullClassName).getConstructor().newInstance();
                System.out.println("Class name: " + obj.getClass().getName()); // Afficher le nom de la classe
                System.out.println("Method name: " + mapp.getMethod()); // Afficher le nom de la méthode
                Modelview model = (Modelview) obj.getClass().getMethod(mapp.getMethod()).invoke(obj);
                System.out.println("View name: " + model.getView()); // Afficher le nom de la vue
                RequestDispatcher disp = request.getRequestDispatcher(model.getView());
                disp.forward(request, response);
            }
        } catch (

        Exception e) {
            e.printStackTrace();
            // Afficher un message d'erreur à l'utilisateur
            response.getWriter().println("Une erreur est survenue : " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
       }

    public static HashMap<String, Mapping> getMappings() {
        HashMap<String, Mapping> mappingUrls = new HashMap<>();
        Reflections reflections = new Reflections("etu1954.framework.controller", new MethodAnnotationsScanner());
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
