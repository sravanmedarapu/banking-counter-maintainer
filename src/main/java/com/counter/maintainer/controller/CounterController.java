package com.counter.maintainer.controller;

import java.util.List;

import com.counter.maintainer.data.contracts.CounterDetails;
import com.counter.maintainer.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.counter.maintainer.data.contracts.Customer;
import com.counter.maintainer.service.CustomerService;

@RestController
public class CounterController {

	@Autowired
	private CounterService counterService;

	/**
	 *
	 * returns available counters details along with current counter queue ticketId list
	 *
	 * @return
	 */
	@RequestMapping("/api/counter/status")
	public List<CounterDetails> getCounterStatus() {

		return counterService.getCounterStatus();
	}

	/**
	 *
	 * returns available counters details along with current counter queue ticketId list for given branch
	 *
	 * @return
	 */
	@RequestMapping("/api/branch/{branchName}/counter/status")
	public List<CounterDetails> getCounterStatus(@PathVariable String branchName) {

		return counterService.getCounterStatus(branchName);
	}

	

}
