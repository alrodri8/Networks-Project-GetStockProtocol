import java.io.*;
import java.net.*;
import java.util.*;

class UDPServer
{
	private static int port = 9001;
	public static void main(String args[]) throws Exception
	{
         DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            boolean state = true;
            boolean temp = false;
            Vector<String> users = new Vector();
    		Vector<String> userAdded = new Vector();

            
//            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//            while (interfaces.hasMoreElements()){
//                NetworkInterface current = interfaces.nextElement();
//                System.out.println(current);
//                if (!current.isUp() || current.isLoopback() || current.isVirtual()) continue;
//                Enumeration<InetAddress> addresses = current.getInetAddresses();
//                while (addresses.hasMoreElements()){
//                    InetAddress current_addr = addresses.nextElement();
//                    if (current_addr.isLoopbackAddress()) continue;
//                    if (current_addr instanceof Inet4Address)
//                    	  System.out.println(current_addr.getHostAddress());
////                    	else if (current_addr instanceof Inet6Address)
////                    	  System.out.println(current_addr.getHostAddress());
//                }
//            }

            while(state)
            {
            	receiveData = new byte[1024];
            	DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            	serverSocket.receive(receivePacket);
            	String sentence = 
            			new String( receivePacket.getData(), 
            					receivePacket.getOffset(), 
            					receivePacket.getLength(), "UTF-8");
            	System.out.println("RECEIVED: " + sentence);
            	InetAddress IPAddress = receivePacket.getAddress();
            	int port = receivePacket.getPort();
            	String capitalizedSentence = sentence.toUpperCase();
//            	sendData = capitalizedSentence.getBytes();
//            	DatagramPacket sendPacket =
//            			new DatagramPacket(sendData, sendData.length, IPAddress, port);
//            	serverSocket.send(sendPacket);
            	
            	if (capitalizedSentence.equals("OFF"))
            	{
                	  state = false;
                	  System.out.println("Server shutting down");
                	  serverSocket.close();
            	}
            	
            	String[] commands = sentence.split(";");
            	for (int i = 0; i < commands.length; i++)
            	{
            		System.out.print("Command " + i + ": ");
            		
            		String[] parts = commands[i].split(",");
            		
        			if (parts[0].equals("REG"))
        			{
        				if (!users.contains(parts[1].toUpperCase()))
        				{
        					users.add(parts[1].toUpperCase());
//        					userAdded = 1;
        					System.out.println(users);
        					
        	            	sendData = "ROK".getBytes();
        	            	DatagramPacket sendPacket =
        	            			new DatagramPacket(sendData, sendData.length, IPAddress, port);
        	            	serverSocket.send(sendPacket);

        				}
                		else
        				{
//                            byte[] sendData1 = new byte[1024];
        					System.out.println("Print else");
//        					String temp1 = new String("UAE");
//        					sendData = temp1.getBytes();
//        	            	DatagramPacket sendPacket1 =
//        	            			new DatagramPacket(sendData, sendData.length, IPAddress, port);
//        	            	serverSocket.send(sendPacket1);
        	            	sendData = "UAE".getBytes();
        	            	DatagramPacket sendPacket =
        	            			new DatagramPacket(sendData, sendData.length, IPAddress, port);
        	            	serverSocket.send(sendPacket);

        	            	
        				}
        			}

            		
            		System.out.println();
//            		System.out.println(commands[i]);
            	}
//                  System.out.println("sentence: " + sentence + "capitalizedSentence: " + capitalizedSentence + "asdf");
               }
            }   
	}
// - See more at: https://systembash.com/a-simple-java-udp-server-and-udp-client/#sthash.ui72SbBg.dpuf