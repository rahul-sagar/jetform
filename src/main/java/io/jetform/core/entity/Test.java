package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Test")
/*
 * @FormEntity( formActions = @FormActions(actions = {@FormAction(url =
 * "/formbSubmission", buttonOrLinkValue = "Create", action = Action.CREATE,
 * ctaType = CTAType.BUTTON, listable = false),
 * 
 * @FormAction(url = "/formSubmissionTEst", buttonOrLinkValue = "Update", action
 * = Action.UPDATE, ctaType = CTAType.BUTTON, listable = false),
 * 
 * @FormAction(url = "/delete", buttonOrLinkValue = "Delete", ctaType =
 * CTAType.BUTTON, action = Action.DELETE, listable = false),
 * 
 * @FormAction(ctaType = CTAType.BUTTON, buttonOrLinkValue = "Manage KRA", url =
 * "/keyResponsibilityArea", listable = true, action = Action.REDIRECT) }))
 */
public class Test extends EntityClass {
private Integer id;

private String name;

private String fName;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getfName() {
	return fName;
}

public void setfName(String fName) {
	this.fName = fName;
}

@Override
public String toString() {
	return "Test [id=" + id + ", name=" + name + ", fName=" + fName + "]";
}


}
