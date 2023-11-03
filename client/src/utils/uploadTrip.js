import { isJsonResponseValid } from '../utils/restfulAPI';
import * as TripFile from '../../schemas/TripFile.json';
import { placeBuilder } from '../components/Trip/Itinerary/actions';

function csvToArr(csv) {
    let arr = csv.split("\n");
    let places = [];
    for (const e of arr) {
        places.push(e.split(","));
    }
    return places;
}

function csvToJson(file) {
    let places = csvToArr(file.text);
    let json = { "places": [] };
    let nameIndex = places[0].indexOf("\"name\"");
    let latIndex = places[0].indexOf("\"latitude\"");
    let lngIndex = places [0].indexOf("\"longitude\"");
    for (const place of places) {
        if(places.indexOf(place) == 0) continue;
        let element = {
            //Doing calculation in other method, removes duplicate code too
            "name": checkValues(place, nameIndex),
            "latitude": checkValues(place, latIndex),
            "longitude": checkValues(place, lngIndex)
        }
        if (element.name && element.latitude && element.longitude) {
            json.places.push(element);
        }
    }
    return json;
}

function checkValues(place, index){
    if(place[index] == null){
        return null
    }
    return place[index].substring(1, place[index].length - 1);
    //return (place[index] ? place[index].substring(1, place[index].length - 1) : null);
}

function parseFile(file) {
    const extension = file.name.split('.').pop();
    let file_json = file;
    if (extension === "csv") {
        console.log("Building trip from CSV file.");
        file_json = csvToJson(file);
        console.log(file_json);
    }
    else if (extension === "json") {
        console.log("Building trip from JSON file.");
        file_json = JSON.parse(file_json.text);
    }

    if(!isJsonResponseValid(TripFile, file_json)) {
        console.error("Invalid file format");
        return;
    }
    return file_json;
}

async function readFile(fileName, fileObject) {
    return new Promise(resolve => {
        const reader = new FileReader();
        reader.readAsText(fileObject, "UTF-8");
        reader.onload = event => {
            const file = { name: fileName, text: event.target.result };
            resolve(parseFile(file));
        };
    })
}

export async function handleFileUpload(props, event) {
    const fileName = event.target.files[0].name;
    const fileObject = event.target.files[0];
    let file_json = await readFile(fileName, fileObject);
    props.placeActions.appendPlaces(file_json.places.map(place => {
        return placeBuilder(place);
    }));
}