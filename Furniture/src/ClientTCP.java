
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

	public static void main(String args[]) {
		try {
			

			Scanner scan = new Scanner(System.in);
			
			System.out.println("�������� ������������");
			System.out.println("1 - User \n2 - Admin");
			int use = scan.nextInt();
			
			String login = "admin";
			int password = 123321;
			int att = 0;
			scan.nextLine();
			if (use==2) {
			while(true) {
				System.out.println("������� �����");
				
				String log = scan.nextLine();
				if (log.equals(login)) {
					System.out.println("������� ������");
					int pass = scan.nextInt();
					if (pass == password) {
						System.out.println("������ ������, ����� ����������!");
						break;
					}
					else {
						System.out.println("�� ����� ������������ ������. ���������� ��� ���");
						att++;
					}
				}
				else {
					System.out.println("���������� ��� ���");
					att++;
				}
				if (att>5) {
					System.out.println("������� ����� �������. �� ������������� ���������� �� User!");
					use = 1;
					break;
				}
			}
			}
			
			while(true) {
			
				Socket clientSocket = new Socket("localhost", 1500);
				ObjectOutputStream out =new ObjectOutputStream(clientSocket.getOutputStream());
			
			
		
			if (use==1) {
			System.out.println("������� ����� �������. \n1 - ������� ��� ������ \n2 - ������� ��� ���� ������ \n3 - ������� ��� ������ �� ��������� ���� \n4 - ������� ��� ������, � ������� ������ ������ �������� \n5 - ������� ��� ������, ������ ������� ������ ��������");
			
			int num = scan.nextInt();
			String costr = "";
			
			if (num == 3) {
				System.out.println("������� ��� ������. ");
				scan.nextLine();
				costr = scan.nextLine();
			}
			if (num == 4) {
				System.out.println("������� ����������� �������� ������.");
				scan.nextLine();
				costr = scan.nextLine();
			}
			if (num == 5) {
				System.out.println("������� ����������� �������� ������.");
				scan.nextLine();
				costr = scan.nextLine();
			}
			
			out.writeObject(num);
			out.writeObject(costr);
			}
			
			if (use==2) {
				System.out.println("������� ����� �������.\n6 - ���������� � ������� \n7 - �������� �� ������� \n8 - ��������� ������� � �������");
				
				int num = scan.nextInt();
				int tnum;
				String costr = "";
				
				System.out.println("�������� �������.\n1 - ������ \n2 - ���� ������");
				tnum = scan.nextInt();
				
				if (num==6 && tnum==1) {
					System.out.println("������� ������ ��� ���������� ����� ������ �������������. \n��������_���� ���� ������_��������_����_���������� ������(���� �� ���, ������� 0)_��� ���������_������_������");
					scan.nextLine();
					costr = scan.nextLine();
				
				}
				if (num==6 && tnum==2) {
					System.out.println("������� ������ ��� ���������� ����� ������ �������������. \n��������_��������");
					scan.nextLine();
					costr = scan.nextLine();
				
				}
				
				if (num==7) {
					System.out.println("������� ���� ������, ������� ����� �������.");
					scan.nextLine();
					costr = scan.nextLine();
				}
				
				if (num==8 && tnum==1) {
					System.out.println("������� ������ ��� ��������� ����� ������ �������������.\n���� ������,������� ����� ��������_��������_���� ���� ������_��������_����_���������� ������(���� �� ���, ������� 0)_��� ���������_������_������");
					scan.nextLine();
					costr = scan.nextLine();
				}
				if (num==8 && tnum==2) {
					System.out.println("������� ������ ��� ��������� ����� ������ �������������.\n���� ������,������� ����� ��������_��������_��������");
					scan.nextLine();
					costr = scan.nextLine();
				}
				
				costr = tnum +"_"+costr;
				
				System.out.println(costr);
				
				out.writeObject(num);
				out.writeObject(costr);
			}
			
			
			
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			String str = "";
			while (!str.equals("Stop") ) {
			str = (String) in.readObject();
			System.out.println(str);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
