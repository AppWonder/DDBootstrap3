package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;

public class BSConfirmPanel extends BSComponent {
	
    public BSConfirmPanel(WOContext context) {
        super(context);
    }
    
    public String cssClass() {
    	StringBuilder sb = new StringBuilder();
    	
    	if (!booleanValueForBinding("displayAsLink", false)) {
    		sb.append("btn btn-danger");
    	}
    	
    	if (canGetValueForBinding("class")) {
    		sb.append(stringValueForBinding("class"));
    	}
    	return sb.length() != 0 ? sb.toString() : null;
    }
    
    public String onclickJS() {
    	String message = stringValueForBinding("confirmMessage");
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("return confirm('");
    	sb.append(message);
    	sb.append("');");
    	
    	return sb.toString();
    }
}