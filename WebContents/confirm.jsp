<% Item item = (Item) request.getAttribute("item"); %>
<% String ccn = (String) request.getAttribute("ccn"); %>
<% String purchaseTime = (String) request.getAttribute("purchaseTime"); %>
<%@ page language="java" contentType="text/html" import="edu.ucla.cs.cs144.*, java.util.List, java.util.ArrayList, java.util.Iterator" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"charset>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="item.css" />

<title>Item Purchase Confirmation - <%=item.id%></title>
</head>

<body>
  <div class="item-search navbar navbar-default" role="navigation">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
    <form class="navbar-form navbar-left" role="search" name="item" action="item" method="get">
      <label>Find Item by ID</label>
      <div class="form-group">
        <input type="text" name="id" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
  </div>
  </div>

  <ul class="item-breadcrumbs breadcrumb">
    <li><a href="keywordSearch.html">Auction Search</a></li>
    <li><a href="item?id=<%=item.id%>">Item #<%=item.id%></a></li>
  </ul>

  <div class="item-details">

    <div class="item-header">
      <h2><%=item.name%></h2>
        <ul class="categories">
          |
          <% for(String category : item.category) { %>
          <li> <small><%=category%> | </small></li>
          <% } %>
        </ul>
        <span class="seller-details">
          by: <%=item.seller.id%> (<span class="seller-rating"><%=item.seller.rating%></span>)
          from: <%=item.location%>, <%=item.country%>
        </span>
    </div>

    <ul class="item-current">
      <li><strong>First Bid: </strong><%=item.firstBid%></li>
      <li><strong>Current Bid: </strong></strong><%=item.currently%></li>
      <li><strong>Started: </strong><%=item.started%></li>
      <li><strong>Ends:</strong> <%=item.ends%></li>
      <% if (item.buyPrice != null) { %>
        <li><strong>Buy Price: </strong><%=item.buyPrice%></li>
        <li>Purchased with CC: <%=ccn%></li>
        <li>Purchased at Time: <%=purchaseTime%></li>
      <% } %>
    </ul>
    <!-- 1047388061 -->

    <div class="item-description">
      <h4>Description</h4>
      <p> <%=item.description%> </p>
    </div>

    <div class="item-bids">
      <h4>Bids (<%=item.bids.bid.size()%>)</h4>
      <table class="table">
        <tbody>
        <tr>
          <th>Bidder</th>
          <th>Time</th>
          <th>Amount</th>
        </tr>
        <% for(Item.Bid bid : item.bids.bid) { %>
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

  <div id="map_canvas">Location for map cannot be found</div>
</body>
</html>
