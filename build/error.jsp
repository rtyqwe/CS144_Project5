<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"charset>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="item.css" />

<title>Item Search - Error</title>
</head>

<body>

  <div class="item-search navbar navbar-default" role="navigation">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
    <form class="navbar-form navbar-left" role="search" name="item" action="item" method="get">
      <label>Find Item by ID</label>
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
  </div>
  </div>

  <ul class="item-breadcrumbs breadcrumb">
    <li><a href="keywordSearch.html">Auction Search</a></li>
    <li><a href="#">Item #</a></li>
  </ul>

  <div class="item-details">
    <h2>There has been an error getting id.  Please try again.</h2>
  </div>

  <div id="map_canvas">Location for map cannot be found</div>
</body>
</html>
