import React, { useState, useEffect } from 'react';
import { Button, Alert, Modal, ModalBody, ModalHeader, ModalFooter, Table, Input, InputGroup } from 'reactstrap';
import { ItineraryActionsDropdown, PlaceActionsDropdown, returnPlacesObject } from './actions.js';
import { latLngToText } from '../../../utils/transformers';
import { FaRulerHorizontal, FaPencilAlt } from "react-icons/fa";
import { ImSigma } from "react-icons/im";
import { sendAPIRequest, getOriginalServerUrl } from '../../../utils/restfulAPI';

export default function Itinerary(props) {
    const [distances, setDistances] = useState([0]);

    const [modal, setModal] = useState(false);
    const toggleModal = () => setModal(!modal);

    const [tripName, setTripName] = useState("My Trip");

    return (
        <>
        <TotalDistance distances={distances}/>
        <Table responsive striped>
            
            <Header places={props.places} placeActions={props.placeActions} toggleModal={toggleModal} tripName={tripName}/>
            <Body places={props.places} placeActions={props.placeActions} distances={distances} setDistances={setDistances}/>
            
        </Table>
        <EditTripName modal={modal} toggleModal={toggleModal} setTripName={setTripName}/>
        </>
    );
}

function TotalDistance(props) {
        return(
        <Alert>
            Total Trip Distance: {calcTotalDistance(props.distances, props.distances?.length)}
        </Alert>);
        
}

function Header(props) {

    return (
        <thead>
            <tr>
                <th/>
                <th> {props.tripName} <FaPencilAlt onClick={() => props.toggleModal()}/> </th>
                <th>
                    <FaRulerHorizontal />
                </th>
                <th>
                    <ImSigma />
                </th>
                <th>
                    <ItineraryActionsDropdown placeActions={props.placeActions} places={props.places} tripName={props.tripName}/>
                </th>
            </tr>
        </thead>
    );
}

function EditTripName(props){
    return (
        <Modal isOpen={props.modal}>
            <ModalHeader> Change Name </ModalHeader>
            <ModalBody>
                <Input type="text" name="text" id="exampleText" 
                    onChange={(event) => props.setTripName(event.target.value)}
                />
            </ModalBody>
            <ModalFooter>
                <Button onClick={() => props.toggleModal()}>Close</Button>
            </ModalFooter>
        </Modal>
    );
}


function Body(props) {
    
    useEffect(() => {sendDistanceRequest(props.places).then(props.setDistances);}, [props.places]);
    return (
        <tbody>
            {props.places.map((place, index) => 
                <TableRow 
                    key={`table-${JSON.stringify(place)}-${index}`}
                    place={place}
                    placeActions={props.placeActions}
                    index={index}
                    distance={(index-1) < 0 ? 0 : props.distances[index-1]}
                    totDistance={calcTotalDistance(props.distances, index)}
                />
            )}
        </tbody>
    );
}

function calcTotalDistance(distances, index) {
    let sum = 0;
    for(let i = 0; i < index; i++) {
        sum += distances[i];
    }
    return sum;
}

async function sendDistanceRequest(places) {
    places = places.map(place => {
        return returnPlacesObject(place)
    })
    const initializeQuery = { "requestType" : "distances",
                            "places" : places,
                            "earthRadius" : 6371.0 };
    const distanceResponse = await sendAPIRequest(initializeQuery, getOriginalServerUrl());
    exports.distanceResponse = distanceResponse;
    return distanceResponse?.distances;
}

function TableRow(props) {
    const name = props.place.name ? props.place.name : "-";
    const location = latLngToText(props.place);

    return (
        <tr>
            <th scope="row">{props.index + 1}</th>
            <td>
                {name}
                <br/>
                <small className="text-muted">{location}</small>
            </td>
            <td>
                {isNaN(props.distance) ? "-" : props.distance}
            </td>
            <td>
                {isNaN(props.totDistance) ? "-" : props.totDistance}
            </td>
            <td>
                <PlaceActionsDropdown placeActions={props.placeActions} index={props.index} />
            </td>
        </tr>
    );
}
