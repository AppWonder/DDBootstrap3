package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

import er.extensions.appserver.ERXResponseRewriter;

public class BSFooter extends BSComponent {
    public BSFooter(WOContext context) {
        super(context);
    }
    
    @Override
    public void appendToResponse(WOResponse response, WOContext context) {
    	ERXResponseRewriter.addStylesheetResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "css/footer.css");
    	super.appendToResponse(response, context);
    }
}