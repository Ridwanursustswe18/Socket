package socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static ArrayList<ClienHandler> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(3);

	public static void main(String[] args) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(4100);
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					try {
						String str = bf.readLine();
						String[] arr = str.split(":");
						
						//String name = bf.readLine();
						if(arr.length == 1)
						for(int i = 0; i<clients.size();i++) {
							 ClienHandler tmpClient = clients.get(i);
					            tmpClient.dos.writeUTF(str);
					            tmpClient.dos.flush();
					           // break;
						}
						else if(arr.length >=2) {
						
							for(int i = 0; i<clients.size();i++) {
								//System.out.println(arr[1] + arr[0]+ "K");
								 ClienHandler tmpClient = clients.get(i);
								 //System.out.println(tmpClient.userName);
								 if(arr[0].equals(tmpClient.userName)) {
						            tmpClient.dos.writeUTF(arr[1]);
						           // System.out.println(arr[1] + arr[0]+ "K");
						            tmpClient.dos.flush();
								 }
						           // break;
							}	
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		while (true) {

			Socket socket = serverSocket.accept();
			
        
			ClienHandler clienthandler = new ClienHandler(socket);
			clients.add(clienthandler);
			pool.execute(clienthandler);
		}

	}

}
