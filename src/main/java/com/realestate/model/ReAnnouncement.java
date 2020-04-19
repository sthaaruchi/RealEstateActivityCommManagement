package com.realestate.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Su
 * Entity class for re_announcement table
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="re_announcement")
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
public class ReAnnouncement extends Auditable<String>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long announcementId;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String description;
	
	private Date publishAnnouncementDate;
	
	@Column(nullable=false)
	private String editableBy;
	
	@Column(nullable=false)
	private boolean isPublished;
	
	private String userGroup;
	
	@ManyToMany()
	@JsonIgnore
	@JoinTable(name = "announce_for",
    joinColumns = {
            @JoinColumn(name = "announcement_id",
                    nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "building_id",
                    nullable = false, updatable = false)})
	private Set<ReBuilding> buildings;
}
