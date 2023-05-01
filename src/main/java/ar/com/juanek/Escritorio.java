package ar.com.juanek;

import ar.com.juanek.resources.EscritorioCSS;
import ar.com.juanek.resources.EscritorioJS;
import ar.com.juanek.event.MenuEvent;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Escritorio extends Panel {

    private static final Class[] constructorDePanel = {String.class, IModel.class};

    public static final String CONTENIDO = "contenido";
    private Panel contenido;



    public Escritorio(String id, IModel<UserModel> model) {
        super(id, model);

        add(new BarraEstado("barra-estado", model));

        add(new Sidebar("sidebar", model));

        add(contenido = new EmptyPanel(CONTENIDO));
        contenido.setOutputMarkupId(true);
    }

    @Override
    public void onEvent(IEvent<?> event) {

        Panel newContenido = null;
        if (event.getPayload() instanceof MenuEvent) {
            MenuEvent barraEvent = (MenuEvent) event.getPayload();
            Class clazz = barraEvent.getPanelClass();
            try {
                Constructor cons = clazz.getDeclaredConstructor(constructorDePanel);
                newContenido = (Panel) cons.newInstance("contenido", getDefaultModel());

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            newContenido.setOutputMarkupId(true);
            contenido.replaceWith(newContenido);
            barraEvent.getTarget().add(newContenido);
            contenido = newContenido;

        }

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new EscritorioCSS()));
        response.render(JavaScriptHeaderItem.forReference(new EscritorioJS()));
    }
}
