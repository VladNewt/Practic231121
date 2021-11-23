package pack;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        List<User> users = Arrays.asList(
                new User("a",10),
                new User("b", 10),
                new User("c", 10)
                );

        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();
        doc.setXmlVersion("1.0");
        doc.setXmlStandalone(false);
        Element root = doc.createElement("users");
        doc.appendChild(root);
        for (User user : users) {
            Element data = doc.createElement("user");
            root.appendChild(data);
            data.setAttribute("name", user.getName());
            data.setAttribute("age", String.valueOf(user.getAge()));
        }
        //
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("file.xml")));

        //
        Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("file.xml");
        parse.getDocumentElement().normalize();
        System.out.println(parse.getDocumentElement());
        //
        Node node = parse.getDocumentElement().getFirstChild();
        while (node!=null) {
            Attr val = parse.createAttribute("val");
            val.setValue("123");
            node.getAttributes().setNamedItem(val);
            System.out.println(node.getAttributes().getLength());
            node = node.getNextSibling();
        }
        //



    }


}
