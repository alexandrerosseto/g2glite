package com.arosseto.g2glite.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.arosseto.g2glite.entities.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tb_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String clientPersonalIdNumber;
	private Integer clientType;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "client")
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="tb_contact")
	private Set<String> phone = new HashSet<>();
	
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public Client() {
	}

	public Client(Long id, String nome, String email, String clientPersonalIdNumber, ClientType clientType) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.clientPersonalIdNumber = clientPersonalIdNumber;
		this.clientType = clientType.getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClientPersonalIdNumber() {
		return clientPersonalIdNumber;
	}

	public void setClientPersonalIdNumber(String clientPersonalIdNumber) {
		this.clientPersonalIdNumber = clientPersonalIdNumber;
	}

	public ClientType getClientType() {
		return ClientType.valueOf(clientType);
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getCode();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhone() {
		return phone;
	}

	public void setPhone(Set<String> phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
