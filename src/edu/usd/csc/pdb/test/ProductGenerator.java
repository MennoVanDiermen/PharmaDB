/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.csc.pdb.test;

import edu.usd.csc.pdb.records.StockRecord;
import java.util.Random;

/**
 *
 * @author Menno.VanDiermen
 */
public class ProductGenerator {
    
    private static Random random = new Random();

    private static String[] meds = {
        "Advil",
        "Aleve",
        "Tylenol",
        "Diazepam",
        "Lipitor",
        "Metfornin",
        "Plaquenil",
        "Amiodarone",
        "Atenolol",
        "Flonase",
        "Symbicort",
        "Synthroid",
        "Crestor",
        "Diovan",
        "Ventolin",
        "Advair Diskus",
        "Lantus Solostar",
        "Cymbalta",
        "Vyvanse",
        "Lyrica",
        "Lantus",
        "Celebrex",
        "Abilify",
        "Januvia",
        "Namenda",
        "Viagra",
        "Cialis",
        "Zetia",
        "Nasonex",
        "Suboxone",
        "Bystolic",
        "Flovent",
        "Oxycontin",
        "Levemir",
        "Xarelto",
        "Benicar",
        "Tamiflu",
        "Novolog",
        "Premarin",
        "Humalog",
        "Lumigan",
        "Afluria",
        "Janumet",
        "Ortho Tri-Cyclen",
        "Toprol",
        "Pristiq",
        "Combivent",
        "Vytorin",
        "Travatan",
        "Focalin",
        "Adderall"
    };
    
    private static String[] doses = {
        "1mg",
        "2mg",
        "3mg",
        "5mg",
        "10mg",
        "12mg",
        "15mg"
    };
    
    public static StockRecord generateProduct() {
        return new StockRecord(randomMed(), randomDose(), randomStock());
    }
    
    public static String randomMed() {
        return meds[random.nextInt(meds.length)];
    }
    
    public static String randomDose() {
        return doses[random.nextInt(doses.length)];
    }
    
    public static int randomStock() {
        return random.nextInt(200);
    }
}
