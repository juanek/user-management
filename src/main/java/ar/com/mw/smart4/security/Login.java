package ar.com.mw.smart4.security;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Login extends WebPage {

    public Login(PageParameters parameters) {
        super(parameters);
        add(new SmartLogin("login",false));
    }
}
