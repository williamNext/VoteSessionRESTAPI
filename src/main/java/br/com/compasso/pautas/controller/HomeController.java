package br.com.compasso.pautas.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public void swaggerUi(HttpServletResponse response) throws IOException {
		response.sendRedirect("swagger-ui.html");
	}
}
