package com.dd.bootstrap.components;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.dd.bootstrap.components.smartEditorRowInterfaces.IBSHelpTextProvider;
import com.dd.bootstrap.components.smartEditorRowInterfaces.IBSLabelStringProvider;
import com.dd.bootstrap.components.smartEditorRowInterfaces.IBSPossibleSelectablesProvider;
import com.dd.bootstrap.components.smartEditorRowInterfaces.IBSSelectableDisplayStringProvider;
import com.dd.bootstrap.components.smartEditorRowInterfaces.IBSUnitStringProvider;
import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EOAttribute;
import com.webobjects.eoaccess.EORelationship;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSForwardException;

import er.extensions.eof.ERXEntityClassDescription;
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
    
    
    public Object valueForAttributeKey(){
    	Object value = genericRecord().valueForKey(key());
    	if(value instanceof EOGenericRecord){
    		return value;
    	}
    	if(keyIsEnum()){
    		return value;
    	}
    	if(keyIsToManyRelationship()){
    		return value;
    	}
    	return ObjectUtils.toString(value);
	}
	
	public void setValueForAttributeKey(Object value){
		if(keyIsToOneRelationship()||keyIsToManyRelationship()){
			genericRecord().takeValueForKey(value, key());
			return;
		}
		if(keyIsEnum()){
			genericRecord().takeValueForKey(value, key());
		}
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
	
	public IBSSelectableDisplayStringProvider selectableDisplayStringProvider(){
		return (IBSSelectableDisplayStringProvider)valueForBinding("selectableDisplayStringProvider");
	}
	
	public IBSPossibleSelectablesProvider possibleSelectablesProvider(){
		return (IBSPossibleSelectablesProvider)valueForBinding("possibleSelectablesProvider");
	}
	
	public IBSLabelStringProvider labelStringProvider(){
		return (IBSLabelStringProvider)valueForBinding("labelStringProvider");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NSArray popUpArray(){
		NSArray objects = null;
		if(possibleSelectablesProvider()!=null){
			objects = possibleSelectablesProvider().selectablesForKey(genericRecord(), key(), context());
		}
		if(objects != null){
			return objects;
		}
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
	
	@SuppressWarnings("rawtypes")
	public String displayStringForCurrentSelectableItem(){
		String aValue = null;
		if(selectableDisplayStringProvider()!=null){
			aValue = selectableDisplayStringProvider().displayString(currentSelectable, context());
		}
		if(aValue!=null){
			return aValue;
		}
		if(keyIsEnum()){
			return ((Enum)currentSelectable).name();
		}
		if(currentSelectable instanceof ERXGenericRecord){
			return ObjectUtils.toString(((ERXGenericRecord) currentSelectable).valueForKey(possibleDisplayStringForCurrentSelectable()));
		}
		return ObjectUtils.toString(currentSelectable);
	}
	
	public static final Format UNSCALED_NUMBER_FORMATTER = new Format(){

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
		ERXGenericRecord selectableRecord = (ERXGenericRecord)currentSelectable();
		for(String attributeKey : selectableRecord.attributeKeys()){
			EOAttribute attribute = selectableRecord.entity().attributeNamed(attributeKey);
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
		String labelString = (String)valueForBinding("label");
		
		if(labelStringProvider()!=null){
			labelString = labelStringProvider().labelString(genericRecord(), key(), context());
		}
		if(labelString!=null){
			return labelString;
		}
		String localizedLabel = new ERXEntityClassDescription(genericRecord().entity()).displayNameForKey(key());
		if(StringUtils.isNotBlank(localizedLabel)){
			return localizedLabel;
		}
		return key();
	}


	public IBSUnitStringProvider unitStringProvider(){
		return (IBSUnitStringProvider)valueForBinding("unitStringProvider");
	}
	
	public String unit() {
		if(unitStringProvider()!=null){
			return unitStringProvider().unitString(genericRecord(), key(), context());
		}
		return null;
	}
	
	public IBSHelpTextProvider helpTextProvider(){
		return (IBSHelpTextProvider)valueForBinding("helpTextProvider");
	}
	
	public String helpText() {
		if(helpTextProvider()!=null){
			return helpTextProvider().helpText(genericRecord(), key(), context());
		}
		return null;
	}
}