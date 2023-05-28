package com.cg.bankapplication.pojos;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
private int customerId;
private String fName;
private String lName;
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getfName() {
	return fName;
}
public void setfName(String fName) {
	this.fName = fName;
}
public String getlName() {
	return lName;
}
public void setlName(String lName) {
	this.lName = lName;
}
@Override
public String toString() {
	return "Customer [customerId=" + customerId + ", fName=" + fName + ", lName=" + lName + "]";
}
public Customer(int customerId, String fName, String lName) {
	super();
	this.customerId = customerId;
	this.fName = fName;
	this.lName = lName;
}
public Customer() {
	super();
	// TODO Auto-generated constructor stub
}

}
