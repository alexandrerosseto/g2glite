package com.arosseto.g2glite.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.arosseto.g2glite.entities.enums.ClientType;
import com.arosseto.g2glite.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique=true)
	private String email;
	private String clientPersonalIdNumber;
	private Integer clientType;
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="tb_contact")
	private Set<String> phone = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="tb_profile")
	private Set<Integer> profiles = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	private String imageUrl;
	
	public Client() {
		//Every user will be a client necessarily
		addProfile(Profile.CLIENT);
	}

	public Client(Long id, String name, String email, String clientPersonalIdNumber, ClientType clientType, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.clientPersonalIdNumber = clientPersonalIdNumber;
		this.clientType = (clientType==null) ? null : clientType.getCode();
		this.password = password;
		addProfile(Profile.CLIENT);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Set<Profile> getProfile() {
		return profiles.stream().map(x -> Profile.valueOf(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCode());
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
