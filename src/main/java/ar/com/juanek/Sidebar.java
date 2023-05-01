package ar.com.juanek;

import ar.com.juanek.block.BlockUIListener;
import ar.com.juanek.event.MenuEvent;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class Sidebar extends Panel {
    public Sidebar(String id, IModel<?> model) {
        super(id, model);
        setMarkupId("sidebar-wrapper");
        add(new AjaxLink<Void>("link1") {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                send(Sidebar.this.getParent(), Broadcast.EXACT,new MenuEvent(ajaxRequestTarget, Contenido2.class));
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                attributes.getAjaxCallListeners().add(new BlockUIListener("Abriendo Contenido2 ..."));
            }
        });
    }
}
