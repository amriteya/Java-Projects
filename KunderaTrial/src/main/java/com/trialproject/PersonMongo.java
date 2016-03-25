package com.trialproject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON", schema = "testDB@mongo_pu")
public class PersonMongo {

	public PersonMongo() {
	}

	public PersonMongo(String pId, String pName, int pAge) {
		this.age = pAge;
		this.personId = pId;
		this.personName = pName;
	}

	@Id
	@Column(name = "PERSON_ID")
	private String personId;

	/** The person name. */
	@Column(name = "PERSON_NAME")
	private String personName;

	/** The age. */
	@Column(name = "AGE")
	private int age;

	// setters and getters.

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String pName) {
		this.personName = pName;
	}

}
