package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ConverterGUI {

	private JFrame frame;
	private JTextField textXmlFile;
	private static String xmlNewString;
	private static String xslNewString;
	private JTextField textXslFile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterGUI window = new ConverterGUI();
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
	public ConverterGUI() {
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
		
		JLabel lblNewLabel = new JLabel("Choose XML-file to convert to PDF");
		lblNewLabel.setBounds(10, 53, 254, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textXmlFile = new JTextField();
		textXmlFile.setBounds(10, 78, 414, 20);
		frame.getContentPane().add(textXmlFile);
		textXmlFile.setColumns(10);
		
		JButton btnBrowseXml = new JButton("Browse");
		btnBrowseXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser ();
				// fc.setCurrentDirectory(new java.io.File("."));
		        fc.setDialogTitle("Locate XML-file to Convert");
		        fc.setAcceptAllFileFilterUsed(false);
				
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  // Put absolute filepath in textfield 
					  textXmlFile.setText(fc.getSelectedFile().getPath());
					  
					  // Replaces single / with double //, to match java filesearch patterns
					  String filePath=textXmlFile.getText();
					  xmlNewString = filePath.replace("\\", "\\\\");
   
			          
				}
				
			}
			
		});
		btnBrowseXml.setBounds(335, 110, 89, 23);
		frame.getContentPane().add(btnBrowseXml);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FOPPdfConverter.convertToPDF();
				} catch (FOPException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (TransformerException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
				
			}
		});
		btnConvert.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnConvert);
		
		JButton btnOutput = new JButton("Set folder for PDF output");
		btnOutput.setBounds(265, 0, 169, 23);
		frame.getContentPane().add(btnOutput);
		
		JButton btnBrowseXsl = new JButton("Choose format for PDF");
		btnBrowseXsl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser xslFc = new JFileChooser ();
				// fc.setCurrentDirectory(new java.io.File("."));
				xslFc.setAcceptAllFileFilterUsed(false);
				
				if (xslFc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  // Put absolute filepath in textfield 
					textXslFile.setText(xslFc.getSelectedFile().getPath());
					  
					  // Replaces single / with double //, to match java filesearch patterns
					  String XslfilePath=textXslFile.getText();
					  xslNewString = XslfilePath.replace("\\", "\\\\");
			          
				}
			}
		});
		btnBrowseXsl.setBounds(121, 0, 143, 23);
		frame.getContentPane().add(btnBrowseXsl);
		
		textXslFile = new JTextField();
		textXslFile.setBounds(131, 22, -20, 20);
		frame.getContentPane().add(textXslFile);
		textXslFile.setColumns(10);
	}
	
	public static String getXmlFilepath() { 
  	  	return xmlNewString;   
    }
	
	public static String getXslFilepath() { 
	  	return xslNewString;   
	}
}
