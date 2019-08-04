import java.util.Enumeration;

import com.fazecast.jSerialComm.SerialPort;





public class OpenPort {
	static SerialPort port;
	static SerialPort[] list = null;
	
	 static Enumeration portList;

	 
	public OpenPort(Object p) {
		
		
//		
//		portList = CommPortIdentifier.getPortIdentifiers();
//
//	    while (portList.hasMoreElements()) {
//	      portId = (CommPortIdentifier) portList.nextElement();
//	      if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//	        // if (portId.getName().equals("COM1")) {
//	        if (portId.getName().equals(p)) {
//	          MainClass reader = new MainClass();
//	        }
//	      }
//	    }
//	    
//	}
//	    
//	    public class MainClass {
//	    	MainClass(){
//	        try {
//	          port = (SerialPort) portId.open("MainClassApp", 2000);
//	        } catch (PortInUseException e) {
//	        }
//	    }
//	    }
	        
      for(SerialPort port :getList()){
			
			if(p.equals( port.getSystemPortName())){
				           // port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
							port.openPort();
							this.port=port;
							System.out.println("connected");
							ExpConfigurator.connected.setText("Conneceted to: "+ExpConfigurator.getSelectedItem());
							break;
						}
			
			
			
		}
	}

	
	

	
		//list of ports for combo
		
		public static SerialPort getPort() {
		return port;
	}





	public static void setPort(SerialPort port) {
		OpenPort.port = port;
	}
//
//
//
//
//
		static  void list_ports(){
			list = SerialPort.getCommPorts();
		  
		  while(list==null )
	        {
	        	list = SerialPort.getCommPorts();
	        }

		
	          for(SerialPort port:list){
	        	  ExpConfigurator.portCombo.setEnabled(true);
	              ExpConfigurator.portCombo.addItem(port.getSystemPortName());
	              //ExpConfigurator.portCombo.setSelectedItem("COM1");
	          }
		  
		}
		
		static SerialPort[] returnList(){
			
			list = SerialPort.getCommPorts();
						return list;
			
		}





		public static SerialPort[] getList() {
			return list;
		}





		public static void setList(SerialPort[] list) {
			OpenPort.list = list;
		}
		

	
	

	    
}
