import React, {useState} from 'react';
import { Button, Col, Container, Input, Modal, ModalBody, ModalFooter, ModalHeader, Row } from 'reactstrap';
import { useServerInputValidation } from '../../hooks/useServerInputValidation';

export default function ServerSettings(props) {
    const [serverInput, setServerInput, config, validServer, resetModal]
        = useServerInputValidation(props.serverSettings.serverUrl, props.toggleOpen);

    return (
        <Modal isOpen={props.isOpen} toggle={props.toggleOpen}>
            <Header toggleOpen={props.toggleOpen} />
            <Body
                serverInput={serverInput}
                setServerInput={setServerInput}
                serverSettings={props.serverSettings}
                serverName={getCurrentServerName(config, props.serverSettings)}
                requestTypes={getCurrentFeatures(config, props.serverSettings)}
                validServer={validServer}
            />
            <Footer
                config={config}
                serverInput={serverInput}
                validServer={validServer}
                resetModal={resetModal}
                processServerConfigSuccess={props.processServerConfigSuccess}
            />
        </Modal>
    );
}

function getCurrentServerName(config, serverSettings) {
    if (config) {
        return config.serverName;
    }
    else if (serverSettings.serverConfig) {
        return serverSettings.serverConfig.serverName;
    }
    return "t18 Break Main";
}

function getCurrentFeatures(config, serverSettings){
    function listRequests(type, requestTypes=""){
        requestTypes += type.features;
        return requestTypes
    }
    if (config){
        return listRequests(config);
    }
    else if (serverSettings.serverConfig){
        return listRequests(serverSettings.serverConfig)
    }
    return "config,find,distances,tour";
}

function getFeatureInput(userInput, listOfFeatures, setValidFeature){

    let featureArray = listOfFeatures.split(",")

    if(featureArray.indexOf(userInput) > -1){
        setValidFeature(true)
    }
    else{
        setValidFeature(false)
    }
}

function Header(props) {
    return (
        <ModalHeader className="ml-2" toggle={props.toggleOpen}>
            Server Connection
        </ModalHeader>
    );
}

function Body(props) {
    const urlInput =
        <Input
            value={props.serverInput}
            placeholder={props.serverSettings.serverUrl}
            onChange={(e) => { props.setServerInput(e.target.value) }}
            valid={props.validServer}
            invalid={!props.validServer}
        />;

    const [validFeature, setValidFeature] = useState(false)
    const verifyFeatures =
        <Input
            placeholder={"Enter Feature:"}
            onChange={(e) => {getFeatureInput(e.target.value, props.requestTypes, setValidFeature)}}
            valid={validFeature}
            invalid={!validFeature}
        />;

    return (
        <ModalBody>
            <Container>
                <SettingsRow label="Name" value={props.serverName} />
                <SettingsRow label="URL" value={urlInput} />
                <SettingsRow label="Features" value={props.requestTypes} />
                <SettingsRow label="Verify Feature" value={verifyFeatures} />
            </Container>
        </ModalBody>
    );
}

function SettingsRow({label, value}) {
    return (
        <Row className="my-2 vertical-center">
            <Col xs={3}>
                {label}:
            </Col>
            <Col xs={9}>
                {value}
            </Col>
        </Row>
    );
}

function Footer(props) {
    return (
        <ModalFooter>
            <Button color="secondary" onClick={props.resetModal}>Cancel</Button>
            <Button color="primary" onClick={() => {
                props.processServerConfigSuccess(props.config, props.serverInput);
                props.resetModal(props.serverInput);
            }}
                disabled={!props.validServer}
            >
                Save
            </Button>
        </ModalFooter>
    );
}
