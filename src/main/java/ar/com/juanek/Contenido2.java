package ar.com.juanek;

import ar.com.juanek.block.BlockUIListener;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class Contenido2 extends Panel {
    public Contenido2(String id, IModel<SmartModel> model) {
        super(id, model);

        Form<SmartModel> form = new Form<>("form",model);
        form.add(new TextField<String>("nombre",new PropertyModel<>(model,"nombre")));
        form.add(new AjaxButton("aceptar") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("new Model "+Contenido2.this.getDefaultModel().getObject());
                //send(Contenido2.this.getParent(), Broadcast.EXACT, new MenuEvent(target,));
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                attributes.getAjaxCallListeners().add(new BlockUIListener());
            }
        });
        add(form);
    }


}
