package com.counter.maintainer.data.contracts;

import java.util.PriorityQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token implements Comparable<Token> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tokenId")
	private Long tokenId;

	@Column(name="servicePriority")
	private ServicePriority servicePriority;

	@Column(name="customerId")
	private Long customerId;

	@Transient
	private Customer customer;

	@Column(name="comments")
	private String comments;

	@Column(name="actionItems")
	private String actionItems;

	@Column(name="status")
	private TokenStatus status;

	@Column(name="createdDate")
	private DateTime createdDate;

	private Long approxTime;

	private Long counterId;

	@Transient
	private DateTime counterAddedTime;

	@Column(name="serviceType")
	private ServiceType serviceType;
	
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
	public String getActionItems() {
		return actionItems;
	}
	public void setActionItems(String actionItems) {
		this.actionItems = actionItems;
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
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
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
		token.setServiceType(ServiceType.DEPOSIT);
		return token;
	}
	
	public static Token getToken(ServicePriority servicePriority, DateTime dateTime, ServiceType reqServiceType) {
		Token token = new Token();
		token.setServicePriority(servicePriority);
		token.setCreatedDate(dateTime);
		token.setServiceType(reqServiceType);
		return token;
	}
	public int compareTo(Token token) {
		if(this.counterAddedTime.isBefore(token.counterAddedTime)) {
			return -1;
		} else {
			return 1;
		}

		//return this.counterAddedTime.compareTo(token.getCounterAddedTime());
	}

	public DateTime getCounterAddedTime() {
		return counterAddedTime;
	}

	public void setCounterAddedTime(DateTime counterAddedTime) {
		this.counterAddedTime = counterAddedTime;
	}
}
