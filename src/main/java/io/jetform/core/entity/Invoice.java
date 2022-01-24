package io.jetform.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.jetform.core.annotation.CustomField;
import io.jetform.core.annotation.Date;
import io.jetform.core.annotation.Form;
import io.jetform.core.annotation.FormAction;
import io.jetform.core.annotation.FormElement;
import io.jetform.core.annotation.FormElementEvent;
import io.jetform.core.annotation.FormElementGroup;
import io.jetform.core.annotation.Hidden;
import io.jetform.core.annotation.JetForm;
import io.jetform.core.annotation.Number;
import io.jetform.core.annotation.Select;
import io.jetform.core.annotation.TextArea;
import io.jetform.core.enums.Action;
import io.jetform.core.enums.Relation;
import io.jetform.core.enums.Type;

/**
 *
 * @author DELL PC
 */
@Entity
@Table(name = "tbl_invoice")
@JetForm(listIndex = false, actions = {
		@FormAction(name = "create", action = Action.CREATE, type = Type.SUBMIT, label = "Create"),
		@FormAction(name = "/update", action = Action.UPDATE, type = Type.BUTTON, label = "Update"),
		@FormAction(name = "/delete", action = Action.DELETE, type = Type.BUTTON, label = "Delete"),
		@FormAction(name = "/list", action = Action.READ, type = Type.BUTTON, label = "Read") },
         groups = {@FormElementGroup(id = "client",label = "Client Info",elementsPerRow = 1),
        		   @FormElementGroup(id = "invoice-date",label = "Invoice Date info",elementsPerRow = 1),
        		   @FormElementGroup(id = "invoice-items",label = "Invoice Item Info",elementsPerRow = 1),
        		   @FormElementGroup(id = "terms-conditions",label = "Invoice T&C ",elementsPerRow = 1),
        		   @FormElementGroup(id = "invoice-tax",label = "Invoice Tax Detail",elementsPerRow = 1)}
,formTemplate = "invoice")//formTemplate = "invoice"
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@FormElement(hidden = @Hidden(value = "0"))
	private int id;

	@Column(name = "client")
	@FormElement(listable = true,select = @Select(options = {"TSC:TSC","WIPRO:WIPRO"}),group = "client")
	private String client;

	@Column(name = "purchaseOrder")
	@FormElement(listable = true,select = @Select(options = {"PO1:PO - 1","PO2:PO - 2"}),group = "client")
	private String purchaseOrder;

	@Column(name = "invoiceNo")
	@FormElement(listable = true,disabled = true ,group = "invoice-date")
	private String invoiceNo;

	@Column(name = "invoiceDate")
	@FormElement(listable = true,date = @Date(format="yy-mm-dd"),group = "invoice-date")
	private String invoiceDate;

	@Column(name = "dueDate")
	@FormElement(listable = true,date = @Date(format="yy-mm-dd"),group = "invoice-date")
	private String dueDate;

	@Column(name = "currency")
	@FormElement(listable = true,select = @Select(options = {"D:Doller","E:Euro"}),group = "invoice-date")
	private String currency;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoiceId")
	@FormElement(listable = true,form = @Form(formClass = "io.jetform.core.entity.InvoiceItem",relation = Relation.ONE_TO_MANY),group = "invoice-items")
	//@FormElement(listable = true,customField = @CustomField(filePath = "invoice-item"),group = "invoice-items")
	private List<InvoiceItem> invoiceItems;
	
	@Column(name = "termsConditions")
	@FormElement(listable = true,textarea = @TextArea(rows = 5 ,cols = 10),group = "terms-conditions")
	private String termsConditions;
	
	@Column(name = "instructions")
	@FormElement(listable = true,textarea = @TextArea(rows = 5,cols = 10),group = "terms-conditions")
	private String instructions;

	
	@Column(name = "subTotal")
	//@FormElement(listable = true,number = @Number(format = "##"), group = "invoice-tax",aggregate=@Aggregate(element="invoiceItems[].amount",type=""))//emum{SUM,AVG,COUNT,MIN,max}
	@FormElement(listable = true,number = @Number(format = "##"), group = "invoice-tax")//emum{SUM,AVG,COUNT,MIN,max}private double subTotal;
	private double subTotal;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	//@FormElement(listable = true,form = @Form(formClass = "io.jetform.core.entity.TaxItem",relation = Relation.ONE_TO_ONE), group = "invoice-tax")
	@FormElement(listable = true, customField = @CustomField(filePath = "tax"), group = "invoice-tax")
	private TaxItem taxItems;
	
	@Column(name = "grandTotal")
	@FormElement(listable = true,number = @Number(format = "##"), group = "invoice-tax")
	private double grandTotal;
	
	@Column(name = "advancePaid")
	@FormElement(listable = true,number = @Number(format = "##"), group = "invoice-tax")
	private double advancePaid;
	
	@Column(name = "balanceDue")
	@FormElement(listable = true,number = @Number(format = "##"), group = "invoice-tax")
	private double balanceDue;
	
	public TaxItem getTaxItems() {
		return taxItems;
	}

	public void setTaxItems(TaxItem taxItems) {
		this.taxItems = taxItems;
	}

	public Invoice() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTermsAndConditions() {
		return termsConditions;
	}

	public void setTermsAndConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getBalanceDue() {
		return balanceDue;
	}

	public void setBalanceDue(double balanceDue) {
		this.balanceDue = balanceDue;
	}
	
	public double getAdvancePaid() {
		return advancePaid;
	}

	public void setAdvancePaid(double advancePaid) {
		this.advancePaid = advancePaid;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public String getTermsConditions() {
		return termsConditions;
	}

	public void setTermsConditions(String termsConditions) {
		this.termsConditions = termsConditions;
	}


	public void add(InvoiceItem tempInvoiceItem) {
		if (invoiceItems == null) {
			invoiceItems = new ArrayList<>();
		}
		invoiceItems.add(tempInvoiceItem);
		// tempInvoiceItem.setInvoice(this);
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [id=").append(id).append(", termsConditions=").append(termsConditions)
				.append(", subTotal=").append(subTotal).append(", serialNo=")
				.append(", invoiceUpload=").append(", invoiceNo=").append(invoiceNo)
				.append(", invoiceDate=").append(invoiceDate).append(", instructions=").append(instructions)
				.append(", grandTotal=").append(grandTotal).append(", dueDate=").append(dueDate).append(", currency=")
				.append(currency).append(", createdDate=").append(", cancelled=")
				.append(", balanceDue=").append(balanceDue).append(", amountReceived=")
				.append(", advancePaid=").append(advancePaid).append(", enabled=")
				.append(", invoiceItems=").append(invoiceItems).append(", client=").append(client)
				.append(", purchaseOrder=").append(purchaseOrder).append(", taxItems=").append(taxItems).append("]");
		return builder.toString();
	}

}
