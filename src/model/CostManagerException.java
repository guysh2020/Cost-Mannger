package model;

import java.sql.SQLException;
import java.util.ArrayList;


public class CostManagerException extends Throwable {
    /**
     *
     * CostManagerException constructor holds the cause of the exception.
     *
     * @param message (string) the detailed cause of the exception.
     *
     * @param sqle (SQLException) the detailed cause of the exception.
     *
     */

    public String message;
    private SQLException sqle;


    public CostManagerException(String s) {
        this.message = s;
    }


    public CostManagerException(String s, SQLException e) {
        this.message = s;
        this.sqle = e;
    }

    public void printMessage(){
        System.out.println(this.message);
    }

    public String getMessage(){
        return this.message ;
    }


    /**
     *
     * This method prints the full exception description from derby DB
     *
     */
    public void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }


    /**
     *
     * This method returns the full exception description from derby DB
     *
     */
    public ArrayList<String> getSQLException(SQLException e) {
        ArrayList<String> exc= new ArrayList<String>();

        while (e != null) {
            exc.add("SQL State:" + e.getSQLState() + "\nError Code:" + e.getErrorCode() + "\nMessage:" + e.getMessage());
            e = e.getNextException();
        }

        return exc;
    }
}
