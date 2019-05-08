package view;

import model.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class firstWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					firstWindow window = new firstWindow();
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
	public firstWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setBounds(174, 42, 86, 20);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 120, 434, 141);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.setBounds(178, 73, 77, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int counter = 0;
				String finalResult;
				int size = Integer.parseInt(textField.getText());
				ArrayBag solutions = NQueensDemonstration.findSolution(size);
				int length = solutions.size();
				Location [] answer = solutions.getData();
		        if(length/size >= 1) {
		        	finalResult = "I find " + length/size + " different way(s).";
			        for(int i = 0; i < length; i++) {
			            if(counter % size == 0) 
			            	finalResult += "\nWay "+(counter/size + 1)+":\n";
			            finalResult += "("+ answer[i].getX()+", "+ answer[i].getY()+")";
			            counter++;
					}
		        }
		        else finalResult = "Sorry, I can't find a way!";
				textArea.setText(finalResult);
			}
		});
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblEnterTheSize = new JLabel("Enter the size of the chess board below:");
		lblEnterTheSize.setBounds(120, 11, 194, 25);
		frame.getContentPane().add(lblEnterTheSize);
		frame.getContentPane().add(textField);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(textArea);
		
		
		
		
	}
}
