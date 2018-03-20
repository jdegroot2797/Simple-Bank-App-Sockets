package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client {
	
	 public static void main(String[] args) {

			Socket clientSocket;		// TCP/IP socket

			try {

		             /* step 1: connect to the server
		                        IP address/server name: "localhost"
		                        port number:            8000
		              */

			     clientSocket = new Socket( InetAddress.getByName( "localhost" ),
		                                        8000 );

		                                      // InetAddress.getByName( "127.0.0.1" )

			     System.out.println( "Connected to " +
				                 clientSocket.getInetAddress().getHostName() );

			         /* step 2: connect input and output streams to the socket
		              */

			     DataOutputStream dosToServer = new DataOutputStream(
		                                                clientSocket.getOutputStream() );

			     DataInputStream  disFromServer= new DataInputStream(
		                                                 clientSocket.getInputStream() );
			     
	        	//added object output and input stream to send and receive objects
	        	
	        	ObjectOutputStream oosToClient = new ObjectOutputStream(
                        								clientSocket.getOutputStream() );

	        	ObjectInputStream  oisFromClient = new ObjectInputStream(
                          								clientSocket.getInputStream() );

	        	

			     System.out.println( "I/O streams connected to the socket" );


			     /* step 3: exchange data with the server
		              */
		         /* BufferedReader keyboard = new BufferedReader(
		                                           new InputStreamReader( System.in ) ); */

		         @SuppressWarnings("resource")
				Scanner keyboard = new Scanner( System.in );
		         
		         //1. Welcome message 
	        	 System.out.println("A BankServer instance is running! ");
	        	 System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a, MMMM dd, yyyy")));
		         
	        	 for (int i=1; i < 5; i++) {

						try {
		                     //2. keyboard input - get client name
				             System.out.print( "Enter your name: " );

		                     String name = ( keyboard.nextLine() );

				             // send client name data to the server
		                     dosToServer.writeChars(name);
		                     dosToServer.flush();		//  data sent immediately!
		                     
		                     int exit = 0;
		                     
		                     //3. keyboard input - show client menu		                 
		                     String menuOption; //menu option input
		                     
		                     do {
		                     //menu option
		                     System.out.println("Enter a command (0 - exit, 1 - show all clients or 2 - search clients by balance): ");
		                     
		                     menuOption = (keyboard.nextLine());
		                     
		                     //check for valid menu option
		                     if(Integer.parseInt(menuOption) != 0 || Integer.parseInt(menuOption) != 1 || Integer.parseInt(menuOption) != 2)
		                     {
		                    	 System.out.println("Please enter a valid option");
		                    	 dosToServer.flush();
		                     }		          
		                     else
		                    	 exit = 1;
		                     
		                     }while(exit == 0);
		                     
		                     //send client menu option to the server
		                     dosToServer.writeInt(Integer.parseInt(menuOption));
		                     dosToServer.flush();
		                     
		                     //4. display for selected menu option
		                     
		                     //0 - client connection is terminated
		                     if(Integer.parseInt(menuOption) == 0)
		                     {
		                    	 System.out.println("\n Thank You!");
		        			     dosToServer.close();
		        			     disFromServer.close();
		        			     clientSocket.close();
		                     }
		                     //1 - client receives and prints a list of accounts
		                     else if(Integer.parseInt(menuOption) == 1)
		                     System.out.println("-------------------------------------------");
		                     
		                     
						}
						catch( EOFException eof ) { // loss of connection

					    System.out.println( "The server has terminated connection!" ); }

						catch(IOException e ) { System.out.println( "I/O errors in data exchange" );
		                                        e.printStackTrace(); 								}
			     }
		              System.out.println( "Client: data exchange completed" );

			     /* step 4: close the connection to the server
		              */
			     System.out.println( "Client: closing the connection..." );

			     dosToServer.close();
			     disFromServer.close();
			     clientSocket.close();
			}
		    catch( IOException ioe ) { System.out.println( "I/O errors in socket connection" );
		                               ioe.printStackTrace(); }

		    System.out.println( "... the client is going to stop running..." );

		   } // end main
}
