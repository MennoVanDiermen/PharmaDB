package edu.usd.csc.pdb.db;

import edu.usd.csc.pdb.records.ScriptRecord;
import edu.usd.csc.pdb.records.StockRecord;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class ScriptResult {
    
    private IntegerProperty
            SID = new SimpleIntegerProperty();
    private StringProperty
            CID = new SimpleStringProperty(),
            PID = new SimpleStringProperty(),
            PROD = new SimpleStringProperty(),
            DOSE = new SimpleStringProperty(),
            DATE = new SimpleStringProperty();
    private BooleanProperty
            FILLED = new SimpleBooleanProperty();
    
    public ScriptResult(ScriptRecord sr, StockRecord str) {
        setSID(sr.getScript_id());
        setCID(sr.getCid());
        setPID(sr.getPid());
        setDATE(sr.getDate());
        setFILLED(sr.isFilled());
        
        setPROD(str.getProd());
        setDOSE(str.getDose());
    }
    
    public final void setSID(int n) {
        SID.setValue(n);
    }
    
    public final void setCID(int n) {
        CID.setValue(String.format("%06d", n));
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
    
    public final void setDATE(String str) {
        DATE.setValue(str);
    }
    
    public final void setFILLED(boolean b) {
        FILLED.setValue(b);
    }
    
    public int getSID() {
        return SID.getValue();
    }
    
    public String getCID() {
        return CID.getValue();
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
    
    public String getDATE() {
        return DATE.getValue();
    }
    
    public boolean getFILLED() {
        return FILLED.getValue();
    }
    
    public IntegerProperty SIDProperty() {
        return SID;
    }
    
    public StringProperty CIDProperty() {
        return CID;
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
    
    public StringProperty DATEProperty() {
        return DATE;
    }
    
    public BooleanProperty FILLEDProperty() {
        return FILLED;
    }
}