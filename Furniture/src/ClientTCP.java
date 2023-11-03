
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {

	public static void main(String args[]) {
		try {
			

			Scanner scan = new Scanner(System.in);
			
			System.out.println("Выберите пользователя");
			System.out.println("1 - User \n2 - Admin");
			int use = scan.nextInt();
			
			String login = "admin";
			int password = 123321;
			int att = 0;
			scan.nextLine();
			if (use==2) {
			while(true) {
				System.out.println("Введите логин");
				
				String log = scan.nextLine();
				if (log.equals(login)) {
					System.out.println("Введите пароль");
					int pass = scan.nextInt();
					if (pass == password) {
						System.out.println("Пароль верный, добро пожаловать!");
						break;
					}
					else {
						System.out.println("Вы ввели неправильный пароль. Попробуйте еще раз");
						att++;
					}
				}
				else {
					System.out.println("Попробуйте еще раз");
					att++;
				}
				if (att>5) {
					System.out.println("Слишком много попыток. Вы автоматически переведены на User!");
					use = 1;
					break;
				}
			}
			}
			
			while(true) {
			
				Socket clientSocket = new Socket("localhost", 1500);
				ObjectOutputStream out =new ObjectOutputStream(clientSocket.getOutputStream());
			
			
		
			if (use==1) {
			System.out.println("Введите цифру запроса. \n1 - Вывести всю мебель \n2 - Вывести все типы мебели \n3 - Вывести всю мебель по заданному типу \n4 - Вывести всю мебель, у которой ширина больше заданной \n5 - Вывести всю мебель, высота которой больше заданной");
			
			int num = scan.nextInt();
			String costr = "";
			
			if (num == 3) {
				System.out.println("Введите тип мебели. ");
				scan.nextLine();
				costr = scan.nextLine();
			}
			if (num == 4) {
				System.out.println("Введите минимальную желаемую ширину.");
				scan.nextLine();
				costr = scan.nextLine();
			}
			if (num == 5) {
				System.out.println("Введите минимальную желаемую высоту.");
				scan.nextLine();
				costr = scan.nextLine();
			}
			
			out.writeObject(num);
			out.writeObject(costr);
			}
			
			if (use==2) {
				System.out.println("Введите цифру запроса.\n6 - добавление в таблицу \n7 - удаление из таблицы \n8 - изменение элемета в таблице");
				
				int num = scan.nextInt();
				int tnum;
				String costr = "";
				
				System.out.println("Выберите таблицу.\n1 - мебель \n2 - типы мебели");
				tnum = scan.nextInt();
				
				if (num==6 && tnum==1) {
					System.out.println("Введите данные для добавления через нижнее подчеркивание. \nНазвание_Айди типа мебели_Материал_Цвет_Количество дверей(если их нет, ставьте 0)_Тип установки_Ширину_Высоту");
					scan.nextLine();
					costr = scan.nextLine();
				
				}
				if (num==6 && tnum==2) {
					System.out.println("Введите данные для добавления через нижнее подчеркивание. \nНазвание_Описание");
					scan.nextLine();
					costr = scan.nextLine();
				
				}
				
				if (num==7) {
					System.out.println("Введите айди строки, которую нужно удалить.");
					scan.nextLine();
					costr = scan.nextLine();
				}
				
				if (num==8 && tnum==1) {
					System.out.println("Введите данные для изменения через нижнее подчеркивание.\nАйди строки,которую нужно изменить_Название_Айди типа мебели_Материал_Цвет_Количество дверей(если их нет, ставьте 0)_Тип установки_Ширину_Высоту");
					scan.nextLine();
					costr = scan.nextLine();
				}
				if (num==8 && tnum==2) {
					System.out.println("Введите данные для изменения через нижнее подчеркивание.\nАйди строки,которую нужно изменить_Название_Описание");
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
