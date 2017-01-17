package edu.easternct.bigdata;

import java.io.Serializable;

public class Provider implements Serializable, Comparable<Provider>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3176395942836540366L;
	
	private	int npi; //0th index in string[]
	private String state; //11th index in string[]
	private String gender; //5th index in string[]
	private String providerType; //12th index in string[]
	//will be populated via the validator
	public Provider(int id, String st, String g, String pt){
		this.npi = id;
		this.state = st;
		this.gender = g;
		this.providerType = pt;
		
	}
	//empty provider
	public Provider(){
		this.npi = 0;
		this.state = null;
		this.gender = null;
		this.providerType = null;
		
	}
	

	public int getNpi() {
		return npi;
	}


	public void setNpi(int npi) {
		this.npi = npi;
	}


	public String getState() {
		return state;
	}


	@Override
	public String toString() {
		return npi  + "," + state + "," + gender + "," + providerType;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getProviderType() {
		return providerType;
	}


	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	@Override
	public int compareTo(Provider p) {
		// TODO Auto-generated method stub
		if(this.equals(p))
		{
		 return 0;
		}
		else if(this.getNpi() > p.getNpi()){
			return 1;
		}
		else return -1;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + npi;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (npi != other.npi)
			return false;
		return true;
	}
	
}
