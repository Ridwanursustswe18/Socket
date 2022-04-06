package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost", 4100);
		
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dous = new DataOutputStream(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your name: ");
		String userName = br.readLine();
		dous.writeUTF(userName);
//		ServerConnection serverConnection = new ServerConnection(socket);
//		new Thread (serverConnection).start();
		new Thread(() -> {
			while (true) {
				
				String msg;
				try {
					
					msg = dis.readUTF();
					System.out.println("Server says: "+ msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		String str1 = "", str2 = "";
		while (!str1.equals("Stop")) {

			str2 = br.readLine();
			dous.writeUTF(str2);
			dous.flush();

		}
		dous.close();
		socket.close();

	}

}
