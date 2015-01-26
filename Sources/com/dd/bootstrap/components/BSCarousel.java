package com.dd.bootstrap.components;

import org.apache.commons.lang.ObjectUtils;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSKeyValueCodingAdditions;

import er.extensions.foundation.ERXArrayUtilities;
import er.extensions.foundation.ERXStringUtilities;

public class BSCarousel extends BSComponent {
	
    private Integer currentIndex;
    private String id;

	public BSCarousel(WOContext context) {
        super(context);
    }

	public String carouselId() {
		if(id==null){
		if(valueForBinding("id")!=null){
			id = (String)valueForBinding("id");
		}
		id = ERXStringUtilities.safeIdentifierName(context().elementID());
		}
		return id;
	}
	
	public NSArray<?> list(){
		return (NSArray<?>)valueForBinding("list");
	}
	
	public void setItem(Object item){
		if(canSetValueForBinding("item")){
			setValueForBinding(item, "item");
		}
	}
	
	public Object item(){
		return valueForBinding("item");
	}
	
	public Object caption(){
		if(valueForBinding("caption")!=null){
			return valueForBinding("caption");
		}
		if(captionKeyPath()!=null&&item()!=null){
			return NSKeyValueCodingAdditions.DefaultImplementation.valueForKeyPath(item(), captionKeyPath());
		}
		return "Item "+currentIndex()+1;
	}
	
	public String captionKeyPath(){
		return (String)valueForBinding("captionKeyPath");
	}
	
	public boolean isFirst(){
		return ObjectUtils.equals(ERXArrayUtilities.firstObject(list()), item());
	}
	


	/**
	 * @return the currentIndex
	 */
	public Integer currentIndex() {
		return currentIndex;
	}

	/**
	 * @param currentIndex the currentIndex to set
	 */
	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}
	
	public String itemClass(){
		if(isFirst()){
			return "item active";
		}
		return "item";
	}

	public String dataTarget() {
		return "#"+carouselId();
	}
}