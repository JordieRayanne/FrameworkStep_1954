/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import etu1954.framework.annotation.MyUrl;

/**
 *
 * @author Legion
 */
public class Myclass {
     @MyUrl.MyURL(url = "/my-url")
    public void myMethod() {
        System.out.println("Hello from myMethod!");
    }    
}
