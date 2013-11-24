var geocoder;
var map;

function initMap(loc, country)
{
  geocoder = new google.maps.Geocoder();

  var mapOptions = { 
    zoom: 14,
    mapTypeId: google.maps.MapTypeId.ROADMAP 
  }; 

  var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions); 

  geocoder.geocode( {'address': loc}, function(results, status) {
    if (status == google.maps.GeocoderStatus.OK) {
      map.setCenter(results[0].geometry.location);
      var marker = new google.maps.Marker({
        map: map,
        position: results[0].geometry.location
      });
    } else {
      geocoder.geocode( { 'address': country}, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
          map.setCenter(results[0].geometry.location);
          var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
          });
        } else {
          // display error
        }
      });
    }});
} 
