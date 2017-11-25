package by.tr.web.dao.parser;

import by.tr.web.dao.exception.DAOException;
import by.tr.web.domain.ExceptionMessages;
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void buildStudentList(String fileName) throws DAOException {

        createDocumentBuilder();
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
            throw new DAOException(ExceptionMessages.FILE_PARSE, e);
        } catch (IOException e) {
            throw new DAOException(ExceptionMessages.FILE_LOADER, e);
        } catch (URISyntaxException e) {
            throw new DAOException(ExceptionMessages.FILE_URI, e);
        }
    }

    private void createDocumentBuilder() throws DAOException {

        DocumentBuilderFactory instance = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = instance.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new DAOException(ExceptionMessages.DOCUMENT_BUILDER, e);
        }
    }

    private Student buildStudent(Element studentElement) throws DAOException {

        Student student = new Student();
        student.setSurname(getSurname(studentElement));
        student.setFirstName(getFirstName(studentElement));
        student.setPatronymic(getPatronymic(studentElement));
        student.setBrothersAmount(getBrothersAmount(studentElement));
        student.setSistersAmount(getSistersAmount(studentElement));
        student.setFather(buildParent(studentElement.getElementsByTagName(Parameters.FATHER.name().toLowerCase())));
        student.setMother(buildParent(studentElement.getElementsByTagName(Parameters.MOTHER.name().toLowerCase())));
        return student;
    }

    private Parent buildParent(NodeList parentList) throws DAOException {

        Element parentElement = (Element) parentList.item(0);
        Parent parent = new Parent();
        parent.setSurname(getSurname(parentElement));
        parent.setFirstName(getFirstName(parentElement));
        parent.setPatronymic(getPatronymic(parentElement));
        parent.setSalary(getSalary(parentElement));
        return parent;
    }

    private String getSurname(Element element) throws DAOException {

        String surname;
        if (isNeededElement(element, Parameters.STUDENT)) {

            surname = getParameterContext(element, Parameters.SURNAME.name());
        } else if (isNeededElement(element, Parameters.FATHER)) {

            surname = getParameterContext(element, toXMLFormat(Parameters.FNS_SURNAME.name()));
        } else if (isNeededElement(element, Parameters.MOTHER)) {

            surname = getParameterContext(element, toXMLFormat(Parameters.MNS_SURNAME.name()));
        } else {

            throw new DAOException(ExceptionMessages.NO_SURNAME);
        }
        return surname;
    }

    private String getFirstName(Element element) throws DAOException {

        String firstName;
        if (isNeededElement(element, Parameters.STUDENT)) {

            firstName = getParameterContext(element, Parameters.FIRST_NAME.name());
        } else if (isNeededElement(element, Parameters.FATHER)) {

            firstName = getParameterContext(element, toXMLFormat(Parameters.FNS_FIRST_NAME.name()));
        } else if (isNeededElement(element, Parameters.MOTHER)) {

            firstName = getParameterContext(element, toXMLFormat(Parameters.MNS_FIRST_NAME.name()));
        } else {

            throw new DAOException(ExceptionMessages.NO_FIRST_NAME);
        }
        return firstName;
    }

    private String getPatronymic(Element element) throws DAOException {

        String patronymic;
        if (isNeededElement(element, Parameters.STUDENT)) {

            patronymic = getParameterContext(element, Parameters.PATRONYMIC.name());
        } else if (isNeededElement(element, Parameters.FATHER)) {

            patronymic = getParameterContext(element, toXMLFormat(Parameters.FNS_PATRONYMIC.name()));
        } else if (isNeededElement(element, Parameters.MOTHER)) {

            patronymic = getParameterContext(element, toXMLFormat(Parameters.MNS_PATRONYMIC.name()));
        } else {

            throw new DAOException(ExceptionMessages.NO_PATRONYMIC);
        }
        return patronymic;
    }

    private int getBrothersAmount(Element element) {

        return Integer.parseInt(getParameterContext(element, Parameters.BROTHERS_AMOUNT.name()));
    }

    private int getSistersAmount(Element element) {

        return Integer.parseInt(getParameterContext(element, Parameters.SISTERS_AMOUNT.name()));
    }

    private double getSalary(Element element) throws DAOException {

        String salary;
        if (isNeededElement(element, Parameters.FATHER)) {

            salary = getParameterContext(element, toXMLFormat(Parameters.FNS_SALARY.name()));
        } else if (isNeededElement(element, Parameters.MOTHER)) {

            salary = getParameterContext(element, toXMLFormat(Parameters.MNS_SALARY.name()));
        } else {

            throw new DAOException(ExceptionMessages.NO_SALARY);
        }
        return Double.parseDouble(salary);
    }

    private boolean isNeededElement(Element element, Parameters parameter) {

        return element.getTagName().equals(parameter.name().toLowerCase());
    }

    private String toXMLFormat(String element) {

        return element.replaceFirst("_", ":");
    }

    private String getParameterContext(Element element, String parameter) {

        NodeList nodeList = element.getElementsByTagName(parameter.toLowerCase());
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

}
