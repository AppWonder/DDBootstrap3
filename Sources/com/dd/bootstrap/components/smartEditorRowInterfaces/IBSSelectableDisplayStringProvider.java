package com.dd.bootstrap.components.smartEditorRowInterfaces;

import com.webobjects.appserver.WOContext;

public interface IBSSelectableDisplayStringProvider {

	
	public String displayString(Object selectable, WOContext context);
}
