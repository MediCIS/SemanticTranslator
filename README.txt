README Semantic Translator

This software was developed to manage the Semantic database of the Image and Radiation Dose BioBank (IRDBB) system of the MEDIRAD project. This semantic database is represented in an RDF graph stored in the Stardog Triple store.

The initial goal of the Semantic Translator was to populate the semantic database. The reason why it was called Semantic Translator is that this data is produced by translating metadata related to the files that are imported into the IRDBB system. 
It turned out that additional capabilities related to the management of the semantic database were needed. So, the scope of this software was extended to cover them as well, but the name Semantic Translator was not changed.

So, the main part of the Semantic Translator is dedicated to populating the Semantic database. This is achieved when data are imported in the IRDBB system. Two mechanisms exist:
- for DICOM data (i.e. CT, NM, PET images and SR documents), the RDF data are produced by translating into RDF the essential DICOM metadata
- for non-DICOM data, the RDF data are are produced by translating into RDF the information contained in the XML FileSetDescriptor.xml file, that represents the nature, content and provenance of the non-DICOM data files. This information describes the complex worflow leading to such non-DICOM files and documents the various tasks involved and their inputs and outputs.

A companion GitHub repository (https://github.com/MediCIS/NewWorkflowDescriptor2XSD.git) contains the software managing this XML data and the Java functions used to read/write this XML data.

A documentation of the Semantic translator is provided in the MEDIRAD_Documentation_of_Semantic_translatorV1.1.pdf file located in the DOC/documentation-semantic-graph/ directory.

The Semantic database is aligned onto the OntoMEDIRAD ontology. A documentation of this ontology is provided in the MEDIRAD_MS8_Documentation_of_ontology_of_IRDBB_semantic_repositoryV1.3.pdf file, also located in the DOC/documentation-semantic-graph/ directory.

The articles to be cited when referring to this work are the following ones: 
- Spaltenstein J, Roduit N, van Dooren N, Pasquier G, Brenet M, Gibaud B, Pasquier G, Mildenberger P and Ratib O. A multicentric IT platform for storage and sharing of imaging-based radiation dosimetric data. Int J Comput Assist Radiol Surg. 2020 Oct;15(10):1639-1643. doi: 10.1007/s11548-020-02179-y.
- Gibaud B, Brenet M, Pasquier G, Vergara Gil A, Bardiès M, Stratakis J, Damilakis J, van Dooren N, Spaltenstein J, and Ratib O. A semantic database for integrated management of image and dosimetric data in low radiation dose research in medical imaging. American Medical Informatics Association (AMIA) Conference, November 2020, Chicago (USA).
(copies are provided in the DOC/papers-for-citation directory)
