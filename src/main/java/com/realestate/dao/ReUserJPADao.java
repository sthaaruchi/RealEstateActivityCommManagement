package com.realestate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.realestate.model.ReUser;

public interface ReUserJPADao extends JpaRepository<ReUser, Long>{
	
	/*
	 * @Query(
	 * value="SELECT * FROM user JOIN subscribing_list ON user.id = subscribing_list.uid WHERE subscribing_list.lid=:lid"
	 * ,nativeQuery=true) List<User> findUsersByList(int lid);
	 */
	
	ReUser findByUsername(String username);

}
