package com.dd.bootstrap.components;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EORelationship;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSForwardException;

import er.extensions.eof.ERXGenericRecord;

public class BSSmartEditorRow extends BSComponent {
	private Object currentSelectable;
	
    public BSSmartEditorRow(WOContext context) {
        super(context);
    }
    
    
    public ERXGenericRecord genericRecord(){
    	return (ERXGenericRecord)valueForBinding("genericRecord");
    }
    
    public String key(){
    	return (String)valueForBinding("key");
    }
    
    public String valueForAttributeKey(){
    	Object value = genericRecord().valueForKey(key());
    	return ObjectUtils.toString(value);
	}
	
	public void setValueForAttributeKey(Object value){

		try{
			Object _value = value;
			if(formatter() != null) {
				if (value instanceof String) {
					_value = formatter().parseObject((String) value);
				}
			}
			genericRecord().takeValueForKey(_value, key());
			
		}
		catch(ParseException e){
			throw new NSForwardException(e);
		}
	}


	
	public EOAttribute attributeForAttributeKey(){
		return genericRecord().entity().attributeNamed(key());
	}

	public boolean showTextField() {
		return attributeAsSimpleInput()
				&& (EOAttribute.AdaptorNumberType == attributeForAttributeKey().adaptorValueType() || attributeForAttributeKey().width() <= 256);
	}
	
	public boolean showSmallTextArea() {
		return attributeAsSimpleInput()&&(attributeForAttributeKey().width()>256&&attributeForAttributeKey().width()<=500);
	}
	
	public boolean showMediumTextArea() {
		return attributeAsSimpleInput()&&(attributeForAttributeKey().width()>500&&attributeForAttributeKey().width()<=1000);
	}
	
	public boolean showLargeTextArea() {
		return attributeAsSimpleInput()&&(attributeForAttributeKey().width()>1000&&attributeForAttributeKey().width()<=1500);
	}
	
	public boolean showXtraLargeTextArea() {
		return attributeAsSimpleInput()&&(attributeForAttributeKey().width()>1500);
	}
	
	public boolean isRequired(){
		return (attributeForAttributeKey()!=null&&!attributeForAttributeKey().allowsNull())||(relationshipForKey()!=null&&relationshipForKey().isMandatory());
	}
	
	public boolean showPopUpButton() {
		return (attributeForAttributeKey()!=null&&attributeForAttributeKey().adaptorValueClass()!=null&&keyIsEnum())||keyIsToOneRelationship();
	}
	
	public boolean showMultipleSelect (){
		return keyIsToManyRelationship();
	}
	
	
	public boolean attributeAsSimpleInput(){
		return !keyIsEnum()&&relationshipForKey()==null;
	}
	
	public EORelationship relationshipForKey(){
		return genericRecord().entity().relationshipNamed(key());
	}
	
	public boolean keyIsToOneRelationship(){
		return relationshipForKey()!=null&&!relationshipForKey().isToMany();

	}
	
	public boolean keyIsToManyRelationship(){
		return relationshipForKey()!=null&&relationshipForKey().isToMany();
	}
	
	public boolean keyIsEnum(){
		try{
			return attributeForAttributeKey()!=null&&Class.forName(attributeForAttributeKey().valueTypeClassName()).isEnum();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public NSArray popUpArray(){
		 if(keyIsEnum()){
			 try{
				 return new NSArray(Class.forName(attributeForAttributeKey().valueTypeClassName()).getEnumConstants());
			 }
			 catch(Exception e){}
		 }
		 if(relationshipForKey()!=null){
			 EOFetchSpecification spec = new EOFetchSpecification(relationshipForKey().destinationEntity().name(),null,null);
			 return genericRecord().editingContext().objectsWithFetchSpecification(spec);
		 }
		 return null;
	}
	
	public String displayStringForCurrentSelectableItem(){
		if(keyIsEnum()){
			return ((Enum)currentSelectable).name();
		}
		if(currentSelectable instanceof ERXGenericRecord){
			return ObjectUtils.toString(((ERXGenericRecord) currentSelectable).valueForKey(possibleDisplayStringForCurrentSelectable()));
		}
		return ObjectUtils.toString(currentSelectable);
	}
	
	private static final Format UNSCALED_NUMBER_FORMATTER = new Format(){

																	@Override
																	public StringBuffer format(Object obj, StringBuffer toAppendTo,
																			FieldPosition pos) {
																		if(obj!=null){
																			toAppendTo.append(obj);
																		}
																		return toAppendTo;
																	}

																	@Override
																	public Object parseObject(String source, ParsePosition pos) {
																		if(StringUtils.isBlank(source)){
																			return null;
																		}
																		Double value = Double.valueOf(source);
																		return value;
																	}
																	
																	public Object parseObject(String source) throws java.text.ParseException {
																		try{
																			if(StringUtils.isBlank(source)){
																				return null;
																			}
																			Double value = Double.valueOf(source);
																			return value;
																		}catch(Exception e){
																			throw new ParseException("Cannot parse: "+source, 0);
																		}
																	};

																	};
	public Format formatter(){
		if (attributeForAttributeKey() == null) { return null; }
		
		String valueTypeClassName = attributeForAttributeKey().valueTypeClassName();
		
		if(Double.class.getName().equals(valueTypeClassName) || Integer.class.getName().equals(valueTypeClassName)) {
			return UNSCALED_NUMBER_FORMATTER;
		}

		return null;
	}
	
	public String possibleDisplayStringForCurrentSelectable(){
		String currentFavoriteKey = ObjectUtils.toString(currentSelectable);
		boolean hasFoundName = false;
		for(String attributeKey : genericRecord().attributeKeys()){
			EOAttribute attribute = genericRecord().entity().attributeNamed(attributeKey);
			if(attribute.className()==String.class.getName()){
				if(attributeKey.toLowerCase().contains("name")){
					if(hasFoundName){
						if(attributeKey.length()<currentFavoriteKey.length()){
							currentFavoriteKey = attributeKey;
						}
					}
					else{
						hasFoundName = true;
						currentFavoriteKey = attributeKey;
					}
					
				}
				else{
					if(!hasFoundName){
						if(attributeKey.length()<currentFavoriteKey.length()){
							currentFavoriteKey = attributeKey;
						}
					}
				}
			}
		}
		return currentFavoriteKey;
	}
	
	public Object currentSelectable(){
		return currentSelectable;
	}
	
	public void setCurrentSelectable(Object currentSelectable){
		this.currentSelectable = currentSelectable;
	}
	public String labelForAttributeKey(){
		//TODO Use LocalizationManager
		return key();
	}
}