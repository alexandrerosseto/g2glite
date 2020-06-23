package com.arosseto.g2glite.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arosseto.g2glite.entities.InvoicePayment;
import com.arosseto.g2glite.entities.Order;
import com.arosseto.g2glite.entities.OrderItem;
import com.arosseto.g2glite.entities.enums.OrderStatus;
import com.arosseto.g2glite.entities.enums.PaymentStatus;
import com.arosseto.g2glite.repositories.OrderItemRepository;
import com.arosseto.g2glite.repositories.OrderRepository;
import com.arosseto.g2glite.repositories.PaymentRepository;
import com.arosseto.g2glite.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ClientService clientService;
	
	public List<Order> findAll() {
		return repo.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setMoment(new Date().toInstant());
		obj.setClient(clientService.findById(obj.getClient().getId()));
		obj.getPayment().setPaymentStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		obj.setOrderStatus(OrderStatus.WATTING_PAYMENT);
		if (obj.getPayment() instanceof InvoicePayment) {
			InvoicePayment ip = (InvoicePayment) obj.getPayment();
			invoiceService.fillInvoicePayment(ip, obj.getMoment());
		}
		System.out.println(obj.getPayment().getPaymentStatus().getDescription());
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.findById(oi.getProduct().getId()));
			oi.setPrice(productService.findById(oi.getProduct().getId()).getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		System.out.println(obj);
		return obj;
	}
}
