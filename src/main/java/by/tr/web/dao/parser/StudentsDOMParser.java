package by.tr.web.dao.parser;

import by.tr.web.domain.Parameters;
import by.tr.web.domain.Parent;
import by.tr.web.domain.Student;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StudentsDOMParser {
    private DocumentBuilder documentBuilder;
    private List<Student> studentList;

    public StudentsDOMParser() {

        DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = instance.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void buildStudentList(String fileName) {

        studentList = new ArrayList<>();
        Document document = null;
        try {
            document = documentBuilder.parse(new File(getClass().getResource(fileName).toURI()));
            Element root = document.getDocumentElement();
            NodeList studentList = root.getElementsByTagName(Parameters.STUDENT.name().toLowerCase());
            for (int studentIndex = 0; studentIndex < studentList.getLength(); studentIndex++) {
                Element studentElement = (Element) studentList.item(studentIndex);
                Student student = buildStudent(studentElement);
                this.studentList.add(student);
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Student buildStudent(Element studentElement) {

        Student student = new Student();
        student.setSurname(getParameterContext(studentElement, Parameters.SURNAME));
        student.setFirstName(getParameterContext(studentElement, Parameters.FIRST_NAME));
        student.setPatronymic(getParameterContext(studentElement, Parameters.PATRONYMIC));
        student.setBrothersAmount(Integer.parseInt(getParameterContext(studentElement, Parameters.BROTHERS_AMOUNT)));
        student.setSistersAmount(Integer.parseInt(getParameterContext(studentElement, Parameters.SISTERS_AMOUNT)));
        student.setFather(buildParent(studentElement.getElementsByTagName(Parameters.FATHER.name().toLowerCase())));
        student.setMother(buildParent(studentElement.getElementsByTagName(Parameters.MOTHER.name().toLowerCase())));
        return student;
    }

    private Parent buildParent(NodeList parentList) {

        Element parentElement = (Element) parentList.item(0);
        Parent parent = new Parent();
        parent.setSurname(getParameterContext(parentElement, Parameters.SURNAME));
        parent.setFirstName(getParameterContext(parentElement, Parameters.FIRST_NAME));
        parent.setPatronymic(getParameterContext(parentElement, Parameters.PATRONYMIC));
        parent.setSalary(Double.parseDouble(getParameterContext(parentElement, Parameters.SALARY)));
        return parent;
    }

    private String getParameterContext(Element element, Parameters parameter) {

        NodeList nodeList = element.getElementsByTagName(parameter.name().toLowerCase().replace(":", "_"));
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

}
