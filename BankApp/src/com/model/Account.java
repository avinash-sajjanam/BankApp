package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="accounts")
public class Account implements Serializable	 {

	@Id
	@Column(name="accountnumber")
	private long accountnumber;
	
	@Column(name="checking")
	private int checking;
	
	@Column(name="saving")
	private int saving;

	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public int getChecking() {
		return checking;
	}
	public void setChecking(int checking) {
		this.checking = checking;
	}
	public int getSaving() {
		return saving;
	}
	public void setSaving(int saving) {
		this.saving = saving;
	}
	
}
