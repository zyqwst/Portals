package com.sy.ShowSy;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class ShowSyApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShowSyApplication.class);
        app.run(args);
    }

    @Bean
    public ServletRegistrationBean facesServletRegistraiton() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), new String[]{"*.xhtml"});
        registration.setName("Faces Servlet");
        registration.setLoadOnStartup(1);
        return registration;
    }


    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
        	servletContext.addListener("com.sun.faces.config.ConfigureListener");
            servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
            servletContext.setInitParameter("primefaces.FONT_AWESOME", "true");
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE","Development");
            servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
            servletContext.setInitParameter("primefaces.THEME", "barcelona-#{guestPreferences.layout}");
            servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/primefaces-barcelona.taglib.xml");
        };
    }
}
