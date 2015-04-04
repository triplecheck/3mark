/*
 * Methods to ease the handling of files
 */

package utils;

import static compare.FindVariables.findSnippetInsideFile;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import structure.MatchBox;
import structure.Variables;

/**
 *
 * @author Nuno Brito, 6th of June 2011 in Darmstadt, Germany.
 */
public class files_minor {

     


 public static char toChar(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("The String must not be empty");
        }
        return str.charAt(0);
    }

// Deletes all files and subdirectories under dir.
// Returns true if all deletions were successful.
// If a deletion fails, the method stops attempting to delete and returns false.
public static boolean deleteDir(File dir) {
    if (dir.isDirectory()) {
        String[] children = dir.list();
        for (int i=0; i<children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
                return false;
            }
        }
    }
    // The directory is now empty so delete it
    return dir.delete();
}


 public static MatchBox findSnippetInDatabase(final String text,
            final int maxMatches){
        // what we use for retrieving the variables from a source code snippet
        Variables vars = new Variables();
        // read the variables
        vars.read(text);
        System.out.println("Searching for variables: " + vars.toString());
        
        // let's track how long each search is taking
//        final long timeStart = System.currentTimeMillis();
        
        // now that we have the variables indexed, let's try to find matches
        MatchBox result = findSnippetInsideFile(vars, maxMatches);
        // add the variables
        result.setVariables(vars);
        
//        final long timeEnd = System.currentTimeMillis();
//        final long timeElapsed = timeEnd - timeStart;
//        
//        // where we store the seach time results
//        String elapsedTimeSummary;
//        
//        if(timeElapsed < 1000){
//            elapsedTimeSummary = "" 
//                + timeElapsed + " milliseconds";
//        }else{
//            elapsedTimeSummary = "" 
//                + utils.time.timeNumberToHumanReadable(timeElapsed);
//        }    
//        // all done
//        result += "\nSearch time:" 
//                + elapsedTimeSummary
//                ;
        
        return result;
    }














  /** Writes an inputstream back into a file */  
  static public void inputFileStreamToFile(InputStream inputStream, 
          File outFile){  
    
      OutputStream out = null;
      try{
          out = new FileOutputStream(outFile);
          byte buf[]=new byte[1024];
          int len;
	  String test = "do something";
          while((len=inputStream.read(buf))>0)
              out.write(buf,0,len);
          inputStream.close();
      }
      catch (IOException e){}
      finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
       
      }
}
    
  public static String getTextFile(String TextFileURL){
         String result = "";
         try {
			URL url = new URL(TextFileURL);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuilder sb = new StringBuilder();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();

                        is.close();
                        isr.close();
                        sb = null;
                        urlConnection = null;
		} catch (MalformedURLException e) {
			//e.printStackTrace();
                        return "";
		} catch (IOException e) {
                    return "";
			//e.printStackTrace();
		}
         return result; // {{{testing for success!
     }
    
  
  public static void main(String [] args) {
    try {
      int port = 9090;
      if (args.length > 1) {
        port = Integer.valueOf(args[0]);
      }
	// Processor
      TestHandler testHandler =
        new TestHandler();
      ThriftTest.Processor testProcessor =
        new ThriftTest.Processor(testHandler);
blablabla
      // Transport
      TServerSocket tServerSocket =
        new TServerSocket(port);

      // Protocol factory
      TProtocolFactory tProtocolFactory =
        new TBinaryProtocol.Factory();

      TServer serverEngine;

      // Simple Server
      // serverEngine = new TSimpleServer(testProcessor, tServerSocket);

      // ThreadPool Server
      serverEngine = new TThreadPoolServer(testProcessor, tServerSocket, tProtocolFactory);
      // Run it
      System.out.println("Starting the server on port " + port + "...");
      serverEngine.serve();
    } catch (Exception x) {
      x.printStackTrace();
    }
    System.out.println("done.");

     
  }
  
}
