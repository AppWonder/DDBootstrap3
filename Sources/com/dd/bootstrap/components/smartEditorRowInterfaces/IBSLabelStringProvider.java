package com.dd.bootstrap.components.smartEditorRowInterfaces;

import com.webobjects.appserver.WOContext;

public interface IBSLabelStringProvider {

	public String labelString(Object object, String key, WOContext context);
}
