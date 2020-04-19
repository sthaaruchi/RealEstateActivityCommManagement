package com.realestate.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Su
 * Entity class for re_juristic table
 */

@Entity
@Data
@NoArgsConstructor
@Table(name="re_juristic")
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
public class ReJuristic{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long juristicId;
	
	@Column(nullable = false)
	private String position;
	
	@Column(nullable = false)
	private String emergencyContactNo;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "juristicId")
	@MapsId
	@JsonIgnore private ReUser user;
	
	/*
	 * @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName = "juristicId")
	 * 
	 * @MapsId
	 * 
	 * @JsonIgnore private Re_User user;
	 */
	/*
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JoinTable(name = "responsible_for",
    joinColumns = {
            @JoinColumn(name = "juristic_id",
                    nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "building_id",
                    nullable = false, updatable = false)})
	private Set<ReBuilding> building;
	
}
