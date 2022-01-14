package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementGroup;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Type;

@Entity
@Table(name = "tbl_tax_item")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") })
public class TaxItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private int id;
	
	@FormElement(listable = true,number = @Number(format = "##"))
	private double amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaxItem [id=");
		builder.append(id);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", tax=");
		builder.append("]");
		return builder.toString();
	}
	
}