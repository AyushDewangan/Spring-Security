package com.example.demo.ag.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("ag_account_transactions")
public class AGAccountTransactions {
	
	@Id
	@Field( "transaction_id")
	private String transactionId;
	
	@Field("account_number")
	private long accountNumber;
	
	@Field( "customer_id")
	private int customerId;
	
	@Field("transaction_dt")
	private Date transactionDt;
	
	@Field("transaction_summary")
	private String transactionSummary;
	
	@Field("transaction_type")
	private String transactionType;
	
	@Field("transaction_amt")
	private int transactionAmt;
	
	@Field("closing_balance")
	private int closingBalance;
	
	@Field("create_dt")
	private String createDt;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getTransactionDt() {
		return transactionDt;
	}

	public void setTransactionDt(Date transactionDt) {
		this.transactionDt = transactionDt;
	}

	public String getTransactionSummary() {
		return transactionSummary;
	}

	public void setTransactionSummary(String transactionSummary) {
		this.transactionSummary = transactionSummary;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmt() {
		return transactionAmt;
	}

	public void setTransactionAmt(int transactionAmt) {
		this.transactionAmt = transactionAmt;
	}

	public int getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(int closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}
}
