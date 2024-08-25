package com.ajay.productdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajay.productdetails.entity.Bike;

public interface BikeRepository extends JpaRepository<Bike,Long> {

}
