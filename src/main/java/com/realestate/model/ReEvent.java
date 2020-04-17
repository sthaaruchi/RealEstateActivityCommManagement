package com.realestate.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * @author shruchi
 * Entity class for re_event table
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
public class ReEvent extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "event_title")
	private String title;
	
	@Column(name = "event_details")
	private String details;
	
	@Column(name = "event_date")
	private Date eventDate;
	
	@Column(name = "event_location")
	private String location;
	
	@Column(name = "event_duration")
	private String duration;
	
	@Column(name = "event_publish_Date")
	private Date eventPublishDate;
	
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
	
	@ManyToMany
	@JoinTable(
	  name = "event_building", 
	  joinColumns = @JoinColumn(name = "event_id"), 
	  inverseJoinColumns = @JoinColumn(name = "building_id"))
	@JsonIgnore
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<ReBuilding> buildings;
	
	@ManyToMany
	@JoinTable(
	  name = "event_joined", 
	  joinColumns = @JoinColumn(name = "event_id"), 
	  inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<ReUser> residents;


//	@PrePersist
//    protected void onCreate() {
//		createdDate = new Date();
//		createdBy = "Ruchi";
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//    	createdDate = getCreatedDate();
//    	createdBy = getCreatedBy();
//    	updatedDate = new Date();
//    	updatedBy = "Su";
//    }
}
