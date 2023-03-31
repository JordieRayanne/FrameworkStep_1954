/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1954.framework.controller;

import etu1954.framework.Modelview;
import etu1954.framework.annotation.MyUrl;

/**
 *
 * @author Legion
 */
public class Myclass {
     public Myclass() {
    }
     @MyUrl.MyURL(url = "/my-url")
    public Modelview myMethod() {
        Modelview view=new Modelview();
        view.setView("tesita.jsp");
        return view;
        //System.out.println("Hello from myMethod!");
    }      
}
