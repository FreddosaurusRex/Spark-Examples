package edu.easternct.bigdata;

import java.util.Arrays;
import java.util.List;
import java.lang.Iterable;

import scala.Tuple2;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

public class WordCount {
	
public static void main(String[] args) throws Exception {

	    
	    SparkConf conf = new SparkConf().setAppName("wordCount");
	    conf.setMaster(args[0]);
		JavaSparkContext sc = new JavaSparkContext(conf);
	    JavaRDD<String> rdd = sc.textFile(args[1]);
	    
	    JavaPairRDD<String, Integer> wcounts = rdd.flatMap(x -> Arrays.asList(x.split(" ")))
	                        .mapToPair(w -> new Tuple2<String, Integer>(w, 1))
	                        .reduceByKey((x, y) -> x + y);
	      System.out.println("Input found : " + wcounts.count());
//	      JavaPairRDD<String, Integer> samples = wcounts.take(10);
//	      wcounts.take(10).saveAsTextFile(args[1]);
	      sc.stop();
		}
	}


