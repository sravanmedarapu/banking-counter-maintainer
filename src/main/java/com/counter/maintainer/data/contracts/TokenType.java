package com.counter.maintainer.data.contracts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public enum TokenType {
	
	WITHDRAW {
		public int getWaitTimeInMins() {
			return 5;
		}

		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(ServiceType.VERIFICATION, ServiceType.WITHDRAW));
		}

	}, DEPOSIT {
		public int getWaitTimeInMins() {
			return 5;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<Enum>(Arrays.asList(ServiceType.VERIFICATION, ServiceType.WITHDRAW));
		}
	}, CHECK_DEPOSIT {
		public int getWaitTimeInMins() {
			return 10;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(ServiceType.VERIFICATION, ServiceType.CHECK_DEPOSIT));
		}
	}, ACCOUNT_CLOSE {
		public int getWaitTimeInMins() {
			return 15;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<Enum>(Arrays.asList(ServiceType.VERIFICATION, ServiceType.MANAGER_APPROVAL, ServiceType.ACCOUNT_CLOSE));
		}
	}, ACCOUNT_OPEN {
		public int getWaitTimeInMins() {
			return 15;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(ServiceType.VERIFICATION, ServiceType.MANAGER_APPROVAL, ServiceType.ACCOUNT_OPEN));
		}
	};

	public abstract int getWaitTimeInMins();

	public abstract Queue<Enum> getActionTimes();

}

