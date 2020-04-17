package com.realestate.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * @author shruchi
 * Entity class for re_building table
 */

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "re_building") // alternative table name manually
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
public class ReBuilding extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_id")
	private Long buildingId;
	
	@Column(name = "building_name")
	private String name;



	@ManyToMany(mappedBy = "buildings") 
	//let the other side create the association table
	//Set has better performance than List in many-to-many
	private Set<ReEvent> events;
	
	@ManyToMany(mappedBy = "building")
	private Set<ReJuristic> juristic;
	
//	@Column(name = "created_by")
//	private String createdBy;
//	
//	@Column(name = "created_date")
//	private Date createdDate;
//	
//	@Column(name = "updated_by")
//	private String updatedBy;
//	
//	@Column(name = "updated_date")
//	private Date updatedDate;
	
//	@PrePersist
//    protected void onCreate() {
//		createdDate = new Date();
//		createdBy = "Ruchi";
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//    	updatedDate = new Date();
//    	updatedBy = "Su";
//    }
	
}

