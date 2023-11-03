import React, { useState } from 'react';
import { sendAPIRequest, getOriginalServerUrl } from '../../../utils/restfulAPI';
import DisplayResults from './DisplayResults';
import { InputGroup, Input, Button } from 'reactstrap';


export default function SearchBar(props) {
    const [searchResults, setSearchResults] = useState(null);
    const [inputString, setInputString] = useState(null);
    return (
      <div>
          <InputGroup>
            <Input
                placeholder="FIND"
                onChange={(e) => setInputString(e.target.value)}
            />
            <SendFindRequest setSearchResults={setSearchResults} inputString={inputString}/>
          </InputGroup>
          
          {searchResults ? <DisplayResults results={searchResults} setSearchResults={setSearchResults} placeActions={props.placeActions}/> : null}
      </div>
    );
}

function SendFindRequest(props) {
    return (
      <>
        <Button
            color="primary"
            onClick={() => { buildJsonFile(props.inputString, props.setSearchResults); }}>
            search
        </Button>
      </>
    )
}

async function buildJsonFile(stringtoMatch, setSearchResults){
    if (stringtoMatch == null) return;
    var initializeQuery = { "requestType" : "find",
                            "match" : stringtoMatch,
                            "limit" : 5 };
    const findResponse = await sendAPIRequest(initializeQuery, getOriginalServerUrl());
    setSearchResults(findResponse.places);
}

