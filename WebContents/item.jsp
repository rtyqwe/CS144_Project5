<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html" import="edu.ucla.cs.cs144.*, java.util.List, java.util.ArrayList, java.util.Iterator" %>
<meta charset="utf-8"charset>
<title>Item Results</title>
</head>
<body>
<%
Item item = (Item) request.getAttribute("item");
%>

<div class="item-search">
  <form name="item" action="item" method="get">
    <label>Search for Item by ID: </label>
    <input type="text" name="id">
    <br>
    <input type="submit" value="Submit">
  </form>
</div>

<br>

<div class="item-nav">
  <a href="keywordSearch.html">Auction Search</a> <span>&lt;</span>
  <a href="item?id=<%=item.id%>">Item #<%=item.id%></a>
</div>

<hr>

<div class="item-container">
  <div class="item-header">
    <h2>
      <%=item.name%>
    </h2>
    <h4>
      |
      <%
      for(String category : item.category) {
      %>
      <%=category + " | "%>
      <% 
      }
      %>
    </h4>
    <h4>
      by: 
      <%=item.seller.id%>
      (<%=item.seller.rating%>)
      from: <%=item.location%>, <%=item.country%>
    </h4>
  </div>
  <hr>
  <div class="item-bid-description">
    <h4>First Bid: <%=item.firstBid%></h4>
    <h4>Current Bid: <%=item.currently%></h4>
    <h4>Started: <%=item.started%></h4>
    <h4>Ends: <%=item.ends%></h4>
  </div>
  <hr>
  <div class="item-description">
    <h4>Description</h4>
    <p>
    <%=item.description%>
    </p>
  </div>
  <hr>
  <div class="item-bids">
    <h4>Bids (<%=item.bids.bid.size()%>)</h4>
    <table>
      <tbody>
      <tr>
        <th>Bidder</th>
        <th>Time</th>
        <th>Amount</th>
      </tr>
      <% for(Item.Bid bid : item.bids.bid) {
      %>
      <tr>
        <th><%=bid.bidder.id%> (<%=bid.bidder.rating%>) </th>
        <th><%=bid.time%></th>
        <th><%=bid.amount%></th>
      </tr>
      <% } %>
      </tbody>
    </table>


  </div>
</div>

</body>
</html>
