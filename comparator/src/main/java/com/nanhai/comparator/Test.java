package com.nanhai.comparator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by hcd on 7/2/16.
 */
public class Test {
  private static final Logger LOG = Logger.getLogger(Test.class);
  public static void main(String[] args) throws Exception {
    //BasicConfigurator.configure();

    System.out.println("test");
    LOG.info("test");
    FileInputStream fis=null;
    PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("out.txt"), "UTF-8"));
    String line;
    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("example.txt"), "UTF-8"));
    
    String wordsLine;
    long totalLength = 0;
    long frequency = 0 ;
    long others = 0;

    while ( (line = in.readLine()) != null) {
      String[] lineSplit = line.split("");
      //calculate total number of characters
      totalLength = totalLength + lineSplit.length;
      for(String s : lineSplit) {

        LOG.info(" word is : " + s);
        if(s.equalsIgnoreCase("，") || s.equalsIgnoreCase("。") || s.equalsIgnoreCase("“") || s.equalsIgnoreCase("”")) {
          others = others + 1;
          continue;
        }

        BufferedReader wordsIn = new BufferedReader(new InputStreamReader(new FileInputStream("highfrequency.txt"), "UTF-8"));
        outerloop:
        while((wordsLine = wordsIn.readLine()) !=null){
          String[] highWords = wordsLine.split("#");
          for(String word: highWords) {
            if(s.equalsIgnoreCase(word)) {
              frequency = frequency + 1;
              LOG.info("found the word in high frequency" + word);
              break outerloop;
            }
          }
        }

      }

    }
    LOG.info("high frequency is: " + frequency);
    LOG.info("other character is: " + others);
    LOG.info("total lenght is:  " + totalLength);

  }
}
