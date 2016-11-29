/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.csc.pdb.test;

import edu.usd.csc.pdb.db.Database;
import edu.usd.csc.pdb.records.ScriptRecord;
import java.util.Random;

/**
 *
 * @author Menno.VanDiermen
 */
public class ScriptGenerator {
   
    private static Random random = new Random();
    
    public static ScriptRecord generateScript() {
        int cbound = Database.nextClientID();
        int pbound = Database.nextProductID();
        String date = String.format("%02d", random.nextInt(27)+1) + "/" + String.format("%02d", random.nextInt(11)+1) + "/" + (1995 + random.nextInt(21));
        return new ScriptRecord(random.nextInt(cbound-1)+1, random.nextInt(pbound-1)+1, date); 
    }
}
