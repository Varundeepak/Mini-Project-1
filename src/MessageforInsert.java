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
public class MessageforInsert {
    public void send(int a,String b,String c,String d,int e) throws Exception
    {
        Connection con;
        Statement stmt;
        String query;
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useTimezone=true&serverTimezone=UTC","root","");
        stmt=con.createStatement();
        query="select * from member";
        rs=stmt.executeQuery(query);
        while(rs.next())
        {
            String abc=rs.getString("contact");
        String message = "Greetings Member. We have included a new title in our library. The details of the title are as follows :\nBook ID : "+a+"\nBook Name : "+b+"\nBook Author : "+c+"\nStream : "+d+"\nNo. of Copies Added : "+e;		
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
        }
    }
}
