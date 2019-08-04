import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
public class COMMUNICATION extends JFrame{

	static public JLabel tosize;
	static public JTextField send_txt;
	static public JButton send_btn,retour;
	static public   JList list ;
	static public JPanel pan1,pan2,pan3,pan4,pan5;
	static public DefaultListModel model;
	static public JScrollPane scroll;
	static public Scanner s;
	private static final int MYTHREADS = 30;
	public COMMUNICATION() {
		//creation et personnalisation de la fenetre
		this.setTitle("COMMUNICATION");
	    this.setSize(500,400);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setAlwaysOnTop(true);
	    this.setUndecorated(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    
	  //les elements
	    send_txt=new JTextField(20);
	    send_btn=new JButton("SEND");
	    model=new DefaultListModel();
	    list=new  JList(model);
	    retour=new JButton("RETURN");
	   
	    tosize=new JLabel();
	    
	  
	    
	    
	    
	    scroll=new JScrollPane(list);
	    
	    list.setSize(200, 500);
	    
	  //instancier jpanel
	    pan1=new JPanel();
	    pan2=new JPanel();
	    pan3=new JPanel();
	    pan4=new JPanel();
	    pan5=new JPanel();
	    pan1.setLayout(new GridLayout(2,2));
	    pan2.setLayout(new FlowLayout());
	    pan3.setLayout(new BorderLayout());
	    pan5.setLayout(new BorderLayout());
	    
	    pan1.add(send_txt);
	    pan1.add( tosize);
	    pan1.add(send_btn);
	    
	    //pan1.add(scroll);
	    pan2.add(pan1);
	   
	    pan4.add(retour);
	    pan3.add(pan2,BorderLayout.NORTH);
	    pan3.add(scroll,BorderLayout.CENTER);
	    pan3.add(pan4,BorderLayout.PAGE_END);
	    retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ExpConfigurator();
				dispose();
		
			}
		});
	    
	    send_btn.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					 
					
				      SwingUtilities.invokeLater(new Runnable() {
			          public void run() {
						send();
			          }
			          });
					}
	    });
	    
			
			
	


	    receive();
	    this.setContentPane(pan3);
	    this.setVisible(true);
	    
	    
	    
	    
	    
	}
	
	InputStream input=OpenPort.getPort().getInputStream() ;
	
	//send
	public   void send() {
		String cr;  
		String rd1 = null;
		
		PrintWriter output = new PrintWriter(OpenPort.getPort().getOutputStream());
		
		cr = COMMUNICATION.send_txt.getText();
			if(cr != null)
			{
				output.print(cr);
				
				System.out.println("Sending : " + cr + "\n");
				output.flush();
				
			}
	}
	
	void receive(){
			
			OpenPort.getPort().addDataListener(new SerialPortDataListener() {
				   @Override
				   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
				   @Override
				   public void serialEvent(SerialPortEvent event)
				   {
					  
						Scanner data = new Scanner(OpenPort.getPort().getInputStream());
			            while(data.hasNextLine()) {
			                    String msg = null;
			                    try{msg = data.nextLine();}catch(Exception e){}
			                    
			                    if(msg != null) {
			                    	System.out.println(msg);
			                    	model.addElement(msg);
			                    }
			                    
			                    msg = null;
			            }
			       }
		
			});
	
	

	}	

}
