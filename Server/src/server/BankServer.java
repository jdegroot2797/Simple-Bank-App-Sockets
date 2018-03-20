package server;

// a simple client/server application
// console-based

// a "mutlithreaded" SERVER program that uses a stream socket connection
// use of DataOutputStream and DataInputStream classes

import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.lang.reflect.Array;

/**
 * Adds six accounts to the bank in it's parameters
 * 
 * @param bank Bank to which new accounts will be added into
 */
/*public static void loadBank(Bank bank){
	Account newAccount1 = new GIC("John Doe", "D1234", "6000.00", 2, 1.5);
	Account newAccount2 = new Chequing("John Doe", "E5678", "15000.00", 0.75);
	Account newAccount3 = new Savings("John Doe", "F9801", "8000.00", 0.15);
	Account newAccount4 = new GIC("Mary Ryan", "A1234", "15000.00", 4 , 2.5);
	Account newAccount5 = new Chequing("Mary Ryan", "B5678", "15000.00", 0.75);
	Account newAccount6 = new Savings("Mary Ryan", "C9012", "8000.00", 0.15);
	bank.addAccount(newAccount1);
	bank.addAccount(newAccount2);
	bank.addAccount(newAccount3);
	bank.addAccount(newAccount4);
	bank.addAccount(newAccount5);
	bank.addAccount(newAccount6);
}*/


public class BankServer 
{
	   public static void main(String[] args) {

		ServerSocket serverSocket;		// TCP server socket used for listening

	    System.out.println( "*** a multithreaded server is running ***" );

		try {
	             /* step 1: create a server socket
	                        port number:     8000
	              */

				serverSocket = new ServerSocket( 8000 );

	             /* setp 2: a loop that listens for connections and
	                                    creates THREADS with sockets
	              */

	            int count = 1;

		     	while (true) {

		        	System.out.println( "listening for a connection..." );

	            	Socket socketConnection
	                          = serverSocket.accept();  // listen and wait
	                                                    // socketConnection: a TCP socket
							    						// that is connected
	                                                    // to the client

	            	System.out.println( "start a thread for client #" + count );
		        	System.out.println( "\t host name: " +
	                                        socketConnection.getInetAddress().getHostName() +
	                                    "\t IP address: " +
	                                        socketConnection.getInetAddress().getHostAddress() );

	                // System.out.println( "at port: " + socketConnection.getPort() );

	            	Thread t = new HandleClientThread( socketConnection );
	            	t.start();

	            	count++;
		     	}
	     }
		 catch(IOException e ) { e.printStackTrace();     }

	     System.out.println( "*** the server is going to stop running ***" );

	   } // end main
	}

class HandleClientThread extends Thread {

         private Socket connection;

         public HandleClientThread(Socket sock) { connection = sock; }

         public void run() {

            try {
	        	/* connect input and output streams to the socket
                 */

	        	DataOutputStream dosToClient = new DataOutputStream(
                                                      connection.getOutputStream() );

	        	DataInputStream  disFromClient = new DataInputStream(
                                                        connection.getInputStream() );
	        	
	        	//added object output and input stream to send and receive objects
	        	
	        	ObjectOutputStream oosToClient = new ObjectOutputStream(
                        								connection.getOutputStream() );

	        	ObjectInputStream  oisFromClient = new ObjectInputStream(
                          								connection.getInputStream() );

	        	
	        	System.out.println( "I/O streams connected to the socket" );

	        	  /* step 4: exchange data with THE client
	              */
		     
	        	//1. Welcome message
	        	System.out.println("A BankServer instance is running! ");
	        	System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a, MMMM dd, yyyy")));
        	 
	         try {
                 while (true) {  // keep on getting data from the client

		             //2. read client data name
                     char clientName = disFromClient.readChar();
                     System.out.println( "+ name received: " + clientName );

                     //3. read client data menu option
                     int menuOption = disFromClient.readInt();
                     System.out.println("+ command received: " + menuOption);
                     
                     //4. respond to client data menu option
                     if(menuOption == 0)
                     {}
                     
                     else if(menuOption == 1)
                     {
                    	 Bank bank = null;
                    	 Account[] array = new Account[bank.accounts.size()];
                    	 for(int i = 0; i < bank.indexOfAccounts(); i++) 
                    	 {
                    		 oosToClient.writeObject(array[i]);
                    	 }

		          	} // end while
                 }
		     }
              	catch( EOFException eof ) { System.out.println(
                                               "*** the CLIENT has terminated connection ***" );
                                       	  }

	     		/* close the connection to the client
              	 */
	     		dosToClient.close();
	     		disFromClient.close();
             	connection.close();
        	}
			catch(IOException e ) { e.printStackTrace();    }

        } // end run

    
} // end HandleClientThread

