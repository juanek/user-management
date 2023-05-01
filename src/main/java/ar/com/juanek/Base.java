package ar.com.juanek;

import ar.com.juanek.dao.RolesMapper;
import ar.com.juanek.dao.UserMapper;
import ar.com.juanek.security.Login;
import ar.com.juanek.util.MetaUtil;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.MetaDataHeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.shiro.ShiroConstraint;
import org.wicketstuff.shiro.annotation.ShiroSecurityConstraint;

@ShiroSecurityConstraint(constraint = ShiroConstraint.IsAuthenticated,loginPage = Login.class)
public class Base extends WebPage {

    private static final Logger log = LoggerFactory.getLogger( Base.class );

    @SpringBean
    UserMapper userMapper;

    @SpringBean
    RolesMapper rolesMapper;

    private UserModel smartModel;

    public Base(PageParameters parameters) {
        super(parameters);
        log.info("userMapper {}",userMapper);
        log.info("userMapper {}",userMapper.getUser("usuario1"));
        log.info("roles {}",rolesMapper.findAll());
        smartModel = new UserModel();
        smartModel.setNombre("Pedro");

        add(new Escritorio("escritorio",
                new PropertyModel<UserModel>(this, "smartModel")));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(new PriorityHeaderItem(
                new StringHeaderItem(MetaUtil.META_CHARSET)));
        response.render(new PriorityHeaderItem(
                new StringHeaderItem(MetaUtil.META_VIEWPORT)));
        response.render(new PriorityHeaderItem(
                new StringHeaderItem(MetaUtil.META_EDGE)));
        ResourceReference faviconRef =
                new PackageResourceReference(
                        getClass(), "favicon.ico");
        response.render(
                MetaDataHeaderItem.forLinkTag("shortcut icon",
                        urlFor(faviconRef, null).toString()));

    }
}
