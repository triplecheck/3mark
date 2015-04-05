/**
 * SPDXVersion: SPDX-1.1
 * Creator: Person: Nuno Brito (nuno.brito@triplecheck.de)
 * Creator: Organization: TripleCheck (http://triplecheck.de)
 * Created: 2015-04-05T18:46:00Z
 * LicenseName: AGPL-3.0+
 * FileCopyrightText: <text> Copyright (c) 2015 Nuno Brito, TripleCheck </text>
 * FileComment: <text> Main class that runs battery of tests.  </text> 
 */
package core;

/**
 *
 * @author Nuno Brito, 5th of April 2015 in Leipzig, Germany
 */
public class Main {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // run the class that performs the tests
        RunTest run = new RunTest();
        run.start();
    }
    
}
