package XMLDomEngine;

import Entity.UserObject;
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

public class userStore {
    private String fileName = "ApplicationStorage.xml";
    public List<UserObject> getUsers()
    {
        //Create the return list of users
        List<UserObject> returnObject = new ArrayList<UserObject>();
        //Create the document builder factory
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try{
            //Create the document builder
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            // get the list of all the users in the application
            NodeList userList = doc.getElementsByTagName("User");
            for(int i = 0; i < userList.getLength(); i++)
            {
                Node u = userList.item(i);
                UserObject userObject = new UserObject();
                if(u.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element user = (Element) u;
                    if(!user.getAttribute("id").isEmpty())
                    {
                        String id = user.getAttribute("id");
                        userObject.setId(Integer.parseInt(id));
                    }

                    NodeList userInfo = user.getChildNodes();

                    for(int j = 0; j < userInfo.getLength(); j++)
                    {
                        Node uInfo = userInfo.item(j);

                        if(uInfo.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element userCredentials = (Element) uInfo;
                            if(userCredentials.getTagName() == "Username")
                            {
                                userObject.setUserName(userCredentials.getTextContent().trim());
                            }
                            else
                            {
                                userObject.setPassword(userCredentials.getTextContent().trim());
                            }
                        }
                    }
                }

                returnObject.add(userObject);
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
    public void insertUser(UserObject user)
    {
        //Create the document builder factory
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try
        {
            //Create the document builder
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(fileName);

            Node rootNode = doc.getElementsByTagName("UserLogin").item(0);
            Element userNode = doc.createElement("User");
            Element UserName = doc.createElement("Username");
            Element Password = doc.createElement("Password");


            Text uName = doc.createTextNode("\n\t\t\t\t" + user.getUserName()+ "\n");
            Text pwd = doc.createTextNode("\n\t\t\t\t" + user.getPassword() + "\n");
            Text tab = doc.createTextNode("\t");
            Text endLine = doc.createTextNode("\n");
            userNode.setAttribute("id",Integer.toString(user.getId()));
            UserName.appendChild(uName);
            Password.appendChild(pwd);
            userNode.appendChild(UserName);
            userNode.appendChild(Password);

            Node node =  rootNode;
            node.appendChild(userNode);
            node.appendChild(endLine);
            node.appendChild(tab);



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

    public Integer getMaxUserId()
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

            Node rootNode = doc.getElementsByTagName("UserLogin").item(0);
            Node lastUser = rootNode.getLastChild().getPreviousSibling();
            if(lastUser != null && lastUser.getNodeType() == Node.ELEMENT_NODE)
            {
                Element lu = (Element)lastUser;
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
