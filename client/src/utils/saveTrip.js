export const MIME_TYPE = {
  JSON : "application/json",
  CSV : "text/csv",
  SVG : "image/svg+xml",
  KML : "application/vnd.google-earth.kml+xml"
};


export function buildTripJSON(){

  let tripResponse = require('../components/Trip/Itinerary/Itinerary')
  const tripJSON = {
      earthRadius: tripResponse.distanceResponse.earthRadius,
      units: "km",
      places: tripResponse.distanceResponse.places,
      distances: tripResponse.distanceResponse.distances 
};
  return JSON.stringify(tripJSON, null, 2);
}

export function buildTripCSV(jsonData){

  let data = typeof jsonData != 'object' ? JSON.parse(jsonData) : jsonData;
  const headers = '"earthRadius","units","distances","latitude","longitude","name"\n';
  let csv = "";

  csv += headers;
  for(let i = 0; i < data.places.length; i++){

    csv += data.earthRadius + ",";
    csv += '"' + data.units + '",';
    csv += data.distances[i] + ",";
    csv += '"' + data.places[i].latitude + '",';
    csv += '"' + data.places[i].longitude + '",';
    csv += '"' + data.places[i].name + '"\n';
  }

  return csv;
}

export function downloadFile(fileNameWithExtension, mimeType, fileText) {
  const file = new Blob([fileText], { type: mimeType });
  const link = document.createElement("a");
  const url = URL.createObjectURL(file);
  link.href = url;
  link.download = fileNameWithExtension;
  document.body.appendChild(link);
  link.click();
  setTimeout(function() {
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  }, 0);
}