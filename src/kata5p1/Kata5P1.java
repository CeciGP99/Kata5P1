package kata5p1;

import java.util.List;

public class Kata5P1 {

    public static void main(String[] args) {
        
        MailListReader mlr = new MailListReader();
        List<String> list = mlr.read("email.txt");
        SelectApp app = new SelectApp("mail");
        app.connect();
        app.createNewTable();
        app.insertData(list);       
    }
}
