import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;


public class SendMessage
{
        public void SendSMS(String phNumber, String message) throws Exception
        {
                OutboundNotification outboundNotification = new OutboundNotification();
                System.out.println("Example: Send message from a serial gsm modem.");
                System.out.println(Library.getLibraryDescription());
                System.out.println("Version: " + Library.getLibraryVersion());
                SerialModemGateway gateway = new SerialModemGateway("modem", "COM4", 9600, "", "");
                gateway.setInbound(true);
                gateway.setOutbound(true);
                Service.getInstance().setOutboundMessageNotification(outboundNotification);
                Service.getInstance().addGateway(gateway);
                Service.getInstance().startService();
                System.out.println();
                System.out.println("Modem Information:");
                System.out.println("  Manufacturer: " + gateway.getManufacturer());
                System.out.println("  Model: " + gateway.getModel());
                System.out.println("  Serial No: " + gateway.getSerialNo());
                System.out.println("  SIM IMSI: " + gateway.getImsi());
                System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
                System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
                System.out.println();
                OutboundMessage msg = new OutboundMessage(phNumber, message);
                Service.getInstance().sendMessage(msg);
                System.out.println(msg);
                
                System.out.println("");
                
                System.out.println("Now Sleeping - Hit <enter> to terminate.");
                System.in.read();
                Service.getInstance().stopService();
        }

        public class OutboundNotification implements IOutboundMessageNotification
        {
                public void process(AGateway gateway, OutboundMessage msg)
                {
                        System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
                        System.out.println(msg);
                }
        }

        public static void main(String args[])
        {
        		MessageUI ui = new MessageUI();
        		SendMessage app = new SendMessage();
                try
                {
                        app.SendSMS(ui.getPhoneNumber(), ui.getMessage());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}