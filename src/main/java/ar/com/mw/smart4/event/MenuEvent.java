package ar.com.mw.smart4.event;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class MenuEvent {
    private final AjaxRequestTarget target;

    private final Class panelClass;

    public MenuEvent(AjaxRequestTarget target, Class panelClass) {
        this.target = target;
        this.panelClass = panelClass;
    }

    public AjaxRequestTarget getTarget() {
        return target;
    }

    public Class getPanelClass() {
        return panelClass;
    }
}
