package ar.com.mw.smart4;

import ar.com.mw.smart4.event.MenuEvent;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.wicketstuff.shiro.page.LogoutPage;

public class BarraEstado extends Panel {
    public BarraEstado(String id, IModel<SmartModel> model) {
        super(id, model);


        add(new AjaxLink<Void>("link1") {

            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                System.out.println("link1");
                System.out.println("model AjaxLink1 "+((SmartModel)BarraEstado.this.getDefaultModel().getObject()).getNombre());
                SmartModel smartModel = ((SmartModel)BarraEstado.this.getDefaultModel().getObject());
                smartModel.setNombre("Maria");
                //BarraEstado.this.modelChanged();

                send(BarraEstado.this.getParent(), Broadcast.EXACT,new MenuEvent(ajaxRequestTarget, Contenido1.class));
            }
        });

        add(new AjaxLink<Void>("link2") {

            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                System.out.println("link2");
                System.out.println("model AjaxLink2 "+((SmartModel)BarraEstado.this.getDefaultModel().getObject()).getNombre());
                SmartModel smartModel = ((SmartModel)BarraEstado.this.getDefaultModel().getObject());
                send(BarraEstado.this.getParent(), Broadcast.EXACT,new MenuEvent(ajaxRequestTarget, Contenido2.class));
            }
        });

        add(new BookmarkablePageLink<Void>("logout",LogoutPage.class));
    }
}
