import React, { useState } from 'react';
import { Input, Modal, ModalHeader, ModalBody, ModalFooter, ButtonGroup, Button, DropdownItem, DropdownMenu, DropdownToggle, UncontrolledDropdown } from 'reactstrap';
import { BiDotsVerticalRounded } from 'react-icons/bi';
import { FaHome, FaTrash, FaTrashAlt, FaUpload, FaSave, FaDownload, FaHourglassHalf, FaHighlighter } from 'react-icons/fa';
import { buildTripJSON, buildTripCSV, downloadFile, MIME_TYPE} from '../../../utils/saveTrip'
import { handleFileUpload } from '../../../utils/uploadTrip';
import { getOriginalServerUrl, sendAPIRequest } from "../../../utils/restfulAPI";
import { latLngToPlace } from "../../../utils/transformers";

export function returnPlacesObject(place) {
    return { name: place.name,
            latitude: place.lat.toString(),
            longitude: place.lng.toString() }

}
export function placeBuilder(place) {
    return { name: place.name,
        lat: Number(place.latitude),
        lng: Number(place.longitude) }
}

async function createTour(props, currentPlaces){
    const initializeQuery = { "requestType" : "tour",
                      "earthRadius" : 3959.0,
                      "response" : 1.0,
                      "places" : currentPlaces.map(place => {
                        return returnPlacesObject(place);
                      })
    }
    const tourResponse = await sendAPIRequest(initializeQuery, getOriginalServerUrl());
    optimizeItinerary(props, tourResponse?.places);
}

function optimizeItinerary(props, newPlaces) {
    props.placeActions.removeAll();
    props.placeActions.appendPlaces(newPlaces.map(place => {
        return placeBuilder(place);
    }));
}

function UploadModal(props) {
    return (
        <>
            <Modal isOpen={props.modal} toggle={props.toggle}>
                <ModalHeader toggle={props.toggle}>Trip Upload</ModalHeader>
                <ModalBody>
                    <Input 
                        type="file" 
                        accept=".json, application/json, .csv, text/csv" 
                        onChange={(event) => handleFileUpload(props, event)} 
                    />
                </ModalBody>
                <ModalFooter>
                    <Button onClick={props.toggle}>Close</Button>
                </ModalFooter>
            </Modal>
        </>
    )
}

function DownloadModal(props) {

    function handleFileDownload(event) {
        const tripName = props.tripName;
        const jsonFile = buildTripJSON();
        const csvFile = buildTripCSV(jsonFile);
        downloadFile(tripName + ".json", MIME_TYPE.JSON, jsonFile);
        downloadFile(tripName + ".csv", MIME_TYPE.CSV, csvFile);
    }

    return (
        <>
            <Modal isOpen={props.modal} toggle={props.toggle}>
                <ModalHeader toggle={props.toggle}>Trip Download</ModalHeader>
                <ModalBody>
                    <Button onClick={handleFileDownload}> <FaDownload /> Download Your Trip</Button>
                </ModalBody>
                <ModalFooter>
                    <Button onClick={props.toggle}>Close</Button>
                </ModalFooter>
            </Modal>
        </>
    )
}

export function ItineraryActionsDropdown(props) {
    const [UPmodal, setUPModal] = useState(false);
    const [DOWNmodal, setDOWNModal] = useState(false);
    const toggleUP = () => setUPModal(!UPmodal);
    const toggleDOWN = () => setDOWNModal(!DOWNmodal);
    return (
        <ActionsDropdown {...props}>
            <DropdownItem onClick={() => props.placeActions.getUserLocation()} data-testid='home-button'>
                <FaHome />
            </DropdownItem>
            <DropdownItem onClick={() => props.placeActions.removeAll()} data-testid='delete-all-button'>
                <FaTrashAlt />
            </DropdownItem>
            <DropdownItem onClick={toggleUP}>
                <UploadModal modal={UPmodal} toggle={toggleUP} placeActions={props.placeActions}/>
                <FaUpload />
            </DropdownItem>
            <DropdownItem onClick={toggleDOWN}>
                <DownloadModal modal={DOWNmodal} toggle={toggleDOWN} tripName={props.tripName}/>
                <FaSave />
            </DropdownItem>
            <DropdownItem onClick={() => createTour(props, props.places)}>
                <FaHourglassHalf />
            </DropdownItem>
        </ActionsDropdown>
    );
}

export function PlaceActionsDropdown(props) {
    return (
        <ActionsDropdown {...props}>
            <DropdownItem onClick={() => props.placeActions.removeAtIndex(props.index)} data-testid={`delete-button-${props.index}`}>
                <FaTrash />
            </DropdownItem>
            <DropdownItem onClick={() => props.placeActions.selectIndex(props.index)} data-testid={`highlight-button-${props.index}`}>
                <FaHighlighter />
            </DropdownItem>
        </ActionsDropdown>
    );
}

function ActionsDropdown(props) {
    return (
        <UncontrolledDropdown direction="left">
            <DropdownToggle tag="div" data-testid={`row-toggle-${props.index}`}>
                <BiDotsVerticalRounded size="1.5em" />
            </DropdownToggle>
            <DropdownMenu>
                <ButtonGroup>
                    {props.children}
                </ButtonGroup>
            </DropdownMenu>
        </UncontrolledDropdown>
    );
}
