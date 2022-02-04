package io.jetform.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementEvent;
import io.jetform.core.annotation.FormElementEventSubscription;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Select;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Type;

/**
 *
 * @author DELL PC
 */
@Entity
@Table(name = "tbl_invoice_item")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") })
public class InvoiceItem{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@FormElement(hidden = @Hidden(value = "0"))
	private int id;
	
	//@FormElement(events= {@htmlelementevent(name="onChange")},listable = true,select = @Select(options = {"d1:des-1","d2:des-2"}))
	@FormElement(events = {@FormElementEvent(name = "onChange")},listable = true,select = @Select(options = {"1:POI-1","2:POI-2","3:POI-3","4:POI-4"}),
			subscribeEvents = {@FormElementEventSubscription(source = "purchaseOrder",action = "onPurchaseOrderChangeRefreshPOI(source)",name = "onChange")})
	private String poi;
	
	@FormElement(listable = true,number = @Number(format = "##"))
	private double amount;

	//@FormElement(subscribeevents= {@htmlelementeventsubscribetion(source="poi",name="onChange",action="call()")},listable = true,select = @Select(options = {"d1:des-1","d2:des-2"}))
	@FormElement(subscribeEvents = {@FormElementEventSubscription(source = "poi",name = "onChange",action = "setPurchaseOrderItems(this)")},listable = true)
	private String descrition;

	@FormElement(listable = true)
	private String name;

	@FormElement(listable = true,number = @Number(format = "##"))
	private double part;

	@FormElement(listable = true,number = @Number(format = "##"))
	private int quantity;

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

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPart() {
		return part;
	}

	public void setPart(double part) {
		this.part = part;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvoiceItem [id=").append(id).append(", amount=").append(amount).append(", descrition=")
				.append(descrition).append(", name=").append(name).append(", part=").append(part).append(", quantity=")
				.append(quantity).append(", clientPurchaseOrderItem=").append("]");
		return builder.toString();
	}

	


}
