package ar.com.juanek;

import ar.com.juanek.component.dialog.ModalWindow;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class Contenido1 extends Panel {

    private String result;
    public Contenido1(String id, IModel<?> model) {
        super(id, model);

        final Label result;
        add(result = new Label("result", new PropertyModel<>(this, "result")));
        result.setOutputMarkupId(true);

        final ModalWindow modal1;
        add(modal1 = new ModalWindow("modal1"));

        modal1.setCookieName("modal-1");
        modal1.setCssClassName(ModalWindow.CSS_CLASS_GRAY);

        modal1.setPageCreator(() -> new ModalContent1Page(modal1));
        modal1.setWindowClosedCallback(target -> target.add(result));
        modal1.setCloseButtonCallback(target -> {
            setResult("Modal window 1 - close button");
            return true;
        });

        add(new AjaxLink<Void>("showModal1")
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target)
            {
                modal1.show(target);
            }
        });

    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
