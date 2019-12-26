package com.mycompany.ap.Normal;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//import org.hibernate.jpa.
//org.hibernate.jpa.HibernatePersistenceProvider 




@Table(name ="OA_RNR")
@Entity
public class NormalSearchEntity {

	@Id
	@Column(columnDefinition = "idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long idx;
	public NormalSearchEntity(){}
	public NormalSearchEntity(Long idx, String company, String system, String pic, String contact) {
		//super();
		this.idx = idx;
		this.company = company;
		this.system = system;
		this.pic = pic;
		this.contact = contact;
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
