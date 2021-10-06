package io.jetform.core.annotation.model;

import io.jetform.core.annotation.Checkbox;
import io.jetform.core.annotation.FormField;
import io.jetform.core.annotation.Select;
import io.jetform.core.enums.LoadType;
import io.jetform.core.enums.ResourceType;

public class DataProvider {
	/*
	 * String path() default ""; ResourceType resource(); LoadType loadType()
	 * default LoadType.EAGER;
	 */
	private String path;
	private ResourceType resource;
	private LoadType loadType;
	private String key;
	private String value;
	
	public DataProvider(String path,ResourceType resource,LoadType loadType){
		this.path = path;
		this.resource = resource;
		this.loadType = loadType;
	}
	
	public DataProvider(FormField field){
		this.path = field.radio().dataProvider().path();
		this.resource = field.radio().dataProvider().resource();
		this.loadType=field.radio().dataProvider().loadType();
		this.key=field.radio().dataProvider().key();
		this.value=field.radio().dataProvider().value();
	}
	
	public DataProvider(Checkbox checkbox){
		this.path = checkbox.dataProvider().path();
		this.resource = checkbox.dataProvider().resource();
		this.loadType = checkbox.dataProvider().loadType();
		this.key = checkbox.dataProvider().key();
		this.value = checkbox.dataProvider().value();
	}
	
	public DataProvider(Select select){
		this.path = select.dataProvider().path();
		this.resource = select.dataProvider().resource();
		this.loadType = select.dataProvider().loadType();
		this.key = select.dataProvider().key();
		this.value = select.dataProvider().value();
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ResourceType getResource() {
		return resource;
	}
	public void setResource(ResourceType resource) {
		this.resource = resource;
	}
	public LoadType getLoadType() {
		return loadType;
	}
	public void setLoadType(LoadType loadType) {
		this.loadType = loadType;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "DataProvider [path=" + path + ", resource=" + resource + ", loadType=" + loadType + ", key=" + key
				+ ", value=" + value + "]";
	}
	
	
}
