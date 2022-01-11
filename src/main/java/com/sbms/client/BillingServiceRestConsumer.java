package com.sbms.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BillingServiceRestConsumer {

	@Autowired
	private LoadBalancerClient client;

	public String getBillingInfo() {
		// get less LoadFactory Service Instance
		ServiceInstance si = client.choose("Billing-Service");
		String url = si.getUri() + "/billing/info";
		RestTemplate rt = new RestTemplate();
		String response = rt.getForObject(url, String.class);
		return response;
	}
}
