package com.dd.bootstrap.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

public class BSEditorTable extends BSComponent {
    
	public static interface Bindings {
		public static String Headers = "headers";
		public static String List = "list";
		public static String Item = "item";
	}
	
	public Object currentHeaderColumn;
	
	private NSArray<?> _headers = null;
	
	
	public BSEditorTable(WOContext context) {
        super(context);
    }
	
	
	public NSArray<?> headerColumns() {
		if (_headers != null) return _headers;
		
		if (canGetValueForBinding(Bindings.Headers)) {
			Object rawHeaders = valueForBinding(Bindings.Headers);
			if (rawHeaders instanceof String) {
				_headers = new NSArray<String>( ((String) rawHeaders).split(",") );
			} else if (rawHeaders instanceof NSArray) {
				_headers = (NSArray<?>) rawHeaders;
			}
		}
		
		return _headers;
	}
	
	
	public String displayStringForCurrentHeaderColumn() {
		if (currentHeaderColumn != null) {
			return currentHeaderColumn.toString();
		}
		return null;
	}
	
	@Override
	public void sleep() {
		_headers = null;
		
		super.sleep();
	}
	
	
}