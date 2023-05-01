package ar.com.juanek.block;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class BlockUIResourceReference extends JavaScriptResourceReference {

    public BlockUIResourceReference() {
        super(BlockUIResourceReference.class,"jquery.blockUI.js");
    }
}
