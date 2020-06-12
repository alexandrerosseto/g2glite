package com.arosseto.g2glite.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.arosseto.g2glite.entities.Address;
import com.arosseto.g2glite.entities.CardPayment;
import com.arosseto.g2glite.entities.Category;
import com.arosseto.g2glite.entities.City;
import com.arosseto.g2glite.entities.Client;
import com.arosseto.g2glite.entities.InvoicePayment;
import com.arosseto.g2glite.entities.Order;
import com.arosseto.g2glite.entities.OrderItem;
import com.arosseto.g2glite.entities.Payment;
import com.arosseto.g2glite.entities.Product;
import com.arosseto.g2glite.entities.State;
import com.arosseto.g2glite.entities.enums.ClientType;
import com.arosseto.g2glite.entities.enums.OrderStatus;
import com.arosseto.g2glite.entities.enums.PaymentStatus;
import com.arosseto.g2glite.repositories.AddressRepository;
import com.arosseto.g2glite.repositories.CategoryRepository;
import com.arosseto.g2glite.repositories.CityRepository;
import com.arosseto.g2glite.repositories.ClientRepository;
import com.arosseto.g2glite.repositories.OrderItemRepository;
import com.arosseto.g2glite.repositories.OrderRepository;
import com.arosseto.g2glite.repositories.PaymentRepository;
import com.arosseto.g2glite.repositories.ProductRepository;
import com.arosseto.g2glite.repositories.StateRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 
		Category cat4 = new Category(null, "Office");
		Category cat5 = new Category(null, "Garden");
		Category cat6 = new Category(null, "Perfume"); 
		Category cat7 = new Category(null, "Design");
		Category cat8 = new Category(null, "Butchery");
		Category cat9 = new Category(null, "Bakery"); 
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client clt1 = new Client(null, "Mary Gross", "maria@gmail.com", "1111111111111111111", ClientType.Personal);
		clt1.getPhone().addAll(Arrays.asList("1111111", "2222222"));
		
		Address ad1 = new Address(null, "Rua Flores", "300", "Building 303", "Garden", "3829665", clt1, c1);
		Address ad2 = new Address(null, "Av. Matos", "105", "Building 803", "Center", "552222454", clt1, c2);
		
		clt1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		
		clientRepository.saveAll(Arrays.asList(clt1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order o1 = new Order(null, sdf.parse("30/09/2019 10:55").toInstant(), OrderStatus.SHIPPED, clt1, ad1);
		Order o2 = new Order(null, sdf.parse("10/10/2019 13:55").toInstant(), OrderStatus.DELIVERED, clt1, ad2);
		
		Payment pay1 = new CardPayment(null, sdf.parse("30/09/2019 10:55").toInstant(), o1, PaymentStatus.SETTLED, 6);
		o1.setPayment(pay1);
		
		Payment pay2 = new InvoicePayment(null, null, o2, PaymentStatus.PENDING, sdf.parse("15/10/2019 00:00"), null);
		o2.setPayment(pay2);
		
		clt1.getOrders().addAll(Arrays.asList(o1, o2));
		
		orderRepository.saveAll(Arrays.asList(o1, o2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem ip1 = new OrderItem(o1, p1, 0.00, 1, 2000.00);
		OrderItem ip2 = new OrderItem(o1, p3, 0.00, 2, 80.00);
		OrderItem ip3 = new OrderItem(o2, p2, 100.00, 1, 800.00);
		
		o1.getItems().addAll(Arrays.asList(ip1, ip2));
		o2.getItems().addAll(Arrays.asList(ip3));
		
		p1.getItems().addAll(Arrays.asList(ip1));
		p2.getItems().addAll(Arrays.asList(ip3));
		p3.getItems().addAll(Arrays.asList(ip2));
		
		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
