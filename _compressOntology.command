#!/bin/bash

cd "$(dirname "$0")"

tar -czf OntoMedirad-X.Y.Z.tar.gz OntoMedirad/bfo.owl OntoMedirad/CHEBI_for_OntoMEDIRAD.owl OntoMedirad/FMA_for_OntoMEDIRAD.owl OntoMedirad/IAO_for_OntoSPM.owl OntoMedirad/MEDIRADClinicalResearchStudies.owl OntoMedirad/ontoMedirad.owl OntoMedirad/PATO_for_OntoMEDIRAD.owl OntoMedirad/Radionuclides_for_OntoMEDIRAD.owl OntoMedirad/radiopharmaceuticals.owl OntoMedirad/skos.rdf OntoMedirad/UO_for_OntoMEDIRAD.owl 
