package com.realestate.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Su
 * Entity class for re_user table
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="re_user")
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
public class ReUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Email
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String role;

	@Column(nullable = false)
	private boolean active;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String phone;
	
	/*
	 * @Column(nullable = false) private String updated_by;
	 * 
	 * @Column(nullable = false) private String updated_date;
	 * 
	 * private String created_by;
	 * 
	 * @Column(nullable = false) private String created_date;
	 */
	
	@OneToOne(mappedBy="userForResiident", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	@JsonIgnore
	private ReResident resisdent;
	
	@OneToOne(mappedBy="user", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	@JsonIgnore
	private ReJuristic juristic;
	
	@ManyToMany(mappedBy = "residents") 
	//let the other side create the association table
	//Set has better performance than List in many-to-many
	private Set<ReEvent> events;
}
