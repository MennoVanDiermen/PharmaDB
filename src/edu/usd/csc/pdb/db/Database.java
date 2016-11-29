package edu.usd.csc.pdb.db;

import edu.usd.csc.pdb.records.PatientRecord;
import edu.usd.csc.pdb.records.ScriptRecord;
import edu.usd.csc.pdb.records.StockRecord;
import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * @author Menno.VanDiermen, Kurtis Van Gent, Krishna Pareek
 */
public class Database {
    
    private static final String 
            DB_PATH = "PharmaDB.db",
            PATIENT_PATH = "patients",
            SCRIPT_PATH = "scripts",
            STOCK_PATH = "stock",
            ALL_PATH = "recent";
    
    private Comparator<Integer> idComp;
    
    public Database() {
        DB db = openDB();
        
        Map<Integer, PatientRecord> patients = db.createTreeMap(PATIENT_PATH).makeOrGet();
        Map<Integer, StockRecord> inventory = db.createTreeMap(STOCK_PATH).makeOrGet();
        Map<Integer, Map<Integer, ScriptRecord>> prescriptions = db.createTreeMap(SCRIPT_PATH).makeOrGet();
        Map<Integer, ScriptRecord> allscripts = db.createTreeMap(ALL_PATH).makeOrGet();
        
        db.commit(); db.close();
    }
    
    public static int nextProductID() {
        DB db = openDB();
        
        Map map = db.getTreeMap(STOCK_PATH);
        int id = map.size() + 1;
        
        db.commit(); db.close();
        return id;
    }
    
    public void insertStockRecord(StockRecord sr) {
        DB db = openDB();
        
        Map<Integer, StockRecord> inventory = db.getTreeMap(STOCK_PATH);
        inventory.put(sr.getProduct_id(), sr);
        
        
        db.commit(); db.close();
    }
    
    public void updateStockRecord(StockRecord sr) {
        insertStockRecord(sr);
    }
    
    public StockRecord getProduct(int pid) {
        DB db = openDB();
        Map<Integer, StockRecord> stock = db.getTreeMap(STOCK_PATH);
        
        StockRecord sr = stock.get(pid);
        db.commit(); db.close();
        return sr;
    }
    
    public ObservableList<ProdResult> searchPID(Integer pid) throws Exception {
        ObservableList<ProdResult> list = FXCollections.observableArrayList();
        
        DB db = openDB();
        Map<Integer, StockRecord> stock = db.getTreeMap(STOCK_PATH);        
        
        if(stock.keySet().contains(pid)) {
            list.add(new ProdResult(stock.get(pid)));
        }
        for(Integer i: stock.keySet()) {
            if(i.toString().contains(pid.toString()) && !i.equals(pid)) {
                list.add(new ProdResult(stock.get(i)));
            }
        }
        
        db.commit(); db.close();
        return list;
    }
    
    
    public ObservableList<ProdResult> searchPROD(String nm) {
        ObservableList<ProdResult> list = FXCollections.observableArrayList();
        
        DB db = openDB();
        Map<Integer, StockRecord> stock = db.getTreeMap(STOCK_PATH);        
        
        for(StockRecord sr: stock.values()) {
            if(sr.getProd().contains(nm)) list.add(new ProdResult(sr));
        }
        
        db.commit(); db.close();
        return list;
    }
    
    public static int nextClientID() {
        DB db = openDB();
        
        Map map = db.getTreeMap(PATIENT_PATH);
        int id = map.size() + 1;
        
        db.commit(); db.close();
        return id;
    }
    
    public void insertPatientRecord(PatientRecord pr) {
        DB db = openDB();
        
        Map<Integer, PatientRecord> patients = db.getTreeMap(PATIENT_PATH);        
        patients.put(pr.getPatient_id(), pr);
        
        
        db.commit(); db.close();
        this.addScriptMap(pr.getPatient_id());
    }
    
    public void updatePatientRecord(PatientRecord pr) {
        insertPatientRecord(pr);
    }
    
    public PatientRecord getPatient(int cid) {
        DB db = openDB();
        Map<Integer, PatientRecord> patients = db.getTreeMap(PATIENT_PATH);
        
        PatientRecord pr = patients.get(cid);
        db.commit(); db.close();
        return pr;
    }
    
    public void addScriptMap(int cid) {
        DB db = openDB();
        
        Map<Integer, Map<Integer, ScriptRecord>> prescriptions = db.getTreeMap(SCRIPT_PATH);
        prescriptions.put(cid, new TreeMap<>());
        
        db.commit(); db.close();
    }
    
    public ObservableList<PatientResult> searchCID(Integer cid) throws Exception {
        ObservableList<PatientResult> list = FXCollections.observableArrayList();
        
        DB db = openDB();
        Map<Integer, PatientRecord> patients = db.getTreeMap(PATIENT_PATH);        
        
        if(patients.keySet().contains(cid)) {
            list.add(new PatientResult(patients.get(cid)));
        }
        for(Integer i: patients.keySet()) {
            if(i.toString().contains(cid.toString()) && !i.equals(cid)) {
                list.add(new PatientResult(patients.get(i)));
            }
        }
        
        db.commit(); db.close();
        return list;
    }
    
    public ObservableList<PatientResult> searchLN(String ln) throws Exception {
        ObservableList<PatientResult> list = FXCollections.observableArrayList();
        
        DB db = openDB();
        Map<Integer, PatientRecord> patients = db.getTreeMap(PATIENT_PATH);
        
        for(PatientRecord r: patients.values()) {
            if(r.getLname().startsWith(ln)) {
                list.add(new PatientResult(r));
            }
        }
        
        
        db.commit(); db.close();
        return list;
    }
    
    public static int nextScriptID() {
        DB db = openDB();
        
        Map map = db.getTreeMap(ALL_PATH);
        int id = map.size() + 1;
        
        db.commit(); db.close();
        return id;
    }
    
    
    
    public void insertScriptRecord(ScriptRecord sr) {
        DB db = openDB();
        
        Map<Integer, Map<Integer, ScriptRecord>> prescriptions = db.getTreeMap(SCRIPT_PATH);
        Map<Integer, ScriptRecord> scripts = prescriptions.get(sr.getCid());
        scripts.put(sr.getScript_id(), sr);
        prescriptions.put(sr.getCid(), scripts);
        
        db.commit(); db.close();
        
        db = openDB();
        
        Map<Integer, ScriptRecord> allscripts = db.getTreeMap(ALL_PATH);
        allscripts.put(sr.getScript_id(), sr);
        
        db.commit(); db.close();
    }
    
    public void updateScriptRecord(ScriptRecord sr) {
        insertScriptRecord(sr);
    }    
    
    public ScriptRecord getScript(int sid) {
        DB db = openDB();
        Map<Integer, ScriptRecord> scripts = db.getTreeMap(ALL_PATH);
        
        ScriptRecord sr = scripts.get(sid);
        db.commit(); db.close();
        return sr;
    }
    
    public ObservableList<ScriptResult> scriptsForCid(int cid) {
        ObservableList<ScriptResult> list = FXCollections.observableArrayList();
        
        DB db = openDB();
        
        Map<Integer, Map<Integer, ScriptRecord>> prescriptions = db.getTreeMap(SCRIPT_PATH);
        
        System.out.println("Number of prescriptions for CID "+cid+" :"+prescriptions.get(cid).size());
        for (ScriptRecord sr: prescriptions.get(cid).values()) {
            ScriptResult res = new ScriptResult(sr, this.getProduct(sr.getPid()));
            list.add(res);
        }
        
        db.commit(); db.close();
        return list;
    }
    
    public ObservableList<ScriptResult> getRecent() {
        ObservableList<ScriptResult> list = FXCollections.observableArrayList();
        DB db = openDB();
        
        Map<Integer, ScriptRecord> recents = db.getTreeMap(ALL_PATH);
        if(recents.isEmpty()) return list;
        int max = Collections.max(recents.keySet());
        for (int i = 0; i < 20; i++) {
            ScriptRecord sr = recents.get(max-i);
            list.add(new ScriptResult(sr, this.getProduct(sr.getPid())));
        }
        
        return list;
    }
    
    public static String getDate() {
        Calendar c = Calendar.getInstance(Locale.US);
        String date = "";
        date += String.format("%02d" ,c.get(Calendar.MONTH));
        date += "/";
        date += String.format("%02d" ,c.get(Calendar.DATE)+1);
        date += "/";
        date += c.get(Calendar.YEAR);
        return date;
    }
    
    public static DB openDB() {
        File file = new File(DB_PATH);
        return DBMaker.newFileDB(file).make();
    }
}
