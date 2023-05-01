package ar.com.mw.smart4.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

public abstract class MenuAjaxLink<T> extends AjaxLink<T> {

    public MenuAjaxLink(String id) {
        super(id);
    }

    public MenuAjaxLink(String id, IModel<T> model) {
        super(id, model);
    }

}
