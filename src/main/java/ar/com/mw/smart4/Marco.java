package ar.com.mw.smart4;

import ar.com.mw.smart4.dao.UserMapper;
import ar.com.mw.smart4.security.Login;
import ar.com.mw.smart4.util.MetaUtil;
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
public class Marco extends WebPage {

    private static final Logger log = LoggerFactory.getLogger( Marco.class );

    @SpringBean
    UserMapper userMapper;
    private SmartModel smartModel;

    public Marco(PageParameters parameters) {
        super(parameters);
        log.info("userMapper {}",userMapper);
        log.info("userMapper {}",userMapper.getUser("admin"));
        smartModel = new SmartModel();
        smartModel.setNombre("Pedro");

        add(new Escritorio("escritorio",
                new PropertyModel<SmartModel>(this, "smartModel")));
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
