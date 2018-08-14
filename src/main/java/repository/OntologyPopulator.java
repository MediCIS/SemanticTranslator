package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pixelmed.dicom.ContentItem;

public abstract class OntologyPopulator {
	static OntModel populateModel;
	static OntModel model;
	
	static String handle; static String patientID; 
	static Individual patient; static Individual patientRole;
	static Individual i; 
	
	static Memory memory = Application.memory;

	static String racineURI = "http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#";
	static String racineDCM = "http://dicom.nema.org/resources/ontology/DCM/";
	static String racineObo = "http://purl.obolibrary.org/obo/";
	static String racineMol = "http://purl.bioontology.org/ontology/SNMI/";
	
	protected static final Logger logger = LoggerFactory.getLogger(repository.OntologyPopulator.class);
	
	static Hashtable<String, String> dico;
	
	public static void retrievePatientData(PatientDescriptorType patientData) {
		logger.info("Retrieving PatientData");
		
		String patientWeight;String patientBirthDate;
		String patientSex; String patientAge; String patientSize;

		populateModel = ModelFactory.createOntologyModel();
		if (model==null) {model = Application.getModel();}
		if (memory==null) {memory = Application.memory;}

		patient = createIndiv(generateName("Human"), model.getResource(racineURI+"human"));
		patientRole = createIndiv(generateName("Patient"), model.getResource(racineURI+"patient"));

		addObjectProperty(patient, racineObo+"BFO_0000087", patientRole);
		//addObjectProperty(patient, racineObo+"BFO_0000070", createIndiv("Patient", model.getResource(racineURI+"patient")));

		if (patientData.getPatientID00100020()!=null) {
			patientID = patientData.getPatientID00100020();
			addDataProperty(patient, racineURI+"has_id", patientID);
		}

		if (patientData.getPatientBirthDate00100030()!=null) {
			patientBirthDate = patientData.getPatientBirthDate00100030();
			addDataProperty(patient, racineURI+"has_birthdate", patientBirthDate);
		}

		if (patientData.getPatientSex00100040()!=null) {
			patientSex = patientData.getPatientSex00100040();
			Individual sexe;
			switch (patientSex) {
			case "M":
				sexe = createIndiv(model.getResource(racineURI+"male"));
				addObjectProperty(patient, racineURI+"has_sex", sexe); break;
			case "F":
				sexe = createIndiv(model.getResource(racineURI+"female")); 
				addObjectProperty(patient, racineURI+"has_sex", sexe); break;	
			default:break;
			}
		}

		if (patientData.getPatientAge00101010()!=null) {
			patientAge = patientData.getPatientAge00101010();
			i = createIndiv(generateName("age_of_patient_undergoing_medical_procedure"), model.getResource(racineURI+"age_of_patient_undergoing_medical_procedure"));
			String test = patientAge.substring(0, patientAge.length()-1);
			patientAge = patientAge.replaceAll("Y", "");
			patientAge = patientAge.replaceAll("M", "");
			switch (test) {
			case "M":
				addDataProperty(i, racineURI+"months", patientAge); break;
			case "Y":
				addDataProperty(i, racineURI+"years", patientAge); break;
			default:
				addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", patientAge);break;
			}
			addObjectProperty(i, racineURI+"is_about", patient);
		}

		if (patientData.getPatientSize00101020()!=null && patientData.getPatientSize00101020().length()>1) {
			patientSize = patientData.getPatientSize00101020();
			i = createIndiv(generateName("Patient_Height"), model.getResource(racineURI+"patient_height"));
			addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", patientSize);
			addObjectProperty(i, racineURI+"is_about", patient);
		}

		if (patientData.getPatientWeight00101030()!=null && patientData.getPatientWeight00101030().length()>1) {
			patientWeight = patientData.getPatientWeight00101030();
			i = createIndiv(generateName("Patient_Weight"), model.getResource(racineURI+"patient_weight"));
			addDataProperty(i, "http://purl.obolibrary.org/obo/IAO_0000004", patientWeight);
			addObjectProperty(i, racineURI+"is_about", patient);
		}
	}

	public static void addDataProperty(Individual ind, String propName, String value) {
		logger.debug("Data Property adding with subjet : "+ind+" predicat : "+propName+" object : "+value);
		Property prop = populateModel.createDatatypeProperty(propName);
		ind.addProperty(prop, value);
	}

	public static void addObjectProperty(Individual ind, String propName, Individual ind2) {
		logger.debug("Object Property adding with subjet : "+ind+" predicat : "+propName+" object : "+ind2);
		ObjectProperty prop = populateModel.createObjectProperty(propName);	
		populateModel.add(ind, prop.asObjectProperty(), ind2);
	}

	public static Individual createIndiv(String name, Resource ressource) {
		logger.debug("Creating Individual named "+name+" with classs "+ressource.getLocalName());
		Individual ind = populateModel.createIndividual(racineURI+name, ressource);
		return ind;
	}
	
	public static Individual createIndiv(Resource ressource) {
		logger.debug("Creating Individual (punning) "+" with classs "+ressource.getLocalName());
		//Individual ind = populateModel.createIndividual(racineURI+name, ressource);
		Individual ind = populateModel.createIndividual(ressource.getURI(), ressource);
		return ind;
	}

	public static Individual createIndivWithUnit(String value, String name, String adresseValue, Resource ressource, String adresseProperty) {
		logger.debug("Creating Individual named "+name+" from "+adresseValue+" with Value and Unit : "+value);
		Individual indiValue = createIndiv(generateName(name), model.getResource(adresseValue));
		if (value.split("_")[0].contains(".")) {
			indiValue.addLiteral(populateModel.createProperty(racineObo+"IAO_0000004"), Double.parseDouble(value.split("_")[0]));
		} else {
			indiValue.addLiteral(populateModel.createProperty(racineObo+"IAO_0000004"), Integer.parseInt(value.split("_")[0]));
		}
		ressource.addProperty(populateModel.createProperty(adresseProperty), indiValue);
		return indiValue;
	}

	public static void loadDico() {
		BufferedReader br; String cle; String iri;
		try {
			dico = new Hashtable<String, String>();
			br = new BufferedReader(new FileReader("dico.txt"));
            for(String line; (line = br.readLine()) != null; ) {
                cle = line.split(":")[0];
                iri = line.replace(cle+":", "");
            	System.out.println("Dico : "+cle+" : "+iri);
                dico.put(cle, iri);
            }
		} catch (IOException e) {e.printStackTrace();}
	}

	public static Resource getResource(String cle) {
		if (dico==null) {loadDico();}
		cle = cle.replace(" ", "_");
		return model.getResource(dico.get(cle));
	}

	public static ArrayList<Individual> createIndividualOrgan(String name, ContentItem e) {
		ArrayList<Individual> listOrgans = new ArrayList<Individual>();
		Individual indOrgane = null; ContentItem lat; String latStr;
		logger.debug("Creating Organ (Individual) from organ name : "+name);
		switch (name) {
		//CID 4030
		case "Abdominal_aorta":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3789")); 
			listOrgans.add(indOrgane); break;
		case "Adrenal_gland":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9604")); 
			listOrgans.add(indOrgane); break;
		case "Aortic_arch":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3768")); 
			listOrgans.add(indOrgane); break;
		case "Brain":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_50801")); 
			listOrgans.add(indOrgane); break;
		case "Carotid_Artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3939"));  
			listOrgans.add(indOrgane); break;
		case "Cerebellum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_67944")); 
			listOrgans.add(indOrgane); break;
		case "Circle_of_Willis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_50454"));  
			listOrgans.add(indOrgane); break;
		case "Coronary_artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_49893")); 
			listOrgans.add(indOrgane); break;
		case "Cranial_venous_system":
			logger.warn("Undefined Organ : "+name); 
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineURI+"owl:nothing")); 
			listOrgans.add(indOrgane); break;
		case "Iliac_and/or_femoral artery":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA70249"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_70250"));
					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_70248"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_70248"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Kidney":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7204"));

					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7205"));

					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7203")); 
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7203")); 
				listOrgans.add(indOrgane); break;
			} break;
		case "Liver":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7197")); 
			listOrgans.add(indOrgane); break;
		case "Pancreas":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7198")); 
			listOrgans.add(indOrgane); break;
		case "Parathyroid":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13890"));
			listOrgans.add(indOrgane); break;
		case "Pulmonary_artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_66326"));
			listOrgans.add(indOrgane); break;
		case "Renal_artery":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14752"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14753"));
					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14751"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14751"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Spleen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7196"));
			listOrgans.add(indOrgane); break;
		case "Testis":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7211"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7212"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7210"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7210"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Thoracic_aorta":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3786"));
			listOrgans.add(indOrgane); break;
		case "Thymus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9609")); 
			listOrgans.add(indOrgane); break;
		case "Thyroid":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9603"));
			listOrgans.add(indOrgane); break;
		case "Uterus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_17558")); 
			listOrgans.add(indOrgane); break;

			// CID 4031
		case "Abdomen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577"));
			listOrgans.add(indOrgane); break;
		case "Abdomen_and_Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); 
			listOrgans.add(indOrgane);
			break;
		case "Acromioclavicular_joint":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25901"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25902"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25898"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25898"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Ankle_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35195"));
			listOrgans.add(indOrgane); break;
		case "Anus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_15711")); 
			listOrgans.add(indOrgane); break;
		case "Apex_of_Lung":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7369"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7381"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7319"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7319"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Bile_duct":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9706")); 
			listOrgans.add(indOrgane); break;
		case "Bladder":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_15900"));
			listOrgans.add(indOrgane); break;
		case "Bone_of_lower_limb":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24169"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24170"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24140"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24140"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Bone_of_upper_limb":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24160"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24161"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24139"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24139"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Breast":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9601")); //aproximatif : sexe
			listOrgans.add(indOrgane); break;
		case "Bronchus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7409"));
			listOrgans.add(indOrgane); break;
		case "Calcaneus":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24497"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24498"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24496"));
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24496"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Cervical_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24138"));
			listOrgans.add(indOrgane); break;
		case "Cervico-thoracic_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9140"));
			listOrgans.add(indOrgane); break;
		case "Chest":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576"));
			listOrgans.add(indOrgane); break;
		case "Chest_and_Abdomen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);			
			break;
		case "Chest,_Abdomen_and_Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);	
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); //aproximatif : multiple & sexe
			listOrgans.add(indOrgane);
			break;
		case "Clavicle":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13322"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13323"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13321"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13321"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Coccyx":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_20229"));
			listOrgans.add(indOrgane); break;
		case "Colon":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7201"));
			listOrgans.add(indOrgane); break;
		case "Duodenum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7206"));
			listOrgans.add(indOrgane); break;
		case "Elbow_joint":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_32294"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_32295"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_32289"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_32289"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Entire_body":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_256135"));
			listOrgans.add(indOrgane); break;
		case "Esophagus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7131"));
			listOrgans.add(indOrgane); break;
		case "Esophagus,_stomach_and_duodenum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7131"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7148"));
			listOrgans.add(indOrgane);	 
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7206"));
			listOrgans.add(indOrgane); break;
		case "Extremity":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7182"));
			listOrgans.add(indOrgane); break;
		case "Eye":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54449"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54450"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54448"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54448"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Eye_region":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54237"));
			listOrgans.add(indOrgane); break;
		case "Femur":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24474"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24475"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9611"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9611"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Fibula":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24480"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24481"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24479"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24479"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Finger":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9666"));
			listOrgans.add(indOrgane); break;
		case "Foot":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_11343"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_11344"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9664"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9664"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Forearm":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_11345"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_11346"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9663"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9663"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Gallbladder":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7202"));
			listOrgans.add(indOrgane); break;
		case "Hand":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9712"));
			listOrgans.add(indOrgane); break;
		case "Head":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7154"));
			listOrgans.add(indOrgane); break;
		case "Head_and_Neck":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7154"));
			listOrgans.add(indOrgane); 
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			break;
		case "Heart":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7088"));
			listOrgans.add(indOrgane); break;
		case "Hip_joint":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35179"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35180"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35178"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35178"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Humerus":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_23130"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_23131"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13303"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13303"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Ileum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7208"));
			listOrgans.add(indOrgane); break;
		case "Ilium":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16590"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16591"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16589"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16589"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Internal_Auditory_Canal":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_53103"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_53104"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_53163"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_53163"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Jaw_region":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54397"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54398"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54396"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54396"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Jejunum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7207"));
			listOrgans.add(indOrgane); break;
		case "Knee":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24977"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24978"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24974"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24974"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Large_intestine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7201"));
			listOrgans.add(indOrgane); break;
		case "Larynx":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_55097"));
			listOrgans.add(indOrgane); break;
		case "Lower_leg":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24979"));
			listOrgans.add(indOrgane); break;
		case "Lower_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7184"));
			listOrgans.add(indOrgane); break;
		case "Lumbar_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_65469"));
			listOrgans.add(indOrgane); break;
		case "Lumbo-sacral_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_65535"));
			listOrgans.add(indOrgane); break;
		case "Mandible":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_52748"));
			listOrgans.add(indOrgane); break;
		case "Mastoid_bone":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_56088"));
			listOrgans.add(indOrgane); break;
		case "Maxilla":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9711"));
			listOrgans.add(indOrgane); break;
		case "Mediastinum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9826"));
			listOrgans.add(indOrgane); break;
		case "Muscle_of_lower_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9622"));
			listOrgans.add(indOrgane); break;
		case "Muscle_of_upper_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9621"));
			listOrgans.add(indOrgane); break;
		case "Nasal_bone":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_52745"));
			listOrgans.add(indOrgane); break;
		case "Neck":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane); break;
		case "Neck_and_Chest	":	
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576"));
			listOrgans.add(indOrgane); break;
		case "Neck,_Chest_and_Abdomen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);break;
		case "Neck,_Chest,_Abdomen_and_Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);			
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); // approximatif : sexe
			listOrgans.add(indOrgane);	
			break;
		case "Optic_canal":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54775"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54776"));
					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54774"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54774"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Orbital_structure":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54449"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54450"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54448"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54448"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Pancreatic_duct_and_bile_duct_systems":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_10419")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9706")); 
			listOrgans.add(indOrgane);
			break;
		case "Paranasal_sinus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59679"));
			listOrgans.add(indOrgane); break;
		case "Parotid_gland":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59797"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59798"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59790"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59790"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Patella":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24486"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24487"));
					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24485"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24485"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); //aproximatif : sexe
			listOrgans.add(indOrgane); break;
		case "Pelvis_and_lower_extremities":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); //aproximatif : sexe 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7184")); 
			listOrgans.add(indOrgane);			
			break;
		case "Phantom":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineURI+"phantom_device")); 
			listOrgans.add(indOrgane); break;
		case "Prostate":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9600")); 
			listOrgans.add(indOrgane); break;
		case "Rectum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14544")); 
			listOrgans.add(indOrgane); break;
		case "Rib":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7574"));
			listOrgans.add(indOrgane); break;
		case "Sacroiliac_joint":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_21441"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_21442"));
					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_21440"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_21440"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Sacrum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16202"));
			listOrgans.add(indOrgane); break;
		case "Scapula":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13395"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13396"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13394"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13394"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Sella_turcica":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54709"));
			listOrgans.add(indOrgane); break;
		case "Sesamoid_bones_of_foot":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_241723"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_241725"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_71341"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_71341"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Shoulder":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_33642"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_33643"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25202"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25202"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Skull":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_46565")); 
			listOrgans.add(indOrgane); break;
		case "Small_intestine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7200")); 
			listOrgans.add(indOrgane); break;
		case "Spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13478"));
			listOrgans.add(indOrgane); break;
		case "Sternoclavicular_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25883"));
			listOrgans.add(indOrgane); break;
		case "Sternum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7485"));
			listOrgans.add(indOrgane); break;
		case "Stomach":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7148")); 
			listOrgans.add(indOrgane); break;
		case "Submandibular_gland":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59802"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59803"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_55093"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_55093"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Tarsal_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24491"));
			listOrgans.add(indOrgane); break;
		case "Temporomandibular_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54832"));
			listOrgans.add(indOrgane); break;
		case "Thigh":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24968"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24969"));
					listOrgans.add(indOrgane); break;	
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24967"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24967"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Thoracic_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9140"));
			listOrgans.add(indOrgane); break;
		case "Thoraco-lumbar_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9921"));
			listOrgans.add(indOrgane); break;
		case "Thumb":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_60325"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_60326"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24938"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24938"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Toe":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25046"));
			listOrgans.add(indOrgane); break;
		case "Trachea":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7394"));
			listOrgans.add(indOrgane); break;
		case "Upper_arm":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24890")); 
			listOrgans.add(indOrgane); break;
		case "Upper_limb":
			lat = verifChild(e, "Laterality");
			if (lat!= null) {
				latStr = e.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
				switch (latStr) {
				case "Right":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7185"));
					listOrgans.add(indOrgane); break;
				case "Left":
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7186"));
					listOrgans.add(indOrgane); break;
				default:
					indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7183"));
					listOrgans.add(indOrgane); break;
				}
			} else {
				indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7183"));
				listOrgans.add(indOrgane); break;
			} break;
		case "Upper_urinary_tract":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_45658"));
			listOrgans.add(indOrgane);
			break;
		case "Ureter":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9704")); 
			listOrgans.add(indOrgane); break;
		case "Urethra":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_19667")); 
			listOrgans.add(indOrgane); break;
		case "Uterus_and_fallopian_tubes":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_17558")); // completer
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_18245"));
			listOrgans.add(indOrgane); break;
		case "Vertebral_column_and_cranium":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13478"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_46565"));
			listOrgans.add(indOrgane); break;
		case "Wrist_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35291"));
			listOrgans.add(indOrgane); break;
		case "Zygoma":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_52747"));
			listOrgans.add(indOrgane); break;	

		default:
			logger.warn("Unknown Organ Name : "+name);
			indOrgane = createIndiv("unknown_organe"+"_"+patientID, model.getResource(racineObo+"FMA_67165"));
			listOrgans.add(indOrgane); break;
		}
		return listOrgans;
	}

	public static ArrayList<Individual> createIndividualOrgan(String name) {
		ArrayList<Individual> listOrgans = new ArrayList<Individual>();
		Individual indOrgane = null; 
		logger.debug("Creating Organ (Individual) from organ name : "+name);
		switch (name) {

		// nonDicomFileSetDescriptor
		case "bone":
			indOrgane = createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_5018")); 
			listOrgans.add(indOrgane); break;
		case "breast": case "breasts":
			indOrgane =  createIndiv("breasts"+"_"+patientID, model.getResource(racineURI+"breasts")); 
			listOrgans.add(indOrgane); break;
		case "esophagus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7131")); 
			listOrgans.add(indOrgane); break;
		case "heart":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7088")); 
			listOrgans.add(indOrgane); break;
		case "lungs":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_68877")); 
			listOrgans.add(indOrgane); break;
		case "skin":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7163")); 
			listOrgans.add(indOrgane); break;

			//CID 4030
		case "Abdominal_aorta":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3789")); 
			listOrgans.add(indOrgane); break;
		case "Adrenal_gland":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9604")); 
			listOrgans.add(indOrgane); break;
		case "Aortic_arch":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3768")); 
			listOrgans.add(indOrgane); break;
		case "Brain":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_50801")); 
			listOrgans.add(indOrgane); break;
		case "Carotid_Artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3939"));  
			listOrgans.add(indOrgane); break;
		case "Cerebellum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_67944")); 
			listOrgans.add(indOrgane); break;
		case "Circle_of_Willis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_50454"));  
			listOrgans.add(indOrgane); break;
		case "Coronary_artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_49893")); 
			listOrgans.add(indOrgane); break;
		case "Cranial_venous_system":
			logger.warn("Unknown Organ Name : "+name);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineURI+"owl:nothing")); 
			listOrgans.add(indOrgane); break;
		case "Iliac_and/or_femoral artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_70248"));
			listOrgans.add(indOrgane); break;
		case "Kidney":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7203")); 
			listOrgans.add(indOrgane); break;
		case "Liver":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7197")); 
			listOrgans.add(indOrgane); break;
		case "Pancreas":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7198")); 
			listOrgans.add(indOrgane); break;
		case "Parathyroid":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13890"));
			listOrgans.add(indOrgane); break;
		case "Pulmonary_artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_66326"));
			listOrgans.add(indOrgane); break;
		case "Renal_artery":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14751"));
			listOrgans.add(indOrgane); break;
		case "Spleen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7196"));
			listOrgans.add(indOrgane); break;
		case "Testis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7210"));
			listOrgans.add(indOrgane); break;
		case "Thoracic_aorta":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_3786"));
			listOrgans.add(indOrgane); break;
		case "Thymus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9609")); 
			listOrgans.add(indOrgane); break;
		case "Thyroid":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9603"));
			listOrgans.add(indOrgane); break;
		case "Uterus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_17558")); 
			listOrgans.add(indOrgane); break;

			// CID 4031
		case "Abdomen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577"));
			listOrgans.add(indOrgane); break;
		case "Abdomen_and_Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); 
			listOrgans.add(indOrgane);
			break;
		case "Acromioclavicular_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25898"));
			listOrgans.add(indOrgane); break;
		case "Ankle_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35195"));
			listOrgans.add(indOrgane); break;
		case "Anus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_15711")); 
			listOrgans.add(indOrgane); break;
		case "Apex_of_Lung":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7319"));
			listOrgans.add(indOrgane); break;
		case "Bile_duct":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9706")); 
			listOrgans.add(indOrgane); break;
		case "Bladder":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_15900"));
			listOrgans.add(indOrgane); break;
		case "Bone_of_lower_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24140"));
			listOrgans.add(indOrgane); break;
		case "Bone_of_upper_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24139"));
			listOrgans.add(indOrgane); break;
		case "Breast":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9601")); //aproximatif : sexe
			listOrgans.add(indOrgane); break;
		case "Bronchus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7409"));
			listOrgans.add(indOrgane); break;
		case "Calcaneus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24496"));
			listOrgans.add(indOrgane); break;
		case "Cervical_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24138"));
			listOrgans.add(indOrgane); break;
		case "Cervico-thoracic_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9140"));
			listOrgans.add(indOrgane); break;
		case "chest":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576"));
			listOrgans.add(indOrgane); break;
		case "chest_and_Abdomen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);			
			break;
		case "chest,_Abdomen_and_Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);	
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); //aproximatif : multiple & sexe
			listOrgans.add(indOrgane);
			break;
		case "Clavicle":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13321"));
			listOrgans.add(indOrgane); break;
		case "Coccyx":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_20229"));
			listOrgans.add(indOrgane); break;
		case "Colon":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7201"));
			listOrgans.add(indOrgane); break;
		case "Duodenum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7206"));
			listOrgans.add(indOrgane); break;
		case "Elbow_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_32289"));
			listOrgans.add(indOrgane); break;
		case "Entire_body":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_256135"));
			listOrgans.add(indOrgane); break;
		case "Esophagus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7131"));
			listOrgans.add(indOrgane); break;
		case "Esophagus,_stomach_and_duodenum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7131"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7148"));
			listOrgans.add(indOrgane);	 
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7206"));
			listOrgans.add(indOrgane); break;
		case "Extremity":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7182"));
			listOrgans.add(indOrgane); break;
		case "Eye":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54448"));
			listOrgans.add(indOrgane); break;
		case "Eye_region":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54237"));
			listOrgans.add(indOrgane); break;
		case "Femur":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9611"));
			listOrgans.add(indOrgane); break;
		case "Fibula":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24479"));
			listOrgans.add(indOrgane); break;
		case "Finger":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9666"));
			listOrgans.add(indOrgane); break;
		case "Foot":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9664"));
			listOrgans.add(indOrgane); break;
		case "Forearm":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9663"));
			listOrgans.add(indOrgane); break;
		case "Gallbladder":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7202"));
			listOrgans.add(indOrgane); break;
		case "Hand":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9712"));
			listOrgans.add(indOrgane); break;
		case "Head":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7154"));
			listOrgans.add(indOrgane); break;
		case "Head_and_Neck":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7154"));
			listOrgans.add(indOrgane); 
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			break;
		case "Heart":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7088"));
			listOrgans.add(indOrgane); break;
		case "Hip_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35178"));
			listOrgans.add(indOrgane); break;
		case "Humerus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13303"));
			listOrgans.add(indOrgane); break;
		case "Ileum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7208"));
			listOrgans.add(indOrgane); break;
		case "Ilium":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16589"));
			listOrgans.add(indOrgane); break;
		case "Internal_Auditory_Canal":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_53163"));
			listOrgans.add(indOrgane); break;
		case "Jaw_region":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54396"));
			listOrgans.add(indOrgane); break;
		case "Jejunum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7207"));
			listOrgans.add(indOrgane); break;
		case "Knee":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24974"));
			listOrgans.add(indOrgane); break;
		case "Large_intestine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7201"));
			listOrgans.add(indOrgane); break;
		case "Larynx":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_55097"));
			listOrgans.add(indOrgane); break;
		case "Lower_leg":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24979"));
			listOrgans.add(indOrgane); break;
		case "Lower_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7184"));
			listOrgans.add(indOrgane); break;
		case "Lumbar_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_65469"));
			listOrgans.add(indOrgane); break;
		case "Lumbo-sacral_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_65535"));
			listOrgans.add(indOrgane); break;
		case "Mandible":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_52748"));
			listOrgans.add(indOrgane); break;
		case "Mastoid_bone":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_56088"));
			listOrgans.add(indOrgane); break;
		case "Maxilla":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9711"));
			listOrgans.add(indOrgane); break;
		case "Mediastinum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9826"));
			listOrgans.add(indOrgane); break;
		case "Muscle_of_lower_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9622"));
			listOrgans.add(indOrgane); break;
		case "Muscle_of_upper_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9621"));
			listOrgans.add(indOrgane); break;
		case "Nasal_bone":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_52745"));
			listOrgans.add(indOrgane); break;
		case "Neck":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane); break;
		case "Neck_and_chest	":	
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576"));
			listOrgans.add(indOrgane); break;
		case "Neck,_chest_and_Abdomen":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);break;
		case "Neck,_chest,_Abdomen_and_Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7155"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9576")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9577")); 
			listOrgans.add(indOrgane);			
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); // approximatif : sexe
			listOrgans.add(indOrgane);	
			break;
		case "Optic_canal":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54774"));
			listOrgans.add(indOrgane); break;
		case "Orbital_structure":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54448"));
			listOrgans.add(indOrgane); break;
		case "Pancreatic_duct_and_bile_duct_systems":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_10419")); 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9706")); 
			listOrgans.add(indOrgane);
			break;
		case "Paranasal_sinus":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59679"));
			listOrgans.add(indOrgane); break;
		case "Parotid_gland":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_59790"));
			listOrgans.add(indOrgane); break;
		case "Patella":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24485"));
			listOrgans.add(indOrgane); break;
		case "Pelvis":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); //aproximatif : sexe
			listOrgans.add(indOrgane); break;
		case "Pelvis_and_lower_extremities":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9578")); //aproximatif : sexe 
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7184")); 
			listOrgans.add(indOrgane);			
			break;
		case "Phantom":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineURI+"phantom_device")); 
			listOrgans.add(indOrgane); break;
		case "Prostate":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9600")); 
			listOrgans.add(indOrgane); break;
		case "Rectum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_14544")); 
			listOrgans.add(indOrgane); break;
		case "Rib":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7574"));
			listOrgans.add(indOrgane); break;
		case "Sacroiliac_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_21440"));
			listOrgans.add(indOrgane); break;
		case "Sacrum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_16202"));
			listOrgans.add(indOrgane); break;
		case "Scapula":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13394"));
			listOrgans.add(indOrgane); break;
		case "Sella_turcica":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54709"));
			listOrgans.add(indOrgane); break;
		case "Sesamoid_bones_of_foot":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_71341"));
			listOrgans.add(indOrgane); break;
		case "Shoulder":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25202"));
			listOrgans.add(indOrgane); break;
		case "Skull":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_46565")); 
			listOrgans.add(indOrgane); break;
		case "Small_intestine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7200")); 
			listOrgans.add(indOrgane); break;
		case "Spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13478"));
			listOrgans.add(indOrgane); break;
		case "Sternoclavicular_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25883"));
			listOrgans.add(indOrgane); break;
		case "Sternum":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7485"));
			listOrgans.add(indOrgane); break;
		case "Stomach":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7148")); 
			listOrgans.add(indOrgane); break;
		case "Submandibular_gland":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_55093"));
			listOrgans.add(indOrgane); break;
		case "Tarsal_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24491"));
			listOrgans.add(indOrgane); break;
		case "Temporomandibular_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_54832"));
			listOrgans.add(indOrgane); break;
		case "Thigh":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24967"));
			listOrgans.add(indOrgane); break;
		case "Thoracic_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9140"));
			listOrgans.add(indOrgane); break;
		case "Thoraco-lumbar_spine":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9921"));
			listOrgans.add(indOrgane); break;
		case "Thumb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24938"));
			listOrgans.add(indOrgane); break;
		case "Toe":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_25046"));
			listOrgans.add(indOrgane); break;
		case "Trachea":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7394"));
			listOrgans.add(indOrgane); break;
		case "Upper_arm":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_24890")); 
			listOrgans.add(indOrgane); break;
		case "Upper_limb":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_7183"));
			listOrgans.add(indOrgane); break;
		case "Upper_urinary_tract":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_45658"));
			listOrgans.add(indOrgane);
			break;
		case "Ureter":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_9704")); 
			listOrgans.add(indOrgane); break;
		case "Urethra":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_19667")); 
			listOrgans.add(indOrgane); break;
		case "Uterus_and_fallopian_tubes":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_17558")); // completer
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_18245"));
			listOrgans.add(indOrgane); break;
		case "Vertebral_column_and_cranium":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_13478"));
			listOrgans.add(indOrgane);
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_46565"));
			listOrgans.add(indOrgane); break;
		case "Wrist_joint":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_35291"));
			listOrgans.add(indOrgane); break;
		case "Zygoma":
			indOrgane =  createIndiv(name+"_"+patientID, model.getResource(racineObo+"FMA_52747"));
			listOrgans.add(indOrgane); break;	

		default:
			logger.warn("Unknown Organ Name : "+name);
			indOrgane = createIndiv("unknown_organe"+"_"+patientID, model.getResource(racineObo+"FMA_67165"));
			listOrgans.add(indOrgane); break;
		}
		return listOrgans;
	}
 
	public static ContentItem verifChild(ContentItem root, String test) {
		String name; String value; ContentItem child;
		for (int i=0; i<root.getChildCount(); i++) {
			child = (ContentItem) root.getChildAt(i);
			value = child.getConceptValue().trim().replaceAll(" ", "_").replaceAll("\n", "");
			name = child.getConceptNameCodeMeaning().trim().replaceAll(" ", "_").replaceAll("\n", "");
			if (name == test || value == test || name.contains(test) || value.contains(test)) {return child ;}
		}
		return null;
	}

	public static Individual getUnit(String u) {
		Individual unit; 
		logger.debug("Creating Unit (Individual) from unit name : "+u);
		u = u.trim();
		switch (u) {
		case "second": case "seconds":
		case "s":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000010")); break;
		case "mGy.cm":
			unit = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#milligray_centimeter"));break;
		case "mA":	
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000037")); break;
		case "kilovolt":
		case "kV":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000248")); break;
		case "volt":
		case "V":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000218")); break;
		case "Gy":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000134")); break;
		case "milligray":
		case "mGy":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000142")); break;
		case "mSv":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000138")); break;
		case "mm":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000016")); break;
		case "mS": case "ms":
			unit = createIndiv(model.getResource("http://purl.obolibrary.org/obo/UO_0000028")); break;	
		case "milligray per (milligray per (100 milliampere second))":
			unit = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#milligray_per_(milligray_per_(100_milliampere_second))")); 
			break;
		case "milligray per (100 milliampere second)":
			unit = createIndiv(model.getResource("http://medicis.univ-rennes1.fr/ontologies/ontospm/OntoMEDIRAD.owl#milligray_per_(100_milliampere_second)")); 
			break;	
		default:
			unit = createIndiv(generateName("Unknown_Unit"), model.getResource("http://purl.obolibrary.org/obo/UO_0000000"));
			logger.warn("WARN Unknown Unit : "+u);
		}
		return unit;
	}

	public static String generateName(String type) {return type+"_"+UUID.randomUUID();}

	public static String getURI(String cle) {
		if (dico==null) {loadDico();}
		cle = cle.replace(" ", "_");
		if (!dico.containsKey(cle)) {
			logger.error("has key ("+cle+") : "+dico.containsKey(cle));
		} return dico.get(cle);
	}
}