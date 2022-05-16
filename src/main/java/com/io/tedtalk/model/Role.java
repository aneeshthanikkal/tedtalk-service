package com.io.tedtalk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "T_ROLES")
public class Role {
	@Id
	@Column(name = "ID", length=36)
	private String id;
	
	@Column(name = "NAME")
	private String name;
}