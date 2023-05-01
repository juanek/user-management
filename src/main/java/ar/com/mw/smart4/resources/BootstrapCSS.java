package ar.com.mw.smart4.resources;

import org.apache.wicket.request.resource.CssResourceReference;

public class BootstrapCSS extends CssResourceReference {

    public BootstrapCSS() {
        super(BootstrapCSS.class, "css/bootstrap.min.css");
    }
}
