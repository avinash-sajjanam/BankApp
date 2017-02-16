package com.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "CheckUser",
	query = "select * from login where username=:username and password=:password",
        resultClass = User.class
	)
})
@Entity
											
@Table(name = "Login")
public class User implements Serializable  {
	

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="accountnumber",insertable=false,updatable=false)
	private long accountnumber;
	
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@OneToOne(targetEntity=Account.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="accountnumber",referencedColumnName="accountnumber")
	private Account account;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
