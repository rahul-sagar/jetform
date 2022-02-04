package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Relation;
import io.jetform.core.enums.Type;

@Entity
@Table(name = "tbl_Invoice_Tax_item")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") })
public class InvoiceTax {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "taxId")
	//@FormElement(listable = true,readOnly = true,relation=@Relation(relatioClass=TaxItem.class,keyField="id",lableField="name"))//relation to show which field
	@FormElement(listable = true,readOnly = true)
	private TaxItem item;
	
	@ManyToOne
	@JoinColumn(name = "invoiceId")
	private Invoice invoice;
	
	@FormElement(listable = true,number = @Number(format = "##"))
	private float amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TaxItem getItem() {
		return item;
	}

	public void setItem(TaxItem item) {
		this.item = item;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
