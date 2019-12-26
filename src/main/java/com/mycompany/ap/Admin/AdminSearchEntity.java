package com.mycompany.ap.Admin;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//import org.hibernate.jpa.
//org.hibernate.jpa.HibernatePersistenceProvider 


@Table(name ="OA_RNR")
@Entity
@SequenceGenerator(
	    name = "OA_SEQ_GENERATOR",
	    sequenceName = "oaSequence",
	    initialValue = 1,
	    allocationSize = 1)
public class AdminSearchEntity {

	@Id
	@Column(columnDefinition = "idx")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_SEQ_GENERATOR")

	public Long idx;
	public AdminSearchEntity(){}
	public AdminSearchEntity(Long idx, String company, String system, String pic, String contact) {
		//super();
		this.idx = idx;
		this.company = company;
		this.system = system;
		this.pic = pic;
		this.contact = contact;
	}

	public boolean hasNull()
	{
		if(company.equals("") ||system.equals("") ||pic.equals("") ||contact.equals(""))
			return true;
		return false;
		
	}
	@Override
	public String toString() {
		return "SearchEntity [idx=" + idx + ", company=" + company + ", system=" + system + ", pic=" + pic
				+ ", contact=" + contact + "]";
	}

	@Column(columnDefinition = "company")
	public String company;
	
	@Column(columnDefinition = "system")
	public String system;
	
	@Column(columnDefinition = "pic")
	public String pic;
	
	@Column(columnDefinition = "contact")
	public String contact;

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	

}
