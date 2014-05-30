package com.dd.bootstrap.dynamicelements;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOElement;
import com.webobjects.foundation.NSDictionary;

public class BSEditorRowTextareaXtraLarge extends BSEditorRowTextarea {
	
	public BSEditorRowTextareaXtraLarge(String aName, NSDictionary<String, WOAssociation> someAssociations, WOElement template) {
		super(aName, someAssociations, template);
		_associations.setObjectForKey(WOAssociation.associationWithValue("24"), "rows");
	}
	
}
