/**
 * SPDXVersion: SPDX-1.1
 * Creator: Person: Nuno Brito (nuno.brito@triplecheck.de)
 * Creator: Organization: TripleCheck (http://triplecheck.de)
 * Created: 2015-04-05T18:46:00Z
 * LicenseName: AGPL-3.0+
 * FileCopyrightText: <text> Copyright (c) 2015 Nuno Brito, TripleCheck </text>
 * FileComment: <text> Class with the code for running the tests.  </text> 
 */
package core;

import java.io.File;

/**
 *
 * @author Nuno Brito, 5th of April 2015 in Leipzig, Germany
 */
public class RunTest {

    final File 
            configSamples = new File("samples.txt"),
            configDetectors = new File("detectors.txt");
    
    String[] sampleLines = null;
    
    /**
     * Start the tests
     */
    public void start() {
        // first step is making sure that we have the configuration files available
        if(configIsOk() == false){
            System.err.println("RT24 error: Problem with the configuration files");
            return;
        }
        
        // everything seems fine from a config point of view, let's start
        processLines();
        
    }

    /**
     * make sure that our config files are available
     */
    private boolean configIsOk() {
        // do we have a file with samples?
        if(configSamples.exists() == false || configSamples.isDirectory()){
            System.err.println("Was expecting to find a text file with "
                    + "samples at " + configSamples.getPath());
            return false;
        }
        
        // read the contents for our samples
        final String text = utils.files.readAsString(configSamples);
        
        // did we actually read something?
        if(text.isEmpty()){
            System.err.println("Was expecting some text inside " + configSamples.getPath());
            return false;
        }
        
        // break the text of the file into multiple lines
        sampleLines = text.split("\n");

        // did we actually got some lines that can be used?
        if(sampleLines.length == 0){
            System.err.println("Not good, was expecting at minimum a"
                    + " line inside " + configSamples.getPath());
            return false;
        }
        
        return true;
    }

    /**
     * Go through each line to test the expected output
     */
    private void processLines() {
        // iterate the available lines
        for(final String line : sampleLines){
            // further break the line being handled
            processLine(line);
        }
    }

    /**
     * Decomposes a line and launches the test
     * @param line 
     */
    private void processLine(final String line) {
        // get the data fields inside the line
        final String[] data = line.split(">");
        // the first field if the sample file, which must exist
        File file = new File(data[0]);
        if(file.exists() == false || file.isDirectory()){
            System.err.println("RT95 error, failed to process line: " + line);
            return;
        }

        
        System.out.println("File: " + data[0] + "; Result: " + data[1]);
    }
        
}
