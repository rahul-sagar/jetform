package io.jetform.core.helperclasses;

import java.util.List;

import io.jetform.core.annotation.FormEntity;
import io.jetform.core.annotation.model.FormFieldBase;

public class FormMetaData{

	private String id;
	private String name;
	private String title;
	private List<FormAction> actions;
	private List<FormFieldBase> elements;
	
	public FormMetaData() {}
	
	public FormMetaData(FormEntity formEntity) {
	  this.id = formEntity.id();
	  this.name = formEntity.name();
	  this.title = formEntity.title();
	  
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<FormFieldBase> getElements() {
		return elements;
	}
	public void setElements(List<FormFieldBase> elements) {
		this.elements = elements;
	}
  
	public List<FormAction> getActions() {
		return actions;
	}

	public void setActions(List<FormAction> actions) {
		this.actions = actions;
	}
	
	/*
	 * public void populateAction(FormEntity formEntity){ List<FormAction> actions =
	 * new ArrayList<>(); Arrays.stream(formEntity.actions()).map(e -> {
	 * actions.add(new FormAction()) }) }
	 */
}
