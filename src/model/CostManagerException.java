package model;
import java.sql.SQLException;

public class CostManagerException extends Throwable {
    public CostManagerException(String s) {
    //added
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
