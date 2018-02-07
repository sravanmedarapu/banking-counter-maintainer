package com.counter.maintainer.data.contracts;

import java.util.PriorityQueue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

public class Token implements Comparable<Token> {
	private Long tokenId;
	private TypeOfService typeOfService;
	private Long customerId;
	private Customer customer;
	private String comments;
	private String actionItems;
	private TokenStatus status;
	private DateTime createdDate;
	@JsonIgnore
	private Long approxTime;
	private Long counterId;
	private ServiceType serviceType;
	
	public Long getTokenId() {
		return tokenId;
	}
	public void setTokenId(Long tokenId ) {
		this.tokenId = tokenId;
	}
	public TypeOfService getTypeOfService() {
		return typeOfService;
	}
	public void setTypeOfService(TypeOfService typeOfService) {
		this.typeOfService = typeOfService;
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
		return "Token [tokenId=" + tokenId + ", typeOfService=" + typeOfService + ", customerId=" + customerId
				+ ", comments=" + comments + ", actionItems=" + actionItems + ", status=" + status + "]";
	}
	
	public static void main(String[] args) {
		PriorityQueue<Token> queue = new PriorityQueue<Token>();
		queue.add(getToken());
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().minusMinutes(2), ServiceType.CHECKDEPISIT));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(1),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(1),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(2),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(2),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(2),ServiceType.WITHDRAWL));
		
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(2),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(3),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(4),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(6),ServiceType.WITHDRAWL));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().plusMinutes(7),ServiceType.WITHDRAWL));
		
		
		queue.add(getToken(TypeOfService.PREMIUM, DateTime.now().plusMinutes(8),ServiceType.DEPOSIT));
		queue.add(getToken(TypeOfService.PREMIUM, DateTime.now().plusMinutes(10),ServiceType.DEPOSIT));
		queue.add(getToken(TypeOfService.REGULAR, DateTime.now().minusMinutes(2),ServiceType.ACCOUNT_CLOSE));
	}
	
	public static Token getToken() {
		Token token = new Token();
		token.setTypeOfService(TypeOfService.PREMIUM);
		token.setCreatedDate(DateTime.now());
		token.setServiceType(ServiceType.DEPOSIT);
		return token;
	}
	
	public static Token getToken(TypeOfService typeOfService, DateTime dateTime, ServiceType reqServiceType) {
		Token token = new Token();
		token.setTypeOfService(typeOfService);
		token.setCreatedDate(dateTime);
		token.setServiceType(reqServiceType);
		return token;
	}
	public int compareTo(Token o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
