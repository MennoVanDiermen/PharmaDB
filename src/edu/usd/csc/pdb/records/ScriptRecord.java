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
public class ScriptRecord implements Serializable {
    
    private final int script_id, cid, pid;
    private final String date;
    private boolean filled;
    
    
    public ScriptRecord(int c, int p, String d) {
        script_id = Database.nextScriptID();
        cid = c; pid = p; date = d;
    }
    
    /**
     * @return the filled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * @param filled the filled to set
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * @return the script_id
     */
    public int getScript_id() {
        return script_id;
    }

    /**
     * @return the cid
     */
    public int getCid() {
        return cid;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
}
