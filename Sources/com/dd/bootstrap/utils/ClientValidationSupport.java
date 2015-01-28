package com.dd.bootstrap.utils;

import org.apache.commons.lang.ObjectUtils;

import com.dd.bootstrap.components.BSComponent;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.appserver.ERXResponseRewriter;

public class ClientValidationSupport {
	public static final String PARSLEY_NAME_SPACE = "data-parsley-";
	
	public static void appendJavaScript(WOResponse response, WOContext context){
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/jquery.min.js");
		ERXResponseRewriter.addScriptResourceInHead(response, context, BSComponent.FRAMEWORK_NAME, "js/parsley.min.js");		
	}
	
	public static void appendValidiationBindings(NSMutableDictionary<String, WOAssociation> associations, WOResponse response, WOContext context){		
		/*
		 * Supported validations
		 * data-parsley-required="true"
		 * data-parsley-type="email"
		 * data-parsley-type="number"
		 * data-parsley-type="integer"
		 * data-parsley-type="digits"
		 * data-parsley-type="alphanum"
		 * data-parsley-type="url"
		 * data-parsley-minlength="6"
		 * data-parsley-maxlength="6"
		 * data-parsley-length="[6, 10]"
		 * data-parsley-min="6"
		 * data-parsley-max="6"
		 * data-parsley-range="[6,10]"
		 * data-parsley-pattern="\d+"
		 * data-parsley-mincheck="3"
		 * data-parsley-maxcheck="3"
		 * data-parsley-check="[1, 3]"
		 * data-parsley-equalto="#anotherfield"
		 * ---------------
		 * data-parsley-`constraint`-message="my message"
		 * data-parsley-errors-container="#element"
		 */
		WOComponent aComponent = context.component();
		
		NSMutableDictionary<String,String> bindings = new NSMutableDictionary<String, String>();
		if (associations!=null&&associations.objectForKey("required")!=null){
			// requiredType data-parsley-type="email"	
			bindings.takeValueForKey("true", "required");
		}
			if (associations!=null&&associations.objectForKey("requiredtype")!=null&&associations.objectForKey("requiredtype").valueInComponent(context.component())!=null){
				// requiredType data-parsley-type="email"
				
				response.appendContentString(PARSLEY_NAME_SPACE+"type=\"");
				response.appendContentHTMLAttributeValue(ObjectUtils.toString(associations.objectForKey("requiredtype").valueInComponent(aComponent)));
				response.appendContentString("\" ");
			}
			
			if (associations!=null&&associations.objectForKey("requiredminlength")!=null&&associations.objectForKey("requiredminlength").valueInComponent(context.component())!=null){
				// requiredType data-parsley-minlength="6"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredminlength").valueInComponent(aComponent)), "minlength");				
			}
			if (associations!=null&&associations.objectForKey("requiredmaxlength")!=null&&associations.objectForKey("requiredmaxlength").valueInComponent(context.component())!=null){
				// requiredType data-parsley-maxlength="6"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredmaxlength").valueInComponent(aComponent)), "maxlength");
			}
			if (associations!=null&&associations.objectForKey("requiredlength")!=null&&associations.objectForKey("requiredlength").valueInComponent(context.component())!=null){
				// data-parsley-length="[6, 10]"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredlength").valueInComponent(aComponent)), "length");
			}
			if (associations!=null&&associations.objectForKey("requiredmin")!=null&&associations.objectForKey("requiredmin").valueInComponent(context.component())!=null){
				// data-parsley-min="6"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredmin").valueInComponent(aComponent)), "min");
			}
			if (associations!=null&&associations.objectForKey("requiredmax")!=null&&associations.objectForKey("requiredmax").valueInComponent(context.component())!=null){
				// data-parsley-max="6"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredmax").valueInComponent(aComponent)), "max");
			}
			if (associations!=null&&associations.objectForKey("requiredrange")!=null&&associations.objectForKey("requiredrange").valueInComponent(context.component())!=null){
				// data-parsley-range="[6,10]"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredrange").valueInComponent(aComponent)), "range");
			}
			if (associations!=null&&associations.objectForKey("requiredpattern")!=null&&associations.objectForKey("requiredpattern").valueInComponent(context.component())!=null){
				// data-parsley-mincheck="3"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredpattern").valueInComponent(aComponent)), "pattern");
			}
			if (associations!=null&&associations.objectForKey("requiredmaxcheck")!=null&&associations.objectForKey("requiredmaxcheck").valueInComponent(context.component())!=null){
				// data-parsley-maxcheck="3"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredmaxcheck").valueInComponent(aComponent)), "maxcheck");
			}
			if (associations!=null&&associations.objectForKey("requiredcheck")!=null&&associations.objectForKey("requiredcheck").valueInComponent(context.component())!=null){
				// data-parsley-check="[1, 3]"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredcheck").valueInComponent(aComponent)), "check");
			}
			if (associations!=null&&associations.objectForKey("requiredequalto")!=null&&associations.objectForKey("requiredequalto").valueInComponent(context.component())!=null){
				// data-parsley-equalto="#anotherfield"
				bindings.setObjectForKey(ObjectUtils.toString(associations.objectForKey("requiredequalto").valueInComponent(aComponent)), "equalto");
			}
			
			if(!bindings.isEmpty()){
				response.appendContentCharacter(' ');
			}
			for(String key : bindings.allKeys()){
				response.appendContentString(PARSLEY_NAME_SPACE+key+"=\"");
				response.appendContentHTMLAttributeValue(bindings.objectForKey(key));
				response.appendContentString("\" ");
			}
			
		}
	
	public static boolean requiresValidation(NSMutableDictionary<String, WOAssociation> associations, WOContext context){
		if (associations!=null&&associations.objectForKey("required")!=null){
			// requiredType data-parsley-type="email"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredtype")!=null&&associations.objectForKey("requiredtype").valueInComponent(context.component())!=null){
			// requiredType data-parsley-type="email"
			return true;
		}
		
		if (associations!=null&&associations.objectForKey("requiredminlength")!=null&&associations.objectForKey("requiredminlength").valueInComponent(context.component())!=null){
			// requiredType data-parsley-minlength="6"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredmaxlength")!=null&&associations.objectForKey("requiredmaxlength").valueInComponent(context.component())!=null){
			// requiredType data-parsley-maxlength="6"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredlength")!=null&&associations.objectForKey("requiredlength").valueInComponent(context.component())!=null){
			// data-parsley-length="[6, 10]"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredmin")!=null&&associations.objectForKey("requiredmin").valueInComponent(context.component())!=null){
			// data-parsley-min="6"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredmax")!=null&&associations.objectForKey("requiredmax").valueInComponent(context.component())!=null){
			// data-parsley-max="6"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredrange")!=null&&associations.objectForKey("requiredrange").valueInComponent(context.component())!=null){
			// data-parsley-range="[6,10]"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredpattern")!=null&&associations.objectForKey("requiredpattern").valueInComponent(context.component())!=null){
			// data-parsley-mincheck="3"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredmaxcheck")!=null&&associations.objectForKey("requiredmaxcheck").valueInComponent(context.component())!=null){
			// data-parsley-maxcheck="3"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredcheck")!=null&&associations.objectForKey("requiredcheck").valueInComponent(context.component())!=null){
			// data-parsley-check="[1, 3]"
			return true;
		}
		if (associations!=null&&associations.objectForKey("requiredequalto")!=null&&associations.objectForKey("requiredequalto").valueInComponent(context.component())!=null){
			// data-parsley-equalto="#anotherfield"
			return true;
		}
		return false;
	}
	
}
