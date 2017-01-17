package edu.easternct.bigdata;

import java.util.ArrayList;
import java.util.Collections;

import scala.tools.nsc.settings.AdvancedScalaSettings.X;

public class ProviderTester {
	
	
	
	public static void main(String[] args){
		//valid input, taken from the clean medicare csv file, all other test cases are based on this and altered to ensure they are invalid
	 String testprov1 = "1003067661,ALTENBURGER,DANA,L,MD,F,I,10833 LE CONTE AVE,,LOS ANGELES,900953075,CA,US,Pathology,Y,F," +
			"88312,Special stains group 1, 31 , 24 , 24 ,$25.33,$0.00,$124.30,$0.00,$20.26,$0.00";
	//invalid test for gender
	 String testprov2 = "1003067661,ALTENBURGER,DANA,L,MD,,I,10833 LE CONTE AVE,,LOS ANGELES,900953075,CA,US,Pathology,Y,F," +
			"88312,Special stains group 1, 31 , 24 , 24 ,$25.33,$0.00,$124.30,$0.00,$20.26,$0.00";
	
	//invalid test for state
	 String testprov3 = "1003067661,ALTENBURGER,DANA,L,MD,F,I,10833 LE CONTE AVE,,LOS ANGELES,900953075,XX,US,Pathology,Y,F," +
			"88312,Special stains group 1, 31 , 24 , 24 ,$25.33,$0.00,$124.30,$0.00,$20.26,$0.00";
	//invalid test for provider type
	 String testprov4 = "1003067661,ALTENBURGER,DANA,L,MD,F,I,10833 LE CONTE AVE,,LOS ANGELES,900953075,CA,US,,Y,F," +
			"88312,Special stains group 1, 31 , 24 , 24 ,$25.33,$0.00,$124.30,$0.00,$20.26,$0.00";
	//invalid test for npi
	String testprov5 = "beepboopbeep,ALTENBURGER,DANA,L,MD,F,I,10833 LE CONTE AVE,,LOS ANGELES,900953075,CA,US,Pathology,Y,F," +
			"88312,Special stains group 1, 31 , 24 , 24 ,$25.33,$0.00,$124.30,$0.00,$20.26,$0.00";
		
		Provider p1 = new Provider(1003067661,"MD","F","Pathology");
		Provider p2 = new Provider(1003067661,"MD","F","Pathology");
		Provider p3 = new Provider(2,"MD","F","Pathology");
		//shows equals works
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
		ArrayList<Provider> al1 = new ArrayList<Provider>();
		al1.add(p1);
		al1.add(p2);
		al1.add(p3);
		al1.forEach(x -> System.out.println(x));
		Collections.sort(al1);
		System.out.println(); 
		//shows sort function works
		al1.forEach(x -> System.out.println(x));
		System.out.println();
		//shows unique hashcodes for different pid
		al1.forEach(x -> System.out.println(x.hashCode()));
		System.out.println();
		al1.forEach(x -> System.out.println(x.toString()));
		System.out.println();
		ArrayList<ProviderValidator> pvTest = new ArrayList<ProviderValidator>();
		pvTest.add(new ProviderValidator(testprov1));
		pvTest.add(new ProviderValidator(testprov2));
		pvTest.add(new ProviderValidator(testprov3));
		pvTest.add(new ProviderValidator(testprov4));
		pvTest.add(new ProviderValidator(testprov5));
		pvTest.forEach(x -> x.parser());
		pvTest.forEach(x -> System.out.println(x.isClean()));
		System.out.println();
		pvTest.forEach(x -> System.out.println(x.toString()));
		System.out.println();
		ArrayList<Provider> validPvdrs = new ArrayList<Provider>();
		pvTest.forEach(x -> {
			if(x.isClean())
			{ validPvdrs.add(x.generateProvider());} 
		} );
		//should only have 1 valid output
	    validPvdrs.forEach(x -> System.out.println(x.toString()));
		//how i initially tested
//		pvTest.forEach(x -> {
//			if(x.isClean())
//				{ System.out.println(x.generateProvider());} 
//			} );
//		
		
	}
}
