package ua.com.technozona.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                RootConfig.class,
                MultiHttpSecurityConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        final FilterRegistration.Dynamic encodingFilter = servletContext
                .addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

    }

    @Override
    protected DispatcherServlet createDispatcherServlet(final WebApplicationContext context) {
        final DispatcherServlet dispatcherServlet =
                (DispatcherServlet) super.createDispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }
}
