package br.com.salao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import br.com.salao.entity.Order;
import br.com.salao.repository.OrderRepository;
import br.com.salao.repository.OrderRepositorySpringData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
		
	//private OrderRepository orderRepository;	
	
	private OrderRepositorySpringData orderRepository;

	@Autowired
	public OrderController(OrderRepositorySpringData orderRepository) {	
		this.orderRepository = orderRepository;
	}

	@GetMapping("/current")
	public String orderForm() {		
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
		if( errors.hasErrors() ) {
			log.info("Order submitted: " + order);
			System.out.println(errors);
			return "orderForm";
		}
		orderRepository.save(order);
		sessionStatus.setComplete();
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
}
