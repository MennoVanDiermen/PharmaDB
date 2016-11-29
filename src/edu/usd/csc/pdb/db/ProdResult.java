package edu.usd.csc.pdb.db;

import edu.usd.csc.pdb.records.StockRecord;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class ProdResult {
    
    private IntegerProperty 
            STOCK = new SimpleIntegerProperty();
    private StringProperty 
            PID = new SimpleStringProperty(),             
            PROD = new SimpleStringProperty(), 
            DOSE = new SimpleStringProperty();
    
    public ProdResult(StockRecord record) {
        this.setPID(record.getProduct_id());
        this.setPROD(record.getProd());
        this.setDOSE(record.getDose());
        this.setSTOCK(record.getStock());
    }
    
    public final void setPID(int n) {
        PID.setValue(String.format("%06d", n));
    }
    
    public final void setPROD(String str) {
        PROD.setValue(str);
    }
    
    public final void setDOSE(String str) {
        DOSE.setValue(str);
    }
    
    public final void setSTOCK(int n) {
        STOCK.setValue(n);
    }
        
    public String getPID() {
        return PID.getValue();
    }
    
    public String getPROD( ) {
        return PROD.getValue();
    }
    
    public String getDOSE() {
        return DOSE.getValue();
    }
    
    public int getSTOCK() {
        return STOCK.getValue();
    }
        
    public StringProperty PIDProperty() {
        return PID;
    }
    
    public StringProperty PRODProperty() {
        return PROD;
    }
    
    public StringProperty DOSEProperty() {
        return DOSE;
    }
    
    public IntegerProperty STOCKProperty() {
        return STOCK;
    }
}
