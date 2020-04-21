package com.realestate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Su
 * Entity class for re_resident table
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="re_resident")
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
public class ReResident{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long residentId;
	
	@Column(nullable = false)
	private String roomNo;
	
	@Column(nullable = false)
	private String floorNo;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "residentId")
	@MapsId
	@JsonIgnore private ReUser userForResiident;
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "live_in_building_id")
	@MapsId
	@JsonIgnore private ReBuilding building;
	/*
	 * @OneToOne(mappedBy="resisdent", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL)
	 * 
	 * @JsonIgnore private Re_User user;
	 */
}
