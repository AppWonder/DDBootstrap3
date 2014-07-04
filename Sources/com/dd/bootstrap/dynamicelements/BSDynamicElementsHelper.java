package com.dd.bootstrap.dynamicelements;

import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WODynamicElement;
import com.webobjects.foundation.NSMutableDictionary;

public class BSDynamicElementsHelper {

	public static void AppendCSS(NSMutableDictionary<String, WOAssociation> associations, WODynamicElement element){
		if (element instanceof BSTextField){
			// BSTextField
			associations.setObjectForKey(WOAssociation.associationWithValue("form-control"), "class");
		} else if (element instanceof BSEditorForm) {
			// BSEditorForm
			associations.setObjectForKey(WOAssociation.associationWithValue("form-horizontal"), "class");
			associations.setObjectForKey(WOAssociation.associationWithValue("form"), "role");
		} else if (element instanceof BSEditorRowTextField) {
			associations.setObjectForKey(WOAssociation.associationWithValue("form-control"), "class");
		} else if (element instanceof BSEditorRowPopUpButton) {
			associations.setObjectForKey(WOAssociation.associationWithValue("form-control"), "class");
		} else if (element instanceof BSSubmitButton) {
			associations.setObjectForKey(WOAssociation.associationWithValue("btn btn-primary"), "class");
		} else if (element instanceof BSEditorRowTextarea) {
			associations.setObjectForKey(WOAssociation.associationWithValue("form-control"), "class");
		} else if (element instanceof BSPopUpButton) {
			associations.setObjectForKey(WOAssociation.associationWithValue("chosen-select"), "class");
		}
		else if (element instanceof BSBrowser) {
			associations.setObjectForKey(WOAssociation.associationWithValue("chosen-select"), "class");
		}
	}
	
}
