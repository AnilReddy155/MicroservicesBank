package com.cg.bms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfiguration {
	@Value("${base.customer-uri}")
	private String customerUri;
	@Value("${base.account-uri}")
	private String accountUri;
	@Value("${base.transaction-uri}")
	private String transactionUri;

	@Value("${base.consumer-uri}")
	private String consumerUri;

	public String getConsumerUri() {
		return consumerUri;
	}

	public void setConsumerUri(String consumerUri) {
		this.consumerUri = consumerUri;
	}

	public String getCustomerUri() {
		return customerUri;
	}

	public void setCustomerUri(String customerUri) {
		this.customerUri = customerUri;
	}

	public String getAccountUri() {
		return accountUri;
	}

	public void setAccountUri(String accountUri) {
		this.accountUri = accountUri;
	}

	public String getTransactionUri() {
		return transactionUri;
	}

	public void setTransactionUri(String transactionUri) {
		this.transactionUri = transactionUri;
	}

}
