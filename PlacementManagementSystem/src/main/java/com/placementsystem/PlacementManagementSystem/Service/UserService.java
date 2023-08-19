package com.placementsystem.PlacementManagementSystem.Service;


import com.placementsystem.PlacementManagementSystem.Entity.UserDetail;
import com.placementsystem.PlacementManagementSystem.Exception.InvalidUserNameAndPassword;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {


    private List<UserDetail> getUserDetailsFromXml() {
        String filePath = "src/main/resources/Users.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List< UserDetail > userList = new ArrayList < UserDetail>() ;
        try{
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("User");
            for (int i = 0; i < nodeList.getLength(); i++) {
                userList.add(getUser(nodeList.item(i)));
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return userList;
    }

    private static UserDetail getUser(Node node) {

        UserDetail user = new UserDetail();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            user.setFirstName(getTagValue("firstName", element));
            user.setLastName(getTagValue("lastName", element));
            user.setPassword(getTagValue("password",element));
        }
        return user;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    @Override
    public UserDetails loadUserByUsername(String userName) {

        //Logic to get the user form the Database
        List<UserDetail> users = getUserDetailsFromXml();
        UserDetail userDetail = users.stream().filter(user -> user.getFirstName().equalsIgnoreCase(userName)).findFirst().orElse(null);
        if(Objects.isNull(userDetail)){
            throw new InvalidUserNameAndPassword("Invalid Credentials");
        }
         return new User(userDetail.getFirstName(), userDetail.getPassword(), new ArrayList<>());

    }

}
