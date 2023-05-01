package ar.com.mw.smart4.resources;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;

import java.util.ArrayList;
import java.util.List;

public class EscritorioCSS extends CssResourceReference {

    public EscritorioCSS() {
        super(EscritorioCSS.class, "css/escritorio.css");
    }

    @Override
    public List<HeaderItem> getDependencies() {
        List<HeaderItem> items = new ArrayList<>();
        items.add(CssHeaderItem.forUrl("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"));
        items.add(CssHeaderItem.forReference(new BootstrapCSS()));
       // items.add(CssHeaderItem.forReference(new EscritorioCSS()));
        return items;
    }
}
