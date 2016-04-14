package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;


public class ConverterGUI {

	private JFrame frmSecuenti;
	private JTextField textXmlFile;
	private static String xmlNewString;
	private static String xslNewString;
	private static String pdfNewString;
	private static String pdfNameNewString;
	private JTextField textXslFile;
	private JTextField textPdfFile;
	private JTextField textPdfFilename;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterGUI window = new ConverterGUI();
					window.frmSecuenti.setVisible(true);
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
		frmSecuenti = new JFrame();
		frmSecuenti.setTitle("SeCuenti");
		frmSecuenti.setBounds(100, 100, 450, 300);
		frmSecuenti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSecuenti.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose XML-file to convert to PDF");
		lblNewLabel.setBounds(10, 53, 254, 14);
		frmSecuenti.getContentPane().add(lblNewLabel);
		
		textXmlFile = new JTextField();
		textXmlFile.setBounds(10, 78, 414, 20);
		frmSecuenti.getContentPane().add(textXmlFile);
		textXmlFile.setColumns(10);
		
		// Buttom for browsing and choosing XML-file
		JButton btnBrowseXml = new JButton("Browse");
		btnBrowseXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser ();
				// fc.setCurrentDirectory(new java.io.File("."));
		        fc.setDialogTitle("Locate XML-file to Convert");
		        
		       //Adds file filter for easyer browsing
		        fc.setAcceptAllFileFilterUsed(false);
		        FileFilter filter = new FileNameExtensionFilter("XML File","xml");
		        fc.setFileFilter(filter);
				
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
		frmSecuenti.getContentPane().add(btnBrowseXml);
		
		
		//Button for running method that converts XML to PDF
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pdfNameNewString =textPdfFilename.getText();
				
					try {
						main.FOPPdfConverter.convertToPDF();
						//default title and icon, that informs user that PDF was generated
						JOptionPane.showMessageDialog(frmSecuenti,
						    pdfNameNewString + ".pdf, successfully generated");
					} catch (FOPException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			            JOptionPane.showMessageDialog(frmSecuenti,
			            		e, "Error",
			            	    JOptionPane.ERROR_MESSAGE);
			        } catch (IOException e) {
			            // TODO Auto-generated catch block	
			        	JOptionPane.showMessageDialog(frmSecuenti,
			        			e, "Error",
			        		    JOptionPane.ERROR_MESSAGE);
			            e.printStackTrace();
			        } catch (TransformerException e) {
			            // TODO Auto-generated catch block
			        	JOptionPane.showMessageDialog(frmSecuenti,
							    e, "Error",
							    JOptionPane.ERROR_MESSAGE);
			            e.printStackTrace();
			        }					
			}
		});
		btnConvert.setBounds(335, 227, 89, 23);
		frmSecuenti.getContentPane().add(btnConvert);
		
		
		//Button for browsing and selecting output location
		JButton btnOutput = new JButton("Set folder for PDF output");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser pdfFc = new JFileChooser ();
				
				// xslFc.setCurrentDirectory(new java.io.File("."));
				pdfFc.setDialogTitle("Locate folder for PDF-outputfile");
				pdfFc.setAcceptAllFileFilterUsed(false);
				pdfFc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				
				if (pdfFc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  // Put absolute filepath in textfield 
					textPdfFile.setText(pdfFc.getSelectedFile().getPath());
					  
					  // Replaces single / with double //, to match java filesearch patterns
					  String PdffilePath=textPdfFile.getText();
					  pdfNewString = PdffilePath.replace("\\", "\\\\");
			          
				}
				
			}
		});
		btnOutput.setBounds(216, 0, 218, 23);
		frmSecuenti.getContentPane().add(btnOutput);
		
		//Button for browsing and selecting Xsl-Files
		JButton btnBrowseXsl = new JButton("Choose format for PDF");
		btnBrowseXsl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser xslFc = new JFileChooser ();
				
				// xslFc.setCurrentDirectory(new java.io.File("."));
				xslFc.setAcceptAllFileFilterUsed(false);
				xslFc.setDialogTitle("Locate Xsl-file");
				// Create filter for easier browsing
				FileFilter filter = new FileNameExtensionFilter("XSL File","XSL");
				xslFc.setFileFilter(filter);
				
				if (xslFc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					  // Put absolute filepath in textfield 
					  textXslFile.setText(xslFc.getSelectedFile().getPath());
					  
					  // Replaces single / with double //, to match java filesearch patterns
					  String XslfilePath=textXslFile.getText();
					  xslNewString = XslfilePath.replace("\\", "\\\\");
			          
				}
			}
		});
		btnBrowseXsl.setBounds(0, 0, 218, 23);
		frmSecuenti.getContentPane().add(btnBrowseXsl);
		
		textXslFile = new JTextField();
		textXslFile.setBounds(131, 22, -20, 20);
		frmSecuenti.getContentPane().add(textXslFile);
		textXslFile.setColumns(10);
		
		textPdfFile = new JTextField();
		textPdfFile.setBounds(436, 22, -12, 20);
		frmSecuenti.getContentPane().add(textPdfFile);
		textPdfFile.setColumns(10);
		
		JLabel lblWriteNameOf = new JLabel("Choose the name for the generated PDF-Filename");
		lblWriteNameOf.setBounds(10, 133, 276, 14);
		frmSecuenti.getContentPane().add(lblWriteNameOf);
		
		textPdfFilename = new JTextField();
		textPdfFilename.setBounds(10, 158, 254, 20);
		frmSecuenti.getContentPane().add(textPdfFilename);
		textPdfFilename.setColumns(10);
		
		
	}
	
	
	// Return methods to pass variable data to FOPPdfConverter
	public static String getXmlFilepath() { 
  	  	return xmlNewString;   
    }
	
	public static String getXslFilepath() { 
	  	return xslNewString;   
	}
	
	public static String getPdfFolderpath() { 
	  	return pdfNewString;   
	}
	
	public static String getPdfFilename() { 
	  	return pdfNameNewString;   
	}
}
