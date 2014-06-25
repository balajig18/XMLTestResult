package com.balaji.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class XMLReaderWriter {
	
	private static final String XSLT_PATH="D:\\Result.xsl";
	private static final String format1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	private static String ResultXMLPath;
	public static void CreateXML()
	{
		try{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		
		Element rootElement = doc.createElement("Result");
		doc.appendChild(rootElement);
		Attr attr=doc.createAttribute("name");
		attr.setValue("Result_"+System.currentTimeMillis()); 
        rootElement.setAttributeNode(attr);
		// staff elements
	/*	Element Test = doc.createElement("Test");
		rootElement.appendChild(Test);
 
		// set attribute to staff element
		Attr attr = doc.createAttribute("id");
		attr.setValue("1");
		Test.setAttributeNode(attr);
 
		// shorten way
		// staff.setAttribute("id", "1");
 
		// firstname elements
		Element firstname = doc.createElement("Step");
		firstname.appendChild(doc.createTextNode("yong"));
		Test.appendChild(firstname);
 
		// lastname elements
		Element lastname = doc.createElement("lastname");
		lastname.appendChild(doc.createTextNode("mook kim"));
		Test.appendChild(lastname);
 
		// nickname elements
		Element nickname = doc.createElement("nickname");
		nickname.appendChild(doc.createTextNode("mkyong"));
		Test.appendChild(nickname);
 
		// salary elements
		Element salary = doc.createElement("salary");
		salary.appendChild(doc.createTextNode("100000"));
		Test.appendChild(salary);
		*/
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Source xsl = new StreamSource(XSLT_PATH);
		
		Transformer transformer = transformerFactory.newTransformer();
		//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("D:\\Result.html"));
		
        
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } 
		catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
	public static void createXML2(String path) throws FileNotFoundException, UnsupportedEncodingException
	{
		ResultXMLPath = path;
		PrintWriter print=new PrintWriter(ResultXMLPath,"UTF-8");
			 
			// if file doesn't exists, then create it
		print.println("<?xml version="+"'1.0'"+" encoding="+"'UTF-8'"+" standalone="+"'no'"+"?>");
		print.println("<?xml-stylesheet type="+"'text/xsl'"+" href="+"'D:\\Result.xsl'"+"?>");
		print.println("<Result\t"+"name="+"'Result "+format1+"'"+"\tDate='"+format1+"'></Result>");
		print.flush();
		print.close();
		
 
			// get the content in bytes
		//	byte[] contentInBytes = content.getBytes();
 
			
 
	}
	public static void addNode(String Test_Name,String Description,String Status,String Path_Screenshot) 
	{
		try{
			 
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        Document document = documentBuilder.parse(ResultXMLPath);
	        Element root = document.getDocumentElement();
			
	        Element Test=document.createElement("Test");
	        root.appendChild(Test);
	        Element Name =document.createElement("Test_Name");
	        Name.appendChild(document.createTextNode(Test_Name));
	        Test.appendChild(Name);

	        Element Desc =document.createElement("Description");
	        Desc.appendChild(document.createTextNode(Description));
	        Test.appendChild(Desc);

	        Element status1 =document.createElement("Status");
	        status1.appendChild(document.createTextNode(Status));
	        Test.appendChild(status1);

	        Element Path =document.createElement("Screenshot");
	        Path.appendChild(document.createTextNode(Path_Screenshot));
	        Test.appendChild(Path);
	        
	        Transform(document,ResultXMLPath);
	        
		         
		        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void Transform(Document doc,String filepath) throws IOException
	{
		try{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
		}
		 catch (TransformerException tfe) {
				tfe.printStackTrace();
			   } 
	}
	
	public static void addAttribute(String parent_node,String node,String attr,String attr_value)
	{
		 try{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(ResultXMLPath);
        Element root=document.getDocumentElement();
        NodeList list= document.getElementsByTagName(node);
        Element node1=(Element) list.item(list.getLength()-1);
        node1.setAttribute(attr, attr_value);
        Transform(document,ResultXMLPath);
       
		 }
		 catch(Exception e)
			{
				e.printStackTrace();
			}
		 
    
	}
	
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException
	{
		XMLReaderWriter.createXML2("D://file1.xml");
		XMLReaderWriter.addNode("Test1","Login into myhcl", "Fail", "d://");
		XMLReaderWriter.addAttribute("root","Status", "Exception","rae");
			
		XMLReaderWriter.addNode("Test2","Home Page", "Pass", "c://");
		XMLReaderWriter.addAttribute("root","Result", "Exception","rae");
	}

}
