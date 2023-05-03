package ar.com.juanek;

import ar.com.juanek.security.Login;
import ar.com.juanek.security.NoAutorizado;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.RequestCycleSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.wicketstuff.shiro.annotation.AnnotationsShiroAuthorizationStrategy;
import org.wicketstuff.shiro.authz.ShiroUnauthorizedComponentListener;
import org.wicketstuff.shiro.page.LogoutPage;
@Component
public class UserApplication extends WebApplication {

    private static final Logger log = LoggerFactory.getLogger(UserApplication.class);

    @Override
    public Class<? extends Page> getHomePage() {
        return Base.class;
    }

    @Override
    protected void init() {
        super.init();
        getCspSettings().blocking().disabled();
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getMarkupSettings().setStripComments(true);
        getMarkupSettings().setStripWicketTags(true);

        // Configure Shiro
        AnnotationsShiroAuthorizationStrategy authz = new AnnotationsShiroAuthorizationStrategy();
        getSecuritySettings().setAuthorizationStrategy(authz);
        getSecuritySettings().setUnauthorizedComponentInstantiationListener(
                new ShiroUnauthorizedComponentListener(Login.class, NoAutorizado.class, authz));

        mountPage("/login", Login.class);
        mountPage("/logout", LogoutPage.class);
        //la monté en app para evitar faviicon.ico al comienzo de la url
        mountPage("/app", Base.class);
        mountPage("/aut", Autorizada.class);


        getComponentInstantiationListeners().add(new SpringComponentInjector(this, context(), true));


        //revisar
        getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    }

    public ApplicationContext context() {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }
}
