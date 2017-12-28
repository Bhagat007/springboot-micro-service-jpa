package com.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int addressid;
	    private String name;
	    

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		protected Address() {}

	    public Address(int addressid, String name) {
	        this.addressid = addressid;
	        this.name = name;
	    }
	    public int getAddressid() {
			return addressid;
		}

		public void setAddressid(int addressid) {
			this.addressid = addressid;
		}

	

		@Override
	    public String toString() {
	        return String.format(
	                "Customer[address='%s', addressid='%s']",name, addressid);
	    }

}
