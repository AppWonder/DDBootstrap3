package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.utils.GeneralColor;
import com.dd.bootstrap.utils.Glyphicon;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WODynamicElement;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

public class BSGlyphicon extends WODynamicElement {
	private WOAssociation glyphiconNameAssoc;
	private WOAssociation glyphiconAssoc;
	private WOAssociation colorAssoc;
	private WOAssociation standaloneAssoc;
	
	public BSGlyphicon(String aName,
			NSDictionary<String, WOAssociation> associations, WOElement template) {
		super(aName, associations, template);
		glyphiconNameAssoc = associations.remove("glyphiconName");
		glyphiconAssoc = associations.remove("glyphicon");
		colorAssoc = associations.remove("color");
		standaloneAssoc = associations.remove("standalone");
	}
	
	
	private Glyphicon glyphicon(WOContext aContext){
		Glyphicon glyphicon = null;
		if(glyphiconAssoc!=null){
			glyphicon = (Glyphicon)glyphiconAssoc.valueInComponent(aContext.component());
		}
		if(glyphiconNameAssoc!=null){
			glyphicon = Glyphicon.glyphiconForClassName(((String)glyphiconNameAssoc.valueInComponent(aContext.component())));
		}
		return glyphicon;
	}
	
	public GeneralColor color(WOContext aContext){
		if(colorAssoc!=null){
			Object colorOb = colorAssoc.valueInComponent(aContext.component());
			if(colorOb instanceof GeneralColor){
				return (GeneralColor)colorOb;
			}
			if(colorOb instanceof String){
				return GeneralColor.valueOf((String)colorOb);
			}
		}
		return null;
	}
	
	public boolean standalone(WOContext context){
		if(standaloneAssoc!=null){
			return Boolean.TRUE.equals(standaloneAssoc.valueInComponent(context.component()));
		}
		return false;
	}
	
	@Override
	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		Glyphicon glyphicon = glyphicon(aContext);
		if(glyphicon!=null){
			glyphicon.appendToResponse(aResponse, color(aContext));
			if(!standalone(aContext)){
				aResponse.appendContentCharacter(' ');
			}
		}
	}

}
