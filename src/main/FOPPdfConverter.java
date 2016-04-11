package main;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;


public class FOPPdfConverter {

	static String XmlPath = ConverterGUI.getXmlFilepath();
	static String XslPath = ConverterGUI.getXslFilepath();
	static String PdfFolder = ConverterGUI.getPdfFolderpath();
	static String Pdffilename = ConverterGUI.getPdfFilename();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        
    } 
	
	
	/**
     * Method that will convert the given XML to PDF 
     * @throws IOException
     * @throws FOPException
     * @throws TransformerException
     */
    public static void convertToPDF()  throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File(XslPath);
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File(XmlPath));
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        OutputStream out;
        out = new java.io.FileOutputStream(PdfFolder + Pdffilename + ".pdf");

        
    
        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

    
    /**
     * This method will convert the given XML to XSL-FO
     * @throws IOException
     * @throws FOPException
     * @throws TransformerException
     */
    public void convertToFO()  throws IOException, FOPException, TransformerException {
        // the XSL FO file
        File xsltFile = new File("C:\\Users\\Christoffer\\workspace\\SeCuenti_1.0\\Resources\\test.xsl");
        
        
        /*TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource("C:\\Temp\\template.xsl"));*/
        
        // the XML file which provides the input
        StreamSource xmlSource = new StreamSource(new File("C:\\Users\\Christoffer\\workspace\\SeCuenti_1.0\\Resources\\testfile.xml"));
        
        // a user agent is needed for transformation
        /*FOUserAgent foUserAgent = fopFactory.newFOUserAgent();*/
        // Setup output
        OutputStream out;
        
        out = new java.io.FileOutputStream("C:\\Users\\Christoffer\\workspace\\SeCuenti_1.0\\Resources\\temp.fo");
        
        
        
        try {
            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            //Result res = new SAXResult(fop.getDefaultHandler());

            Result res = new StreamResult(out);

            //Start XSLT transformation and FOP processing
            transformer.transform(xmlSource, res);


            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then 
            // PDF is created
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

}
