import java.io.*; 
import java.net.*; 
class UDPClient {    
	public static void main(String args[]) throws Exception    
	{       
		
		//blah
		//System.out.println("Enter Server IP Address:");
		//BufferedReader ipReader = new BufferedReader(new InputStreamReader(System.in));
		//String IP = ipReader.readLine();
		
		while(true){
		
		BufferedReader inFromUser = 
				new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("10.226.0.230");
		//InetAddress IPAddress = InetAddress.getByName("localhost");
		//InetAddress IPAddress = InetAddress.getByName(IP);
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = inFromUser.readLine();
		sendData=null;
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9001);
		clientSocket.send(sendPacket);       
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String modifiedSentence = new String(receivePacket.getData());
		System.out.println("FROM SERVER:" + modifiedSentence);
		
		sentence = "";
		modifiedSentence ="";
		} 
	}
	} 

// - See more at: https://systembash.com/a-simple-java-udp-server-and-udp-client/#sthash.ui72SbBg.dpuf