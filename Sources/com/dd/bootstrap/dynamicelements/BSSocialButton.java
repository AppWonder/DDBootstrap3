package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSComponent;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components._private.ERXHyperlink;

public class BSSocialButton extends ERXHyperlink {
	
	public static interface BSSocialButtonTypes {
		public static final String AppNet 		= "apn";
		public static final String Bitbucket 	= "bitbucket";
		public static final String Dropbox 		= "dropbox";
		public static final String Facebook 	= "facebook";
		public static final String Flickr 		= "flickr";
		public static final String Foursquare 	= "foursquare";
		public static final String GitHub 		= "github";
		public static final String GooglePlus 	= "google-plus";
		public static final String Instagram 	= "instagram";
		public static final String LinkedIn 	= "linkedin";
		public static final String Microsoft 	= "microsoft";
		public static final String OpenID 		= "openid";
		public static final String Reddit 		= "reddit";
		public static final String SoundCloud 	= "soundcloud";
		public static final String Tumblr 		= "tumblr";
		public static final String Twitter 		= "twitter";
		public static final String Vimeo 		= "vimeo";
		public static final String VK 			= "vk";
		public static final String Yahoo 		= "yahoo";
		
		public static final NSArray<String> AllTypes = new NSArray<String>(
				AppNet, Bitbucket, Dropbox, Facebook, Flickr, Foursquare,
				GitHub, GooglePlus, Instagram, LinkedIn, Microsoft, OpenID,
				Reddit, SoundCloud, Tumblr, Twitter, Vimeo, VK, Yahoo
		);
	}
	
	private WOAssociation _asIcon;
	private WOAssociation _type;
	
	@SuppressWarnings("rawtypes")
	public BSSocialButton(String tagname, NSDictionary nsdictionary, WOElement woelement) {
		super(tagname, nsdictionary, woelement);
		
		_asIcon = _associations.removeObjectForKey("asIcon");
		_type = _associations.removeObjectForKey("type");
	}
	
	@Override
	public void appendToResponse(WOResponse aResponse, WOContext aContext) {
		BSComponent.InjectCSSAndJS(aResponse, aContext);
		super.appendToResponse(aResponse, aContext);
	}
	
	@Override
	public void _appendClassAndIdToResponse(WOResponse response, WOContext context) {
		StringBuilder className = new StringBuilder(asIconInContext(context) ? "btn btn-social-icon btn-" : "btn btn-block btn-social btn-");
		className.append(typeInContext(context));
		
		String otherClass = classInContext(context);
		if (otherClass.length() > 0) {
			className.append(" ");
			className.append(otherClass);
		}
		
        if(className.length() > 0) {
            _appendTagAttributeAndValueToResponse(response, "class", className.toString(), false);
        }
        
        String id = idInContext(context);
        if(id.length() > 0) {
            _appendTagAttributeAndValueToResponse(response, "id", id, false);
        }
        
        String style = styleInContext(context);
        if(style.length() > 0) {
            _appendTagAttributeAndValueToResponse(response, "style", style, false);
        }
        
        String title = titleInContext(context);
        if(title.length() > 0) {
            _appendTagAttributeAndValueToResponse(response, "title", title, false);
        }
    }
	
	@Override
	public void appendChildrenToResponse(WOResponse aResponse, WOContext aContext) {
		aResponse.appendContentString("<i class=\"fa fa-"+ typeInContext(aContext) +"\"></i>");
		super.appendChildrenToResponse(aResponse, aContext);
	}
	
	public boolean asIconInContext(WOContext context) {
		return "true".equals(stringValueInContext(_asIcon, context).toLowerCase());
	}
	
	public String typeInContext(WOContext context) {
		String type = stringValueInContext(_type, context);
		if (!BSSocialButtonTypes.AllTypes.containsObject(type)) {
			throw new IllegalArgumentException("Specified button type \"" + type + "\" is not supported. Valid values are: " + BSSocialButtonTypes.AllTypes.componentsJoinedByString(", "));
		}
		return type;
	}
	

}
