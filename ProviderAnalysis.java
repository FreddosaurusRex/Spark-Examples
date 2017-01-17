package edu.easternct.bigdata;


import java.util.Collections;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class ProviderAnalysis{
	
public static void main(String[] args) throws Exception {

	    
	    SparkConf conf = new SparkConf().setAppName("Provider Analysis");
	    conf.setMaster(args[0]);
		JavaSparkContext sc = new JavaSparkContext(conf);
	    JavaRDD<String> rdd = sc.textFile("/home/training/workspace/spark-rdd/a-data/Medicare-Provider-PUF-a-CY2012.csv");
	    
	   Function<String, Provider> Validator = (x-> { ProviderValidator temp = new ProviderValidator(x); 
		  						 temp.parser();
		  						 if(temp.isClean())
		  							 return temp.generateProvider();
		  						 else 
		  							 return null;
								});
	   //left comments in to show the example of how it started compared to the final version
	   // JavaRDD<Provider> cleanOnes = rdd.map(Validator);
	   // JavaRDD<Provider> finalClean =   cleanOnes.distinct().filter(x-> !(x == null));
	   // JavaRDD<String>  states = finalClean.map(x-> x.getState());
	   // Map<String, Long>  stateCount = states.countByValue();       
	   // JavaRDD<String>  gender = finalClean.map(x -> x.getGender());
	   // Map<String,Long> genderCount = gender.countByValue();
	   //  JavaRDD<String>  provType = finalClean.map(x -> x.getProviderType());
	   // Map<String,Long> provTypeCount = provType.countByValue();
	   //filter null and ensures distinct providers
	   JavaRDD<Provider> finalClean = rdd.map(Validator).distinct().filter(x-> !(x == null));
	   //maps and counts the states
	   Map<String,Long> stateCount = finalClean.map(x-> x.getState()).countByValue();
	   //maps and counts the gender of providers
	   Map<String,Long> genderCount = finalClean.map(x -> x.getGender()).countByValue();
	   //maps and counts the provider type
	   Map<String,Long> provTypeCount = finalClean.map(x -> x.getProviderType()).countByValue();
	    				System.out.println(stateCount);
	    				System.out.println(genderCount); 
	    				System.out.println(provTypeCount);
	    							
	   
	    
	    System.out.println("This is the Provider Analysis code.");
	    sc.close();   

	  }
}