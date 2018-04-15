package XMLDomEngine;

import Entity.Input;
import Entity.ProfileObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class profileStore {
    private String fileName = "ApplicationStorage.xml";
    public List<ProfileObject> getProfiles(Integer userId)
    {
        //Create the return list of users
        List<ProfileObject> returnObject = new ArrayList<ProfileObject>();
        //Create the document builder factory
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try{
            //Create the document builder
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            // get the list of all the users in the application
            NodeList profileList = doc.getElementsByTagName("Profile");

            for(int i = 0; i < profileList.getLength(); i++)
            {
                Node p = profileList.item(i);
                if(p.getNodeType() == Node.ELEMENT_NODE) {
                    Element profile = (Element) p;


                    NodeList profileInfo = profile.getChildNodes();

                    for (int j = 0; j < profileInfo.getLength(); j++) {
                        Node pInfo = profileInfo.item(j);
                        ProfileObject profileObject = new ProfileObject();
                        Input input = new Input();
                        if (pInfo.getNodeType() == Node.ELEMENT_NODE) {
                            Element inputData = (Element) pInfo;
                            if (!inputData.getAttribute("id").isEmpty()) {
                                String id = inputData.getAttribute("id");
                                profileObject.setId(Integer.parseInt(id));
                            }
                            NodeList inputList = inputData.getChildNodes();
                            Integer uId = 0;
                            for(int k = 0; k < inputList.getLength(); k++)
                            {
                                Node inputItem =  inputList.item(k);

                                if(inputItem.getNodeType() == Node.ELEMENT_NODE)
                                {
                                    Element il = (Element) inputItem;

                                    if (il.getTagName() == "UserId")
                                    {
                                        uId = Integer.parseInt(il.getTextContent().trim());
                                    }
                                    if (uId == userId) {
                                        profileObject.setUserId(uId);
                                        if (il.getTagName() == "PropertyAddress") {
                                            input.setPropertyAddress(il.getTextContent().trim());
                                        } else if (il.getTagName() == "TotalPurchasePrice") {
                                            input.setTotalPurchasePrice(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "DownPayment") {
                                            input.setDownPayment(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "AnticipatedImprovements") {
                                            input.setAnticipatedImprovements(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "MortgageInterestRate") {
                                            input.setMortgageInterestRate(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "MortgageLengthYears") {
                                            input.setMortgageLengthYears(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "MonthlyIncomeRent") {
                                            input.setMonthlyIncomeRent(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "VacancyPercent") {
                                            input.setVacancyPercent(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "AdditionalFees") {
                                            input.setAdditionalFees(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "PropertyTaxes") {
                                            input.setPropertyTaxes(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "InsuranceCost") {
                                            input.setInsuranceCost(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "ManagementCost") {
                                            input.setManagementCost(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "MaintenanceCost") {
                                            input.setMaintenanceCost(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "Advertising") {
                                            input.setAdvertising(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "UtilityCosts") {
                                            input.setUtilityCosts(Double.parseDouble(il.getTextContent().trim()));
                                        } else if (il.getTagName() == "AppreciationPerYear") {
                                            input.setAppreciationPerYear(Double.parseDouble(il.getTextContent().trim()));
                                        } else {
                                            input.setAdditionalTaxes(Double.parseDouble(il.getTextContent().trim()));
                                        }

                                    }
                                }

                            }

                            profileObject.setInput(input);
                            returnObject.add(profileObject);

                        }
                    }
                }
            }
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return returnObject;
    }
    public void insertProfile(ProfileObject profile)
    {
        //Create the document builder factory
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try
        {
            //Create the document builder
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(fileName);

            Node rootNode = doc.getElementsByTagName("Profile").item(0);
            Element inputNode = doc.createElement("Input");
            Element userId = doc.createElement("UserId");
            Element propertyAddress = doc.createElement("PropertyAddress");
            Element totalPurchasePrice = doc.createElement("TotalPurchasePrice");
            Element DownPayment = doc.createElement("DownPayment");
            Element anticipatedImprovements = doc.createElement("AnticipatedImprovements");
            Element mortgageInterestRate = doc.createElement("MortgageInterestRate");
            Element mortgageLengthYears = doc.createElement("MortgageLengthYears");
            Element monthlyIncomeRent = doc.createElement("MonthlyIncomeRent");
            Element vacancyPercent = doc.createElement("VacancyPercent");
            Element additionalFees = doc.createElement("AdditionalFees");
            Element propertyTaxes = doc.createElement("PropertyTaxes");
            Element insuranceCost = doc.createElement("InsuranceCost");
            Element managementCost = doc.createElement("ManagementCost");
            Element maintenanceCost = doc.createElement("MaintenanceCost");
            Element advertising = doc.createElement("Advertising");
            Element utilityCosts = doc.createElement("UtilityCosts");
            Element appreciationPerYear = doc.createElement("AppreciationPerYear");
            Element additionalTaxes = doc.createElement("AdditionalTaxes");


            Text uId = doc.createTextNode("\n\t\t\t\t" + profile.getUserId()+ "\n");
            Text pAddress = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getPropertyAddress()+ "\n");
            Text tPurchasePrice = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getTotalPurchasePrice()+ "\n");
            Text downPayment = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getDownPayment()+ "\n");
            Text aImprovements = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getAnticipatedImprovements()+ "\n");
            Text mInterestRate = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getMortgageInterestRate()+ "\n");
            Text mLengthYears = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getMortgageLengthYears()+ "\n");
            Text mIncomeRent = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getMonthlyIncomeRent()+ "\n");
            Text vPercent = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getVacancyPercent()+ "\n");
            Text aFees = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getAdditionalFees()+ "\n");
            Text pTaxes = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getPropertyTaxes()+ "\n");
            Text iCost = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getInsuranceCost()+ "\n");
            Text manageCost = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getManagementCost()+ "\n");
            Text maintainCost = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getMaintenanceCost()+ "\n");
            Text advert = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getAdvertising()+ "\n");
            Text uCosts = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getUtilityCosts()+ "\n");
            Text aPerYear = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getAppreciationPerYear()+ "\n");
            Text aTaxes = doc.createTextNode("\n\t\t\t\t" + profile.getInput().getAdditionalTaxes()+ "\n");

            Text tab = doc.createTextNode("\t");
            Text endLine = doc.createTextNode("\n");

            inputNode.setAttribute("id",Integer.toString(profile.getId()));



            userId.appendChild(uId);
            propertyAddress.appendChild(pAddress);
            totalPurchasePrice.appendChild(tPurchasePrice);
            DownPayment.appendChild(downPayment);
            anticipatedImprovements.appendChild(aImprovements);
            mortgageInterestRate.appendChild(mInterestRate);
            mortgageLengthYears.appendChild(mLengthYears);
            monthlyIncomeRent.appendChild(mIncomeRent);
            vacancyPercent.appendChild(vPercent);
            additionalFees.appendChild(aFees);
            propertyTaxes.appendChild(pTaxes);
            insuranceCost.appendChild(iCost);
            managementCost.appendChild(manageCost);
            maintenanceCost.appendChild(maintainCost);
            advertising.appendChild(advert);
            utilityCosts.appendChild(uCosts);
            appreciationPerYear.appendChild(aPerYear);
            additionalTaxes.appendChild(aTaxes);

            Node node = inputNode;
            node.appendChild(userId);
            node.appendChild(propertyAddress);
            node.appendChild(totalPurchasePrice);
            node.appendChild(DownPayment);
            node.appendChild(anticipatedImprovements);
            node.appendChild(mortgageInterestRate);
            node.appendChild(mortgageLengthYears);
            node.appendChild(monthlyIncomeRent);
            node.appendChild(vacancyPercent);
            node.appendChild(additionalFees);
            node.appendChild(propertyTaxes);
            node.appendChild(insuranceCost);
            node.appendChild(managementCost);
            node.appendChild(maintenanceCost);
            node.appendChild(advertising);
            node.appendChild(utilityCosts);
            node.appendChild(appreciationPerYear);
            node.appendChild(additionalTaxes);

            rootNode.appendChild(node);
            rootNode.appendChild(endLine);
            rootNode.appendChild(tab);


            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            try
            {
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(fileName);
                transformer.transform(source, result);
            }
            catch (TransformerConfigurationException e)
            {
                e.printStackTrace();
            }
            catch (TransformerException e)
            {
                e.printStackTrace();
            }

        }

        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch(SAXException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public Integer getMaxProfileId()
    {
        Integer id = 0;
        //Create the document builder factory
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try
        {
            //Create the document builder
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(fileName);

            String extractedId = null;

            Node rootNode = doc.getElementsByTagName("Profile").item(0);
            Node lastProfile = rootNode.getLastChild().getPreviousSibling();
            if(lastProfile != null && lastProfile.getNodeType() == Node.ELEMENT_NODE)
            {
                Element lu = (Element)lastProfile;
                extractedId = lu.getAttribute("id");
                id = Integer.parseInt(extractedId);
            }
            else
            {
                id = 0;
            }

        }

        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch(SAXException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return id;
    }

}
