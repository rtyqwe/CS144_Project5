package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.bind.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	PrintWriter out = response.getWriter();
    	String itemId = request.getParameter("itemId");
    	response.setContentType("text/html");
    	out.println(itemId);
    	String itemXmlResponse = AuctionSearchClient.getXMLDataForItemId(itemId);
    	Document doc = null;
    	try {
    		JAXBContext jc = JAXBContext.newInstance(ItemXMLSchema.class);
    		Unmarshaller unmarshaller = jc.createUnmarshaller();
    		InputStream input = new ByteArrayInputStream(itemXmlResponse.getBytes("UTF-8"));
    		
    		ItemXMLSchema item = (ItemXMLSchema) unmarshaller.unmarshal(input);
    		out.println(item.getName());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    	//out.println(itemXmlResponse);
    	
    	/*
    	 * <Item ItemID="1496968526">
  <Name>Star Trek Next Generation</Name>
  <Category>Movies &amp; Television</Category>
  <Category>Sci-Fi</Category>
  <Category>VHS</Category>
  <Category>Video, Film</Category>

  <Currently>$40.00</Currently>
  <First_Bid>$40.00</First_Bid>
  <Number_of_Bids>0</Number_of_Bids>
  <Bids/>
  <Location>Lynchburg, Virginia</Location>
  <Country>USA</Country>

  <Started>Dec-14-01 17:12:01</Started>
  <Ends>Dec-24-01 17:12:01</Ends>
  <Seller UserID="drshodan7@aol.com" Rating="4"/>
  <Description>all episodes of Star Trek Next Generation on VHS videotapes Payment Details See item description and Payment Instructions, or contact seller for more information. Payment Instructions buyer pays by personal check or money order</Description>
</Item>

    	 */
    }
}
