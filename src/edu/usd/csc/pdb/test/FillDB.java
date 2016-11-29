/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.csc.pdb.test;

import edu.usd.csc.pdb.db.Database;

/**
 *
 * @author Menno.VanDiermen
 */
public class FillDB {
    
    
    public static void fill() throws Exception {
        
        Database db = new Database();
        
        for (int i = 0; i < 500; i++) {
            db.insertPatientRecord(PatientGenerator.generatePatient());
            db.insertPatientRecord(PatientGenerator.generatePatient());
            db.insertStockRecord(ProductGenerator.generateProduct());
        }
        for (int i = 0; i < 2000; i++) {
            db.insertScriptRecord(ScriptGenerator.generateScript());
        }
    }
}