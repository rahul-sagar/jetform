package io.jetform.core.annotation.model;

import java.util.List;

public class JetFormWrapper {
	private String id;
	private String name;
	private String title;
	private List<FormAction> formAction;
	private List<FormElementWrapper> formElementWrappers;

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

	public List<FormAction> getFormAction() {
		return formAction;
	}

	public void setFormAction(List<FormAction> formAction) {
		this.formAction = formAction;
	}

	public List<FormElementWrapper> getFormElementWrappers() {
		return formElementWrappers;
	}

	public void setFormElementWrappers(List<FormElementWrapper> formElementWrappers) {
		this.formElementWrappers = formElementWrappers;
	}

}
