/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.embedded;

import javax.servlet.ServletException;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcatEx {

    public static void main(String[] args) throws LifecycleException,
            InterruptedException,
            ServletException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(".");
	    tomcat.getConnector();

        //Context ctx = tomcat.addWebapp("/clusterbench", "/tmp/server/clusterbench.war");
        Context ctx, ctx2;

        String path = System.getProperty("java.io.tmpdir");
        if (path == null) {
            if (System.getProperty("os.name").contains("win")) {
                path = "C:\\tmp\\";
            } else {
                path = "/tmp/cxf-embedded-tomcat-toolset/";
            }
        }
        
        ctx = tomcat.addWebapp("/jaxrs-server", path + "jaxrs-server.war");
        ctx2 = tomcat.addWebapp("/jaxws-server", path + "jaxws-server.war");
        
        //ctx.addServletMapping("/*", "Embedded");

        tomcat.start();
        tomcat.getServer().await();
    }
}
