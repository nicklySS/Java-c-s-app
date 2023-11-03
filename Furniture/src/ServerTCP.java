
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStream;import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import com.mysql.cj.conf.ConnectionUrl;


public class ServerTCP extends Thread {

	ServerSocket serverSocket = null;
	public ServerTCP() {
		try {
			serverSocket = new ServerSocket(1500);
			System.out.println("Starting the server ");
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
						
			while (true) {
				Socket clientSocket = serverSocket.accept();				
				System.out.println("Connection accepted from " +clientSocket.getInetAddress().getHostAddress());
				
				Thread.sleep(500);
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				int num = (int) in.readObject();
				String strr = (String) in.readObject();
				System.out.println("num = " + num);
				System.out.println("str = " + strr);

				String[] param;
				
				param = strr.split("_");
				for (int i = 0;i<param.length;i++) {
					System.out.println(param[i]);
				}
				
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				
				Connection connection = null;
				PreparedStatement stmt = null;
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/furniture?serverTimezone=UTC", "root", "11111");
							System.out.println(connection.getMetaData().getURL());
							if (num == 1) {
								stmt = connection.prepareStatement("select * from furniture");
							}
							if (num == 2) {
								stmt = connection.prepareStatement("select * from furType");
							}
							if (num == 3 ) {
								stmt = connection.prepareStatement("select f1.* from furniture f1,furType f2 where f1.furtype_id=f2.id and f2.name = '"+strr+"'");
							}
							if (num == 4) {
								stmt = connection.prepareStatement("select * from furniture where width>"+strr);
							}
							if (num == 5) {
								stmt = connection.prepareStatement("select * from furniture where height>"+strr);
							}
							
							if (num == 6 && param[0].equals("1")) {
								Statement stmtt = connection.createStatement(); 
								stmtt.execute("insert into furniture values(0,'"+param[1]+"',"+param[2]+",'"+param[3]+"','"+param[4]+"',"+param[5]+",'"+param[6]+"',"+param[7]+","+param[8]+")");
							}
							if (num == 6 && param[0].equals("2")) {
								Statement stmtt = connection.createStatement(); 
								stmtt.execute("insert into furtype values(0,'"+param[1]+"','"+param[2]+"')");
							}
							
							if (num == 7 && param[0].equals("1")) {
								Statement stmtt = connection.createStatement(); 
								stmtt.execute("delete from furniture where id="+param[1]+"");
							}
							if (num == 7 && param[0].equals("2")) {
								Statement stmtt = connection.createStatement(); 
								stmtt.execute("update furniture set furType_id=null where furType_id="+param[1]);
								stmtt.execute("delete from furtype where id="+param[1]+"");
							}
							
							if (num == 8 && param[0].equals("1")) {
								Statement stmtt = connection.createStatement(); 
								stmtt.execute("update furniture set name='"+param[2]+"',furType_id="+param[3]+",material='"+param[4]+"',color='"+param[5]+"',doors="+param[6]+",installation_type='"+param[7]+"',width="+param[8]+",height="+param[9]+" where id = "+param[1]);
							}
							if (num == 8 && param[0].equals("2")) {
								Statement stmtt = connection.createStatement();
								stmtt.execute("update furtype set name='"+param[2]+"',descr='"+param[3]+"' where id = "+param[1]);
							}
							
							
						
							
				
						
				if (num!=6 && num!=7 && num!=8) {
				
					ResultSet rs = stmt.executeQuery();
				
				
					while (rs.next()) {
					   
					  
						
					   if (num==1 || num==3 || num==4 || num==5) {
						   String name = rs.getString(2);
						   int furtype = rs.getInt(3);
						   String mat = rs.getString(4);
						   String col = rs.getString(5);
						   int doors = rs.getInt(6);
						   String it = rs.getString(7);
						   int w = rs.getInt(8);
						   int h = rs.getInt(9);
						   String data="Название: "+name+"\t Тип фурнитуры: "+furtype+"\t Материал: "+mat+"\t Цвет: "+col+"\t Кол-во дверей(опционально): "+doors+"\t Тип установки: "+it+"\t Ширина: "+w+"\t Высота: "+h;
						   out.writeObject(data);
					   }
					   if (num==2) {
						   String name = rs.getString(2);
						   String desc = rs.getString(3);
						   String data = "Название: "+name+"\t Описание: "+desc;
						   out.writeObject(data);
					   }
					  
					 
					}
				}
				out.writeObject("Stop");
				out.flush();
				
				
				System.out.println("Stop");
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// Запуск сервера 
		new ServerTCP();
	}

}
