/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.csc.pdb.records;

import edu.usd.csc.pdb.db.Database;
import java.io.Serializable;

/**
 *
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public abstract class MedRecord implements Serializable {
    
    private final int product_id;
    private final String prod, dose;
    
    public MedRecord(String p, String d) {
        product_id = Database.nextProductID();
        prod = p; dose = d.isEmpty() ? "N/A" : d;
    }

    /**
     * @return the pid
     */
    public int getProduct_id() {
        return product_id;
    }
    
    /**
     * @return the prod
     */
    public String getProd() {
        return prod;
    }

    /**
     * @return the dose
     */
    public String getDose() {
        return dose;
    }
}
