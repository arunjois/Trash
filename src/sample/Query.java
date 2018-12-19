package sample;

import java.sql.*;
import java.util.StringJoiner;
import java.util.List;
import java.util.ArrayList;
public class Query {

    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        //Demo d = new Demo();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/arun/test.db");
            Demo d = new Demo();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }


    }
    List getCountryCode()
    {
        ResultSet set;
        Connection c = null;
        Statement stmt = null;
        List ar = new ArrayList();
        //String[] ret = new String[ar.length];
        StringJoiner sj = new StringJoiner(",");
        int i=0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/arun/test.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    //"SELECT name FROM geonames where country_code = \""+ query + "\" " );
                    "SELECT DISTINCT name from countries");


            while(rs.next()) {
                //ret[i] = rs.getString("name");
                ar.add(rs.getString("name"));
                //System.out.println(rs.getString("name"));
                i++;

            }


        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return ar;

    }
    List getPlace(String query)
    {
        ResultSet set;
        Connection c = null;
        Statement stmt = null;
        List ar = new ArrayList();
        String tmp = new String();

        /*
         * Get the ISO text to query geonames
         */
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/arun/test.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT iso FROM countries where name=\""+query+"\"");
            tmp=rs.getString("iso");
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }


        int i=0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/home/arun/test.db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM geonames where country_code=\""+tmp+"\"");

            while(rs.next()) {
                ar.add(rs.getString("name"));
                i++;

            }

        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return ar;

    }
}