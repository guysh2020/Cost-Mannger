package model;
import java.sql.SQLException;

public class CostManagerException extends Throwable {

    /****
     * 
     * CostManagerException constructor holds the cause of the exception and prints it
     * @param s (string) the detailed cause of the exception
     *      
     ****/
    public CostManagerException(String s) {
        System.out.println(s);
    }


    public CostManagerException(String s, SQLException e) {
        System.out.println(s);
        printSQLException(e);
    }

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
}
