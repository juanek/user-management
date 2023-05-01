package ar.com.mw.smart4.block;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.IAjaxCallListener;

public class BlockUIListener implements IAjaxCallListener {


    private String message = "Espere ...";

    public BlockUIListener(String message) {
        this.message = message;
    }

    public BlockUIListener() {
    }

    @Override
    public CharSequence getPrecondition(Component component) {
        return String.format("$.blockUI({message: '%s'});",message);
                //"$.blockUI({message: 'Espere, reservando ...'});";
    }

    @Override
    public CharSequence getSuccessHandler(Component component) {
        return "$.unblockUI();";
    }
}
