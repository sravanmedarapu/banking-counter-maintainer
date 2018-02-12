package com.counter.maintainer.data.contracts;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static com.counter.maintainer.data.contracts.ServiceType.MANAGER_APPROVAL;
import static com.counter.maintainer.data.contracts.ServiceType.VERIFICATION;

public enum TokenType {
	
	WITHDRAW {
		public int getWaitTimeInMins() {
			return 5;
		}

		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(VERIFICATION, WITHDRAW));
		}

	}, DEPOSIT {
		public int getWaitTimeInMins() {
			return 5;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(VERIFICATION, WITHDRAW));
		}
	}, CHECK_DEPOSIT {
		public int getWaitTimeInMins() {
			return 10;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(VERIFICATION, CHECK_DEPOSIT));
		}
	}, ACCOUNT_CLOSE {
		public int getWaitTimeInMins() {
			return 15;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(VERIFICATION, MANAGER_APPROVAL, ACCOUNT_CLOSE));
		}
	}, ACCOUNT_OPEN {
		public int getWaitTimeInMins() {
			return 15;
		}
		public Queue<Enum> getActionTimes() {
			return new LinkedList<>(Arrays.asList(VERIFICATION, MANAGER_APPROVAL, ACCOUNT_OPEN));
		}
	};

	public abstract int getWaitTimeInMins();

	public abstract Queue<Enum> getActionTimes();

}

