package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="owner")
public class ListOwners {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Column(name = "OWNER_ID")
	private int owner;
	@Column(name = "OWNER_NAME")
	private String ownerName;
	
	public ListOwners() {
	}
	
	public ListOwners(int owner) {
		this.owner = owner;
	}
	public ListOwners(int owner, String ownerName) {
		this.owner = owner;
		this.ownerName = ownerName;
	}

	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}
	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String toString() {
		return ownerName;
	}

}
