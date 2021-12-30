package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectApp {
    
    private final String name;
    
    public SelectApp(String name) {
        this.name = name;
    }
    
    public Connection connect() {

        String url = "jdbc:sqlite:" + name;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                                rs.getString("Name") + "\t" +
                                rs.getString("Apellidos") + "\t" +
                                rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createNewTable(){
        Connection conn = null;
        String sql = "CREATE TABLE IF NOT EXISTS direcc_email (\n"
                    + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + " direccion text NOT NULL);";
        conn = this.connect();
        Statement stmt;
        
        try {
            stmt = (Statement) conn.createStatement();
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Query(){
        Connection conn = null;
        String sql = "SELECT * FROM PEOPLE";
        try {
            conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
            System.out.println(rs.getInt("Id")+ "\t"
                                + rs.getString("Name")+"\t"
                                + rs.getString("Apellidos")+"\t"
                                + rs.getString("Departamento")+"\t");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
    public void insertData (List<String> email){
        Connection conn = null;
        String sql = "INSERT INTO direcc_email(direccion) VALUES (?)";
        try {
            conn = this.connect();
            for (String string : email) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, string);
                pstmt.executeUpdate();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SelectApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
