import java.awt.EventQueue;
import java.net.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.xml.crypto.Data;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class client {

	private JFrame frame;
	private JTextField user;
	private JTextField qset;
	Socket soc;
	private JTextField ques_set;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public client() throws UnknownHostException, IOException {
		initialize();
					
		ques_set.setText("Click on Question Button");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 638, 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		user = new JTextField();
		user.setBounds(128, 16, 126, 39);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(32, 28, 114, 15);
		frame.getContentPane().add(lblUsername);
		
		qset = new JTextField();
		qset.setBounds(128, 67, 133, 38);
		frame.getContentPane().add(qset);
		qset.setColumns(10);
		
		JLabel lblQues = new JLabel("CodeSet");
		lblQues.setBounds(32, 68, 70, 15);
		frame.getContentPane().add(lblQues);
		
		final JTextArea code = new JTextArea();
		code.setBounds(181, 154, 415, 458);
		frame.getContentPane().add(code);
		
		JButton send = new JButton("Submit");
		send.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					soc=new Socket("localhost",12345);
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} // request a connection with server.
				
				String str;
				str=code.getText(); //textArea.getText();
				
				String ques;
				ques=qset.getText(); //textField.getText();
				
				InputStream in;
				OutputStream out;
				DataInputStream din=null;
				DataOutputStream dout = null;
				
				try {
				
					in = soc.getInputStream();
					out =soc.getOutputStream();
		
					din=new DataInputStream(in);
					dout=new DataOutputStream(out);
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					dout.writeUTF(user.getText());
					dout.writeUTF(ques);
					dout.writeUTF(str);
					String output=null;
					output=din.readUTF();
					//System.out.println("client side output: "+ output);
					code.append("\n\n" + "!!!..OUTPUT..!!!"+ "\n\n");
					code.append(output);
					//result.setText(output);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			try {
				soc.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
			}
		});
		send.setBounds(178, 633, 106, 38);
		frame.getContentPane().add(send);
		
		JLabel lblCodeArena = new JLabel("Code Arena");
		lblCodeArena.setBounds(351, 114, 114, 15);
		frame.getContentPane().add(lblCodeArena);
		
		JLabel lblMadeBy = new JLabel("Made by - Vivek Thakur");
		lblMadeBy.setBounds(396, 645, 193, 15);
		frame.getContentPane().add(lblMadeBy);
		
		JButton Qs_1 = new JButton("Ques_1");
		Qs_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ques_set.setText("print hii viku");
			}
		});
		Qs_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Qs_1.setBounds(32, 227, 117, 25);
		frame.getContentPane().add(Qs_1);
		
		JButton Qs_2 = new JButton("Ques_2");
		Qs_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ques_set.setText("print hii vivek");
			}
		});
		Qs_2.setBounds(32, 287, 117, 25);
		frame.getContentPane().add(Qs_2);
		
		JButton Qs_3 = new JButton("Ques_3");
		Qs_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				ques_set.setText("print hii poppy");
			}
		});
		Qs_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Qs_3.setBounds(32, 348, 117, 25);
		frame.getContentPane().add(Qs_3);
		
		ques_set = new JTextField();
		ques_set.setBounds(299, 45, 311, 38);
		frame.getContentPane().add(ques_set);
		ques_set.setColumns(10);
		
		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setBounds(417, 16, 86, 15);
		frame.getContentPane().add(lblQuestion);
		
	}
}
