package com.dd.bootstrap.components.smartEditorRowInterfaces;

import com.webobjects.appserver.WOContext;

public interface IBSHelpTextProvider {
	public String helpText(Object object, String key, WOContext context);
}
