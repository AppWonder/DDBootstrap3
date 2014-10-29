package com.dd.bootstrap.components.smartEditorRowInterfaces;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXGenericRecord;

public interface IBSPossibleSelectablesProvider {

	public NSArray<? extends Object> selectablesForKey(ERXGenericRecord object,String key, WOContext context);
}
