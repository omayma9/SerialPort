import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ExpConfigurator extends JFrame{
	static public JLabel connected;
static public JButton scan,connect,communication;
static public JComboBox portCombo;
static public JPanel pan,pan1;
static public String[] data;
static public String cr;
static public Object selectedItem;
static public List listEXP;

ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
	
	public ExpConfigurator() {
		//creation et personnalisation de la fenetre
		this.setTitle("EXP CONFIGURATOR");
	    this.setSize(400,100);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setAlwaysOnTop(true);
	    this.setUndecorated(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	   
		//les elements
	    connected=new JLabel();
	    
		scan=new JButton("SCAN");
		connect=new JButton("CONNECT");
		communication =new JButton("COMMUNICATION");
		listEXP=new ArrayList();
		//data= ListOfPorts.list_ports();
		JList list;
		listEXP.add("helloEXP");
		System.out.println(listEXP);
		String[] data={};
		portCombo=new JComboBox(data);
		
		//instancier jpanel
		pan1=new JPanel();
		pan=new JPanel();
		
		//ajouter a pan
		pan1.add(scan);
		pan1.add(portCombo);
		pan1.add(connect);
		pan1.add(communication);
		pan1.add(connected);
		
		
		pan.setLayout(new BorderLayout());
		//pan.add(connected,BorderLayout.LINE_START);
		pan.add(pan1,BorderLayout.CENTER);
		
		//ecouteur
		scan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new SCAN();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			     //IP
//				 executor.execute(sendSCAN("ee_get:100"));
//				 executor.execute(receiveSCAN(SCAN.ipAddress_txt));
//				 //port
//				 executor.execute(sendSCAN("ee_get:160"));
//				 executor.execute(receiveSCAN(SCAN.port_txt));
//				 //intervale
//				 executor.execute(sendSCAN("ee_get:60"));
//				 executor.execute(receiveSCAN(SCAN.intervale_txt));
//				 executor.shutdown();
				 
				dispose();
			}
		});
		
		portCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				selectedItem=portCombo.getSelectedItem();
			}
		});
		
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                new OpenPort(portCombo.getSelectedItem());
				
			}
			
		});
		communication.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new COMMUNICATION();
				//new RECEIVE().receiveData();
				dispose();
			}
		});
		
		
		connected.setText(portCombo.getSelectedItem()+"");
		
	    OpenPort.list_ports();
	    
		this.setContentPane(pan);
	    this.setVisible(true);
	    //this.pack();
		
	}
	

	public static Object getSelectedItem() {
		return selectedItem;
	}

	public static void setSelectedItem(Object selectedItem) {
		ExpConfigurator.selectedItem = selectedItem;
	}
	
//	static Thread sendSCAN(String txt){
//        
//        
//        Thread send=new Thread(){
//        	@Override
//        	public void run() {
//        		PrintWriter output = new PrintWriter(OpenPort.getPort().getOutputStream());
//        		cr=txt;
//        		output.print(cr);
//        		output.flush();
//        	}
//        };
//		return send;
//        	
//       
//	}
//        
//       Thread receiveSCAN(JTextField txt){
//        Thread receive=new Thread(){
//        	@Override
//        	public void run() {
//        		OpenPort.getPort().addDataListener(new SerialPortDataListener() {
// 				   @Override
// 				   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
// 				   @Override
// 				   public void serialEvent(SerialPortEvent event)
// 				   {
// 					  
// 						Scanner data = new Scanner(OpenPort.getPort().getInputStream());
// 			            while(data.hasNextLine()) {
// 			                    String msg = null;
// 			                    try{msg = data.nextLine();}catch(Exception e){}
// 			                    
// 			                    if(msg != null) {
// 			                    	System.out.println(msg);
// 			                    	txt.setText(msg);
// 			                    }
// 			                    
// 			                    msg = null;
// 			            }
// 			       }
// 		
// 			});
//        	}
//        };
//		return receive;
//       }
//     
//        
        
       
	
  
}
