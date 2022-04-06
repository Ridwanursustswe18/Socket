package socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClienHandler implements Runnable{
	public Socket socket;
	public BufferedReader br;
	public DataOutputStream dos;
	public DataInputStream dis;
	public String userName;
	
	public ClienHandler(Socket socket) throws IOException {
		this.socket = socket;
		 br = new BufferedReader(new InputStreamReader(System.in));
		 dos = new DataOutputStream(socket.getOutputStream()); 
		 dis = new DataInputStream(socket.getInputStream());
		 userName = dis.readUTF();
	}
	@Override
	public void run() {
		System.out.println(userName + " " + " connected");
		// TODO Auto-generated method stub
		String str1 = "", str2 = "";
		try {
			
			
		while(!str1.equals("Stop")) {
			
			str1 = dis.readUTF();
			System.out.println( userName + " says: "+str1);

			
			
			
		}
		
		
		
	}catch(IOException e) {
		e.printStackTrace();
	}
		try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		
	}
	
