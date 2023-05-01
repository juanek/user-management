package ar.com.mw.smart4.resources;

import ar.com.mw.smart4.SmartApplication;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.ArrayList;
import java.util.List;

public class BoostrapJS extends JavaScriptResourceReference {
    public BoostrapJS() {
        super(BoostrapJS.class,"js/bootstrap.bundle.min.js");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> items = new ArrayList<>();
        items.add(JavaScriptHeaderItem.forReference(SmartApplication.get().getJavaScriptLibrarySettings().getJQueryReference()));
        return items;
    }
}
