package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;

public class BSPageHeader extends BSComponent {
    
	private static final long serialVersionUID = 1L;

	public BSPageHeader(WOContext context) {
        super(context);
    }
	
	public String title() {
		return stringValueForBinding("title");
	}
	
	public String subtext() {
		return stringValueForBinding("subtext");
	}
}