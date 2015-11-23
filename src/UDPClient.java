import java.io.*; 
import java.net.*; 
import java.util.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

class UDPClient {    
	private static int port = 9001;
	
	public static void main(String args[]) throws Exception    
	{       
		BufferedReader inFromUser = 
				new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
//		InetAddress IPAddress = InetAddress.getByName("10.224.2.155");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		while (true)
		{
			String sentence = inFromUser.readLine();
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = 
					new DatagramPacket(sendData, sendData.length, IPAddress, port);
			clientSocket.send(sendPacket);       
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
		}
//		Enumeration en = NetworkInterface.getNetworkInterfaces();
//		while(en.hasMoreElements()){
//		    NetworkInterface ni=(NetworkInterface) en.nextElement();
//		    Enumeration ee = ni.getInetAddresses();
//		    while(ee.hasMoreElements()) {
//		        InetAddress ia= (InetAddress) ee.nextElement();
//		        System.out.println(ia.getHostAddress());
//		        }
//		    }		
		}
	
	} 

// - See more at: https://systembash.com/a-simple-java-udp-server-and-udp-client/#sthash.ui72SbBg.dpuf