import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class SCAN extends JFrame{
	static public JLabel ipAddress,port,intervale;
	static public JTextField ipAddress_txt,port_txt,intervale_txt;
	
	static public JButton modify,retour,get;
	static public JPanel pan1,pan2;
	static public String cr;
	
	static public List list;

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);

	public SCAN() throws InterruptedException {
		//creation et personnalisation de la fenetre
		this.setTitle("CONNECT");
	    this.setSize(500,150);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setAlwaysOnTop(true);
	    this.setUndecorated(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	  //les elements
	    
	    ipAddress=new JLabel("IP:");
	    port=new JLabel("PORT:");
	    intervale=new JLabel("INTERVALE:");
	    
	    ipAddress_txt=new JTextField(20);
	    port_txt=new JTextField(20);
	    intervale_txt=new JTextField(20);
	    list=new ArrayList();
	    
	    modify=new JButton("MODIFY");
	    retour=new JButton("RETURN");
	    
	    
	    ipAddress_txt.disable();
	    port_txt.disable();
	    intervale_txt.disable();
	    
	  //instancier jpanel
	    pan1=new JPanel();
	    pan2=new JPanel();
	    
	    
	    pan1.setLayout(new GridLayout(3,2));
	    pan2.setLayout(new FlowLayout());
	    
	    
	    pan1.add(ipAddress);
	    pan1.add(ipAddress_txt);
	    pan1.add(port);
	    pan1.add(port_txt);
	    pan1.add(intervale);
	    pan1.add(intervale_txt);
	    
	   
	    
	    pan2.add(pan1);
	    pan2.add(modify);
	    pan2.add(retour);
	    
	    
	  //ecouteur
	    modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SCAN.modify.setText("VALIDATE");
				ipAddress_txt.enable();
				port_txt.enable();
				intervale_txt.enable();
				
				ipAddress_txt.setText(""); 
				intervale_txt.setText("");
				port_txt.setText("");
				if(e.getActionCommand()=="VALIDATE"){
					
					//CONNECT.modify.setText("VALIDATE");
					ipAddress_txt.disable();
					port_txt.disable();
					intervale_txt.disable();
				}
				
			}
		});
	    retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ExpConfigurator();
				dispose();
			}
		});
	    
	    
	   
	 
	//ip					
	 //executor.execute(new ThreadDemo("ee_get:100",SCAN.ipAddress_txt));
	 
	//port
	 //executor.execute(new ThreadDemo("ee_get:160",SCAN.port_txt));

	 //intervale
	 //executor.execute(new ThreadDemo("ee_get:60",SCAN.intervale_txt));
	
	 //executor.shutdown();
	      
		Thread t1=new ThreadDemo("ee_get:100",SCAN.ipAddress_txt);
	    Thread t2=new ThreadDemo1();
		Thread t3=new ThreadDemo("ee_get:60",SCAN.intervale_txt);
		
		
	
		t1.start();
		
		
		t3.start();
		
	    this.setContentPane(pan2);
	    this.setVisible(true);
	    
	    
	}
	
	
	public static JTextField getIpAddress_txt() {
		return ipAddress_txt;
	}
	public static void setIpAddress_txt(JTextField ipAddress_txt) {
		SCAN.ipAddress_txt = ipAddress_txt;
	}
	public static JTextField getPort_txt() {
		return port_txt;
	}
	public static void setPort_txt(JTextField port_txt) {
		SCAN.port_txt = port_txt;
	}
	public static JTextField getIntervale_txt() {
		return intervale_txt;
	}
	public static void setIntervale_txt(JTextField intervale_txt) {
		SCAN.intervale_txt = intervale_txt;
	}
	
	 class ThreadDemo extends Thread {
		String cr=null;
		JTextField txt_;
		String txt;
		private Thread t;
		ThreadDemo(String txt,JTextField txt_) {
		      this.txt = txt;
		      this.txt_ = txt_;
		   }
		
        	@Override
        	public void run() {
        		  
                  sendReceive(txt,txt_);
                  
                  try {Thread.sleep(1000);} catch (InterruptedException ie) {}
        	}
        	
        	
        	
        	
        }
	 
	 class ThreadDemo1 extends Thread {
			
			
	        	@Override
	        	public void run() {
	        		  
	                 // sendReceive(txt,txt_);
	                  sendReceive1();
	        		System.out.println("hello");
	        		
	                  try {ThreadDemo1.sleep(1000);} catch (InterruptedException ie) {}
	                  //SCAN.port_txt.setText("hi");
	        	}
	        	
	        	
	        	
	        	
	        }
	 
		
       
	synchronized  void sendReceive(String txt,JTextField txt_){
    		PrintWriter output = new PrintWriter(OpenPort.getPort().getOutputStream());
    		cr=txt;
    		output.print(cr);
    		output.flush();
    		
    		
				   
					  
						Scanner data=new Scanner(OpenPort.getPort().getInputStream());
						
						
						
			            while(data.hasNextLine()) {
			                    String msg = null;
			                    Object item=null;
			                    try{msg= data.nextLine();}catch(Exception e){}
			                    
			                    if(msg != null) {
			                    	
			                    	
			                    	txt_.setText(msg);
			                    	list.add(msg);
			                    	System.out.println(list);
			                    	System.out.println(item);
			                    	
			                    	
			                    }
			                    msg = null;
			                   
			                  
			            }
			           
			       
		
			
}
	
	synchronized  void sendReceive1(){
		PrintWriter output = new PrintWriter(OpenPort.getPort().getOutputStream());
		cr="ee_get:60";
		output.print(cr);
		output.flush();
		
		OpenPort.getPort().addDataListener(new SerialPortDataListener() {
			   @Override
			   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
			   @Override
			   public void serialEvent(SerialPortEvent event)
			   {
				  
					Scanner data=new Scanner(OpenPort.getPort().getInputStream());
					
					
					
		            while(data.hasNextLine()) {
		                    String msg = null;
		                    Object item=null;
		                    try{msg= data.nextLine();}catch(Exception e){}
		                    
		                    if(msg != null) {
		                    	
		                    	
		                    	SCAN.port_txt.setText(msg);
		                    	list.add(msg);
		                    	System.out.println(list);
		                    	System.out.println(item);
		                    	
		                    	
		                    }
		                    msg = null;
		                   
		                  
		            }
		           
		       }
	
		});
}
        
       Thread receiveSCAN(JTextField txt){
        Thread receive=new Thread(){
        	@Override
        	public void run() {
        		try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}  
        		OpenPort.getPort().addDataListener(new SerialPortDataListener() {
 				   @Override
 				   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
 				   @Override
 				   public void serialEvent(SerialPortEvent event)
 				   {
 					  
 						Scanner data=new Scanner(OpenPort.getPort().getInputStream());
 						
 						
 						
 			            while(data.hasNextLine()) {
 			                    String msg = null;
 			                    Object item=null;
 			                    try{msg= data.nextLine();}catch(Exception e){}
 			                    
 			                    if(msg != null) {
 			                    	
 			                    	
 			                    	txt.setText(msg);
 			                    	list.add(msg);
 			                    	System.out.println(list);
 			                    	System.out.println(item);
 			                    	
 			                    	
 			                    }
 			                    msg = null;
 			                   
 			                  
 			            }
 			           
 			       }
 		
 			});
        	}
        };
        
        //System.out.println(list);
        
		return receive;
       }
     
        
}
