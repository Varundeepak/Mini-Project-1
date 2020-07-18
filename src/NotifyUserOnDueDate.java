/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;
/**
 *
 * @author Varun
 */
public class NotifyUserOnDueDate {
    public void noti2()
    {
        try{
        Connection con;
        Statement stmt;
        String query;
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useTimezone=true&serverTimezone=UTC","root","");
        stmt=con.createStatement();
        
        String query2="select now()";
        rs=stmt.executeQuery(query2);
        rs.next();
        Date xyz=rs.getDate(1);
        query="select issue.memberid,member.contact from member,issue where member.memberid=issue.memberid and issue.dateofreturn2=(select current_date());";
        rs=stmt.executeQuery(query);
        while(rs.next())
        { int ijk=rs.getInt("issue.memberid");
            String abc=rs.getString(1);
        String message = "Greetings Member. This message is to remind you that the due date for the book you borrowed is TODAY!!! Kindly remember to return the book in time. Thank You!!!";		
		String phone = abc;
		String username = "Varundeepak";
		String password = "dishapatani";
		String address = "http://192.168.1.101";
		String port = "8090";
		
		URL url = new URL(
				address+":"+port+"/SendSMS?username="+username+"&password="+password+
				"&phone="+phone+"&message="+URLEncoder.encode(message,"UTF-8"));
		
		URLConnection connection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while((inputLine = bufferedReader.readLine()) !=null){
			System.out.println(inputLine);
		}
		bufferedReader.close();
                String query3="update issue set dateofreturn2=null where memberid="+ijk+";";
                stmt.executeUpdate(query3);
                
     
            }
        }
       
        
         catch(Exception e)
                {
                System.out.println("Notification Error");
                }
    }
}
