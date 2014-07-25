package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

public class BSEditorPartCollapsible extends BSComponent {
    
	public BSEditorPartCollapsible(WOContext context) {
        super(context);
    }
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
	}
	
	public String collapsibleId() {
		return valueForStringBinding("collapsibleId", "collapsibleId_NULL_");
	}
	
}