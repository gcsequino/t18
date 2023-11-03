import React, {useState} from "react";
import { Button, Table } from "reactstrap";
import { latLngToText, placeToLatLng } from '../../../utils/transformers';

export default function DisplayResults(props) {
    return (
        <>
            <Table>
            <tbody>
                {props.results.map((place, index) => 
                    <ResultRow 
                        key={`table-${JSON.stringify(place)}-${index}`}
                        place={place}
                        index={index}
                        placeActions={props.placeActions}
                    />
                )}
            </tbody>
            </Table>
            <Button variant="secondary" onClick={() => { props.setSearchResults(null); }} block>
                close
            </Button>
        </>
    )
}

function ResultRow(props) {
    const name = props.place.name ? props.place.name : "-";
    const location = latLngToText(placeToLatLng(props.place));

    return (
        <tr>
            <td scope="row"><b>{props.index + 1}</b></td>
            <td>
                {name}
                <br/>
                <small className="text-muted">{location}</small>
            </td>
            <td>
            <Button
                onClick={() => { props.placeActions.append(props.place); }}>
                +
            </Button>
            </td>
        </tr>
    );
}