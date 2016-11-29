package edu.usd.csc.pdb.records;

import java.io.Serializable;

/**
 *
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class StockRecord extends MedRecord implements Serializable {
    
    private int stock;
    
    public StockRecord(String p, String d, int s) {
        super(p, d); stock = s;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}