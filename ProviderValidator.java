package edu.easternct.bigdata;

import java.util.Arrays;
import java.util.List;

public class ProviderValidator {


	String line;
	private boolean clean;

	String[] stateSet = { "AA", "AE", "AK", "AL", "AP", "AR", "AS", "AZ", "CA",
			"CO", "CT", "DC", "DE", "FL", "FM", "GA", "GU", "HI", "IA", "ID",
			"IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MH", "MI", "MN",
			"MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV",
			"NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN",
			"TX", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY" };

	List<String> goodStates = Arrays.asList(stateSet);

	/* error count */
	int errorCnt = 0;
	private	int npi; //0th index in string[]
	private String state; //11th index in string[]
	private String gender; //5th index in string[]
	private String providerType; //12th index in string[]
	private String entityCode;

	public ProviderValidator() {

		this.line = null;
	}

	public ProviderValidator(String line) {

		this.line = line;
	}
	/**
	 * @return clean indicator
	 */
	public boolean isClean() {
		return clean;
	}

	/**
	 * @param clean
	 *            the clean to set
	 */
	public void setClean(boolean clean) {
		this.clean = clean;
	}

	/**
	 * @return the errorCnt
	 */
	public int getErrorCnt() {
		return errorCnt;
	}

	/**
	 * @param errorCnt
	 *            the errorCnt to set
	 */
	public void setErrorCnt(int errorCnt) {
		this.errorCnt = errorCnt;
	}

	public boolean parser() {

		String[] tokens = line.split(",");
		if (tokens.length <15)
			return false;
		// validate that it's not an organization
		if (validateOrg(tokens[6])) {
			// validate npi
			if (validateNPI(tokens[0])) {
				this.npi = Integer.parseInt(tokens[0]);
			} else {
				this.npi = 0;
				errorCnt++;
			}
			
			//validate gender
			if (validateGender(tokens[5])) {
				this.gender =tokens[5];
			} else {
				this.gender =null;
				errorCnt++;
			}
			
			//validate state
			if (validateState(tokens[11])) {
				this.state =tokens[11];
			} else {
				this.state = null;
				errorCnt++;
			}
			
			//validate Provider type
			if (validatePT(tokens[13])) {
				this.providerType = tokens[13];
			} else {
				this.providerType = null;
				errorCnt++;
			}
			
		} else errorCnt++;
		
			if (errorCnt > 0) {
				setClean(false);
				return false;
			} else
				setClean(true);
			return true;

		
	}

	public boolean validateNPI(String npi) {

		boolean valid = true;
		//if it isn't a digit or is null, it's false
		if ((npi == null) || (!npi.matches("^\\d+$")))
			valid = false;

		return valid;
	}

	public boolean validateGender(String gender) {

		boolean valid = true;
		//not a very progressive measure, but can only be F or M. 
		if ((gender == null) || (!gender.matches("[FM]")))
			valid = false;

		return valid;
	}
	public boolean validateState(String st){
		boolean valid = true;
		
		if (!goodStates.contains(st))
			valid = false;

		return valid;
	}

	public boolean validateOrg(String org) {
		boolean valid = true;

		if ((org == null) || (!org.matches("[I]")))
			valid = false;

		return valid;
	}
	
	public boolean validatePT(String pt){
		boolean valid = true;

		if ((pt == null) || (!pt.matches("[a-zA-Z]+")))
			valid = false;

		return valid;
	}
	//i wanted to create a method to use the valid data to populate Provider classes directly
	public Provider generateProvider(){ 
			return new Provider(this.npi, this.state,this.gender, this.providerType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  "npi: " + npi  + " Gender: " + gender +
				" Provider Type: " + providerType + " State: " + state +
				" Entity Code: " + entityCode + " Clean: " + clean;
	}

}
