package com.dd.bootstrap.dynamicelements;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOElement;
import com.webobjects.foundation.NSDictionary;

public class BSEditorRowTextareaLarge extends BSEditorRowTextarea {
	
	public BSEditorRowTextareaLarge(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		_associations.setObjectForKey(WOAssociation.associationWithValue("12"), "rows");
	}
	
}
