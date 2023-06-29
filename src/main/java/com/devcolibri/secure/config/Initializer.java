package com.devcolibri.secure.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//указываем сервлету на веб конфиг
        return new Class[] {WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {//указываем сервлету путь к приложению
        return new String[] {"/"};
    }
}