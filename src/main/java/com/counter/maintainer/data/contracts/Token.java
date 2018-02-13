package com.counter.maintainer.data.contracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.Queue;

public class Token implements Comparable<Token> {
	private Long tokenId;
	private ServicePriority servicePriority;
	private Long customerId;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Customer customer;
	@JsonIgnore
	private String comments;
	private TokenStatus status;
	@JsonIgnore
	private DateTime createdDate;
	@JsonIgnore
	private Boolean inQ;
	@JsonIgnore
	private Long approxTime;
	private Long counterId;

	@JsonIgnore
	private DateTime counterAddedTime;

	private TokenType tokenType;

	@JsonIgnore
	private Queue<Enum> actionItems = null;
	
	public Long getTokenId() {
		return tokenId;
	}
	public void setTokenId(Long tokenId ) {
		this.tokenId = tokenId;
	}
	public ServicePriority getServicePriority() {
		return servicePriority;
	}
	public void setServicePriority(ServicePriority servicePriority) {
		this.servicePriority = servicePriority;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Queue<Enum> getActionItems() {
		if(actionItems == null) {
			actionItems = tokenType.getActionTimes();
		}
		return actionItems;
	}
	public TokenStatus getStatus() {
		return status;
	}
	public void setStatus(TokenStatus status) {
		this.status = status;
	}
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	public long getApproxTime() {
		return approxTime;
	}
	public void setApproxTime(long approxTime) {
		this.approxTime = approxTime;
	}
	public long getCounterId() {
		return counterId;
	}
	public void setCounterId(long counterId) {
		this.counterId = counterId;
	}
	public TokenType getTokenType() {
		return tokenType;
	}
	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public Enum<ServiceType> peekNextServiceType() {
		return getActionItems().peek();
	}

	public Enum<ServiceType> pollNextServiceType() {
		return getActionItems().poll();
	}

	public Boolean getInQ() {
		return inQ;
	}

	public void setInQ(Boolean inQ) {
		this.inQ = inQ;
	}

	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", servicePriority=" + servicePriority + ", customerId=" + customerId
				+ ", comments=" + comments + ", actionItems=" + actionItems + ", status=" + status + "]";
	}
	

	public static Token getToken() {
		Token token = new Token();
		token.setServicePriority(ServicePriority.PREMIUM);
		token.setCreatedDate(DateTime.now());
		token.setTokenType(TokenType.DEPOSIT);
		return token;
	}
	
	public static Token getToken(ServicePriority servicePriority, DateTime dateTime, TokenType reqTokenType) {
		Token token = new Token();
		token.setServicePriority(servicePriority);
		token.setCreatedDate(dateTime);
		token.setTokenType(reqTokenType);
		return token;
	}
	public int compareTo(Token token) {
		if (this.counterAddedTime.isBefore(token.counterAddedTime)) {
			return -1;
		} else {
			return 1;
		}
	}

	public DateTime getCounterAddedTime() {
		return counterAddedTime;
	}

	public void setCounterAddedTime(DateTime counterAddedTime) {
		this.counterAddedTime = counterAddedTime;
	}
}
