//package edu.easternct.bigdata.stubs;
//import java.io.IOException;
//
//
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Mapper;
//
//public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
//
//  @Override
//  public void map(LongWritable key, Text value, Context context)
//      throws IOException, InterruptedException {
//
//    /*
//     * TODO implement
//     */
//	  String line = value.toString();
//	  
//	  for(String word : line.split("\\W+")){
//		  if(word.length() > 0){
//			  //if it's a word it will take the first letter and map it to the length of the word.
//			 context.write(new Text( word.substring(0, 1)), new IntWritable(word.length()));
//		  }
//	  }
//
//  }
//}
