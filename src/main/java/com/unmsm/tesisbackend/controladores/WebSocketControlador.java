package com.unmsm.tesisbackend.controladores;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "${client.url}", maxAge=3600)
public class WebSocketControlador {

	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public String greeting(String name) throws Exception {		System.out.println(name);
	    return name;
	}
}