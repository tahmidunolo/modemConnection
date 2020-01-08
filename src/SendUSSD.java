// SendUSSD.java - Sample application.
//
// This application shows you the basic procedure for sending messages.
// You will find how to send synchronous and asynchronous messages.
//
// For asynchronous dispatch, the example application sets a callback
// notification, to see what's happened with messages.

import org.smslib.*;
import org.smslib.AGateway;
import org.smslib.AGateway.Protocols;
import org.smslib.IUSSDNotification;
import org.smslib.Library;
import org.smslib.Service;
import org.smslib.USSDResponse;
import org.smslib.modem.SerialModemGateway;
import java.util.*;

public class SendUSSD
{
        public void doIt() throws Exception
        {
                Service srv;
                USSDNotification ussdNotification = new USSDNotification();
                System.out.println("Example: Send USSD Command from a serial gsm modem.");
                System.out.println(Library.getLibraryDescription());
                System.out.println("Version: " + Library.getLibraryVersion());
                srv = Service.getInstance();
                SerialModemGateway gateway = new SerialModemGateway("modem", "COM4", 9600, "", "");
                gateway.setProtocol(Protocols.PDU);
                gateway.setInbound(true);
                gateway.setOutbound(true);
                gateway.setSimPin("0000");
//                srv.setUSSDNotification(ussdNotification);
                srv.addGateway(gateway);
                
                System.out.println(new Date());
                
                srv.startService();
                srv.setUSSDNotification(ussdNotification);
                
                USSDRequest req = new USSDRequest("*566#");
                srv.sendUSSDRequest(req, gateway.getGatewayId());
                
                System.out.println(new Date());
                
                System.out.println();
                System.out.println("Modem Information:");
                System.out.println("  Manufacturer: " + gateway.getManufacturer());
                System.out.println("  Model: " + gateway.getModel());
                System.out.println("  Serial No: " + gateway.getSerialNo());
                System.out.println("  SIM IMSI: " + gateway.getImsi());
                System.out.println("  Signal Level: " + gateway.getSignalLevel() + "%");
                System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
                System.out.println();
                
//                USSDSession session = new USSDSession(gateway.getGatewayId());
                
//                System.out.println(session);
                
                System.out.println(new Date());
                
//                System.out.println(session.sendRequest(req));
                
                System.out.println(srv.getUSSDNotification());
                
//                System.out.println(gateway.sendUSSDRequest(req));
                
//                System.out.println(req.getRawRequest());
                
//                System.out.println(new Date());

//      String resp = gateway.sendUSSDCommand("*2#"); // not working; output -> null
//      String resp = gateway.sendCustomATCommand("AT+CUSD=1,\"*2#\",15\r"); // not working; output -> error
//      System.out.println(resp);

      System.out.println("Now Sleeping - Hit <enter> to terminate.");
                System.in.read();
                srv.stopService();
        }

   public class USSDNotification implements IUSSDNotification
   {
      public void process(AGateway gateway, USSDResponse response) {
                        System.out.println("USSD handler called from Gateway: " + gateway.getGatewayId());
                        System.out.println(response);
      }
   }

        public static void main(String args[])
        {
                SendUSSD app = new SendUSSD();
                try
                {
                        app.doIt();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}