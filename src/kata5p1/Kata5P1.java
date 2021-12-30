package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static kata5p1.CreateTable.createNewTable;

public class Kata5P1 {

    public static void main(String[] args) {
        createNewTable();
        connect();
        SelectApp app = new SelectApp();
        app.selectAll();
    }
     
    private static void connect(){
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:KATA5.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connexión a SQLite establecida");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally{
            try{
                if (conn != null){
                    conn.close();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
