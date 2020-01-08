import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.*;
import java.awt.event.*;

public class MessageUI {

	private JFrame frame;
	private JTextField PhoneNumber;
	private JTextField MessageBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageUI window = new MessageUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public MessageUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		PhoneNumber = new JTextField();
		PhoneNumber.setBounds(10, 11, 266, 33);
		frame.getContentPane().add(PhoneNumber);
		PhoneNumber.setColumns(10);
		
		MessageBox = new JTextField();
		MessageBox.setBounds(10, 55, 266, 158);
		frame.getContentPane().add(MessageBox);
		MessageBox.setColumns(10);
		
		JButton btnSendButton = new JButton("Send");
		btnSendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSendButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnSendButton);
		
		MessageUI ui = new MessageUI();
		SendMessage app = new SendMessage();
		
		btnSendButton.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		    	try
		        {
		                app.SendSMS(ui.getPhoneNumber(), ui.getMessage());
		        }
		        catch (Exception ex)
		        {
		                ex.printStackTrace();
		        }      
		    }  
		    });  
	}
	
	public String getPhoneNumber() {
	     return PhoneNumber.getText();
	}
	
	public String getMessage() {
	     return MessageBox.getText();
	}
}
