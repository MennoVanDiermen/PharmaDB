/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usd.csc.pdb.test;

import edu.usd.csc.pdb.records.PatientRecord;
import java.util.Random;

/**
 *
 * @author Menno.VanDiermen
 */
public class PatientGenerator {
    
    private static Random random = new Random();
    
    private static String[] fnames = {
        "James",
        "Mitchell",
        "Mike",
        "Michael",
        "Jane",
        "Addison",
        "Bart",
        "Sam",
        "Jimmy",
        "Megan",
        "Katie",
        "Laila",
        "Peter",
        "William",
        "Ben",
        "John",
        "Stuart",
        "Samuel",
        "Zach",
        "Gordon",
        "Brad",
        "Jeremy",
        "Theo",
        "Nancy",
        "Robert",
        "Martin",
        "Maria",
        "Anna",
        "Bonnie",
        "Sarah",
        "Mary",
        "Leonard",
        "Akira",
        "Samantha",
        "Lisa",
        "Iris",
        "Paul",
        "Katrina",
        "Otto",
        "Tony",
        "Carmela",
        "George",
        "Barry",
        "Richard",
        "Nero",
        "Claude",
        "Andrew",
        "Petra",
        "Alexis",
        "Fred",
        "Aaron",
        "Angelina",
        "Bruce",
        "Austin",
        "Morgan",
        "Jesse",
        "Walter",
        "Juan",
        "Miguel",
        "Alfredo",
        "Li",
        "Zhu",
        "Xiao",
        "Sierra",
        "Jason",
        "Monica",
        "Phoebe",
        "Chandler",
        "Joanne",
        "Francesca",
        "Holly",
        "Hayley",
        "Deborah",
        "Kayleigh",
        "Faith",
        "Allison",
        "Alexander",
        "Sandra",
        "Shaun",
        "Christina",
        "Taylor"
    };
    
    private static String[] lnames = {
        "Wilson",
        "Smith",
        "Greene",
        "Samuels",
        "Freeman",
        "Clarkson",
        "Johnson",
        "Williamson",
        "VanDyke",
        "Hunter",
        "Garand",
        "Washington",
        "Jefferson",
        "Adams",
        "Hancock",
        "Garfield",
        "Bush",
        "Cheney",
        "Wilkinson",
        "Abbott",
        "Lee",
        "Hanson",
        "Dawson",
        "Brookman",
        "Norton",
        "Bellman",
        "Ford",
        "Dijkstra",
        "Pascal",
        "Tesla",
        "Newton",
        "Jackson",
        "Ziegler",
        "Pitt",
        "Jolie",
        "Diaz",
        "White",
        "Pinkman",
        "Castro",
        "Nunez",
        "Juarez",
        "Martinez",
        "El Paso",
        "Goodman",
        "Carlson",
        "Morrison",
        "Singh",
        "Sharma",
        "Kim",
        "Windsor",
        "Bateman",
        "Statham",
        "Harrison",
        "Jackman",
        "Kidman",
        "Wesson",
        "Black",
        "Chrissom",
        "Daniels",
        "Petersen",
        "Sanders",
        "Tanner",
        "Cooper",
        "Erickson",
        "Favreau",
        "Zhao",
        "Chan",
        "Xingpei",
    };
    
    private static String[] addr = {
        "Leicester",
        "Chesterfield",
        "Kansas",
        "Washington",
        "Dakota",
        "University",
        "Market",
        "Churchhill",
        "Gregory",
        "Rothschild",
        "Embassy",
        "Capitol",
        "Cross",
        "Inglewood",
        "Springsteen",
        "Rockman",
        "Leighville",
        "Franklin",
        "Kingfisher",
        "Greenfield",
        "Kingman",
        "Santa Luca",
        "San Marino",
        "Cherry",
        "Chestnut",
        "Walnut",
        "Pine",
        "Foreman",
        "Mission",
        "Lexington",
        "Missouri",
        "White Cloud",
        "Pine Springs",
        "Lazy Oaks",
        "Jefferson",
        "Centennial",
        "Hollywood",
        "Anvil",
        "Hammer",
        "Blacksmith",
        "Cooper",
        "Carolina",
        "Oregon",
        "Redwood",
        "Chesterton",
        "Wessexman",
        "Chevalier",
        "Guardian",
        "Bootcamp",
        "Pleasantville",
        "Treasury",
        "Chancellor",
        "Presidents",
        "Tremere",
        "Azure",
        "Blank",
        "Timberwolf",
        "Bison",
        "Catfish",
        "Elkland",
        "Coyote"
    };
    
    private static String[] streets = {
        "Street",
        "Lane",
        "Road",
        "Circle",
        "Place",
        "Square",
        "Way",
        "Drive",
        "Crossing",
        "Highway",
        "Roadway",
        "Plaza",
        "Boulevard"
    };
    
    public static PatientRecord generatePatient() throws Exception {
        return new PatientRecord(randomFirstName(), randomLastName(), randomDob(), randomAddress(), randomZip());
    }
    
    private static String randomFirstName() {
        return fnames[random.nextInt(fnames.length)];
    }
    
    private static String randomLastName() {
        return lnames[random.nextInt(lnames.length)];
    }
    
    private static String randomDob() {
        String dob = String.format("%02d", random.nextInt(27)+1) + "/" + String.format("%02d", random.nextInt(11)+1) + "/" + (1940 + random.nextInt(76));
        return dob;
    }
    
    private static String randomAddress() {
        String address = "" + random.nextInt(1000) + " " + addr[random.nextInt(addr.length)] + " " + streets[random.nextInt(streets.length)];
        
        return address;
    }
    
    private static int randomZip() {
        return ((random.nextInt(9)+1) * 10000) 
                + (random.nextInt(10) * 1000) 
                + (random.nextInt(10) * 100) 
                + (random.nextInt(10) * 10)
                + random.nextInt(10);
    }
}
