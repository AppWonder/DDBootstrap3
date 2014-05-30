package com.dd.bootstrap.dynamicelements;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOElement;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components._private.ERXWOForm;

public class BSEditorForm extends ERXWOForm {
	
	public BSEditorForm(String name, NSDictionary<String, WOAssociation> associations, WOElement element) {
		super(name, associations, element);
		BSDynamicElementsHelper.AppendCSS(_associations, this);
	}

}
