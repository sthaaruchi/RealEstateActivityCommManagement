package com.realestate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.realestate.model.ReBuilding;

/**
 * 
 * @author shruchi
 * JPA Repository class that has all the methods interacting to re_building table
 */

public interface ReBuildingJPADao extends JpaRepository<ReBuilding, Long> {
	
	@Query(value="SELECT * FROM RE_BUILDING WHERE BUILDING_ID IN "
			+ "(SELECT BUILDING_ID FROM RESPONSIBLE_FOR WHERE JURISTIC_ID = ?)", nativeQuery=true)
	List<ReBuilding> findByJuristicResponsible(long userId);

}
