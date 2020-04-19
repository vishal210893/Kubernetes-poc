package com.solum.kubernetes.poc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Controller {
	
	Logger log = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private InstanceInformationService service;

	@Autowired
	private Environment env;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<HashMap<String, String>> welcomeMapping() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		HashMap<String, String> start = new HashMap<>();
		start.put("msg", "Kubernetes service is running");
		start.put("version", "1.0.0");
		start.put("timestamp", strDate);
		start.put("env", env.getProperty("environment"));
		start.put("service", service.retrieveInstanceInfo());
		log.info(start.toString());
		return ResponseEntity.ok().body(start);
	}

	/// hello-world/path-variable/in28minutes
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
