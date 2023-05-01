package ar.com.juanek;


import ar.com.juanek.component.dialog.ModalWindow;
import org.apache.wicket.markup.html.WebPage;

public class ModalContent1Page extends WebPage {

    public ModalContent1Page(final ModalWindow window) {
        System.out.println("modal open");

    }
}
