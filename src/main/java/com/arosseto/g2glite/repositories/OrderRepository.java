package com.arosseto.g2glite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arosseto.g2glite.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
