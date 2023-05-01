package ar.com.juanek.config;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class SmartInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //spring listener required
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.scan("ar.com.juanek");
        context.refresh();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(RequestContextListener.class);

        //shiro configuration
        //ShiroFilter must be declared in @Configuration
        FilterRegistration.Dynamic shFilterRegistration = servletContext.addFilter("ShiroFilter", DelegatingFilterProxy.class);
        shFilterRegistration.setInitParameter("targetFilterLifecycle", "true");
        shFilterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.ERROR, DispatcherType.REQUEST), false, "/*");

        //wicket
        FilterRegistration wicketFilterReg = servletContext.addFilter("WicketFilter", WicketFilter.class);
        wicketFilterReg.setInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        wicketFilterReg.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        wicketFilterReg.addMappingForUrlPatterns(null, false, "/*");

    }
}
