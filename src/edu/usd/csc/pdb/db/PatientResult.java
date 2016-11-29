package edu.usd.csc.pdb.db;

import edu.usd.csc.pdb.records.PatientRecord;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class PatientResult {
    
    private StringProperty 
            CID = new SimpleStringProperty(),
            FN = new SimpleStringProperty(), 
            LN = new SimpleStringProperty(), 
            DOB = new SimpleStringProperty(), 
            ADDR = new SimpleStringProperty();
    private IntegerProperty
            ZIP = new SimpleIntegerProperty();
    
    public PatientResult(PatientRecord record) {
        this.setCID(record.getPatient_id());
        this.setFN(record.getFname());
        this.setLN(record.getLname());
        this.setDOB(record.getDob());
        this.setADDR(record.getAddress());
        this.setZIP(record.getZip());
    }
    
    public final void setCID(int n) {
        CID.setValue(String.format("%06d", n));
    }
    
    public final void setFN(String str) {
        FN.setValue(str);
    }
    
    public final void setLN(String str) {
        LN.setValue(str);
    }
    
    public final void setDOB(String str) {
        DOB.setValue(str);
    }
    
    public final void setADDR(String str) {
        ADDR.setValue(str);
    }
    
    public final void setZIP(int n) {
        ZIP.setValue(n);
    }
    
    public String getCID() {
        return CID.getValue();
    }
    
    public String getFN() {
        return FN.getValue();
    }
    
    public String getLN() {
        return LN.getValue();
    }
    
    public String getDOB() {
        return DOB.getValue();
    }
    
    public String getADDR() {
        return ADDR.getValue();
    }
    
    public int getZIP() {
        return ZIP.getValue();
    }
    
    public StringProperty CIDProperty() {
        return CID;
    }
    
    public StringProperty FNProperty() {
        return FN;
    }
    
    public StringProperty LNProperty() {
        return LN;
    }
    
    public StringProperty DOBProperty() {
        return DOB;
    }
    
    public StringProperty ADDRProperty() {
        return ADDR;
    }
    
    public IntegerProperty ZIPProperty() {
        return ZIP;
    }
}
