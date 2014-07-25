package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

public class BSEditorPartCollapsible extends BSComponent {
    
	private String _collapsibleID;
	
	public BSEditorPartCollapsible(WOContext context) {
        super(context);
    }
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		super.appendToResponse(response, context);
		_collapsibleID = null;
	}
	
	public String collapsibleID() {
		if (null != _collapsibleID) {
			return _collapsibleID;
		}
		
		_collapsibleID = "partcollapse-" + context().javaScriptElementID();
		
		return _collapsibleID;
	}
}