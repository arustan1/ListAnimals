package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "animals")
public class ListAnimals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "TYPE")
	private String type;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "OWNER_ID")
	private ListOwners owner;

	public ListAnimals() {

	}

	public ListAnimals(String name, String type, ListOwners owner) {
		this.owner = owner;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ListOwners getOwner() {
		return owner;
	}

	public void setOwner(ListOwners owner) {
		this.owner = owner;
	}

	public String toString() {
		return "Pet: " + name + " : " + type + " Owner:" + owner.getOwnerName();
	}
}
