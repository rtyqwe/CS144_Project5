package edu.ucla.cs.cs144;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Item")
public class Item {
	@XmlAttribute(name="ItemID")
	public String id;
	
	@XmlElement(name="Name")
	public String name;
	
	@XmlElement(name="Category")
	public List<String> category = new ArrayList<String>();
	
	@XmlElement(name="Currently")
	public String currently;
	
	@XmlElement(name="First_Bid")
	public String firstBid;
	
	@XmlElement(name="Number_of_Bids")
	public String numberOfBids;
	
	@XmlElement(name="Bids")
	public Bids bids;
	
	@XmlElement(name="Location")
	public String location;
	
	@XmlElement(name="Country")
	public String country;
	
	@XmlElement(name="Started")
	public String started;
	
	@XmlElement(name="Ends")
	public String ends;
	
	@XmlElement(name="Seller")
	public Seller seller;
	
	@XmlElement(name="Description")
	public String description;
	
	public static class Bids {
		@XmlElement(name="Bid")
		public List<Bid> bid = new ArrayList<Bid>();
	}
	
	public static class Bid {
		@XmlElement(name="Bidder")
		public Bidder bidder;
		
		@XmlElement(name="Time")
		public String time;
		
		@XmlElement(name="Amount")
		public String amount;
	}
	
	public static class Bidder {
		@XmlAttribute(name="UserID")
		public String id;
		
		@XmlAttribute(name="Rating")
		public String rating;

		@XmlElement(name="Location")
		public String location;
		
		@XmlElement(name="Country")
		public String country;
	}
	
	public static class Seller {
		@XmlAttribute(name="UserID")
		public String id;
		
		@XmlAttribute(name="Rating")
		public String rating;
	}
}
