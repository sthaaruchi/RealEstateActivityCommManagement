package com.realestate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.ReBuilding;

/**
 * 
 * @author shruchi
 * JPA Repository class that has all the methods interacting to re_building table
 */

public interface ReBuildingJPADao extends JpaRepository<ReBuilding, Long> {

}
