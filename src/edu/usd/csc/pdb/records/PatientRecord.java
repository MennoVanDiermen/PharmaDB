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
public class PatientRecord implements Serializable {
    
    private final int patient_id;
    private String fname, lname, address;
    private int mm, dd, yyyy;
    private int zip;
    
    
    public PatientRecord(String fname, String lname, String birthdate, String address, int zip) 
            throws InvalidFirstNameException, InvalidLastNameException, InvalidAddressException {
        if(fname.isEmpty() || fname == null) throw new InvalidFirstNameException();
        this.fname = fname; 
        
        if(lname.isEmpty() || lname == null) throw new InvalidLastNameException();
        this.lname = lname; 
        
        if(address.isEmpty() || address == null) throw new InvalidAddressException();
        this.address = address; this.zip = zip;
        String[] dob = birthdate.split("/"); 
        mm = Short.parseShort(dob[0]);
        dd = Short.parseShort(dob[1]);
        yyyy = Short.parseShort(dob[2]);
        patient_id = Database.nextClientID();
    }

    /**
     * @return the patient_id
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the zip
     */
    public int getZip() {
        return zip;
    }
    
    public String getDob() {
        return "" + mm + "/" + dd + "/" + yyyy;
    }
    
    class InvalidFirstNameException extends Exception {
        
    }
    
    class InvalidLastNameException extends Exception {
        
    }
    
    class InvalidDOBException extends Exception {
        
    }
    
    class InvalidAddressException extends Exception {
        
    }
    
    class InvalidZIPException extends Exception {
        
    }
}