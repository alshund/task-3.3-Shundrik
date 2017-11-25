package by.tr.web.dao.parser;

import by.tr.web.domain.Parameters;
import by.tr.web.domain.Parent;
import by.tr.web.domain.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class StudentsSAXParser extends DefaultHandler {
    private List<Student> studentList;
    private Student student;
    private Parent father;
    private Parent mother;

    private StringBuilder text;

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public void startDocument() throws SAXException {

        studentList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        text = new StringBuilder();
        parseStartElement(qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        parseEndElement(qName);
    }

    private void parseStartElement(String qName) {

        Parameters tagName = Parameters.valueOf(toXMLFormat(qName));
        switch (tagName) {
            case STUDENT:
                student = new Student();
                break;
            case FATHER:
                father = new Parent();
                break;
            case MOTHER:
                mother = new Parent();
                break;
        }
    }

    private void parseEndElement(String qName) {

        if (qName.contains(Parameters.FNS.name().toLowerCase())) {

            parseFatherEndElement(qName);
        } else if (qName.contains(Parameters.MNS.name().toLowerCase())) {

            parserMotherEndElement(qName);
        } else {

            parseStudentEndElement(qName);
        }
    }

    private void parseStudentEndElement(String qName) {

        Parameters tagName = Parameters.valueOf(toXMLFormat(qName));
        switch (tagName) {
            case STUDENT:
                studentList.add(student);
                break;
            case SURNAME:
                student.setSurname(text.toString());
                break;
            case FIRST_NAME:
                student.setFirstName(text.toString());
                break;
            case PATRONYMIC:
                student.setPatronymic(text.toString());
                break;
            case BROTHERS_AMOUNT:
                student.setBrothersAmount(Integer.parseInt(text.toString()));
                break;
            case SISTERS_AMOUNT:
                student.setSistersAmount(Integer.parseInt(text.toString()));
                break;
            case FATHER:
                student.setFather(father);
                break;
            case MOTHER:
                student.setMother(mother);
                break;
        }
    }

    private void parseFatherEndElement(String qName) {

        Parameters tagName = Parameters.valueOf(toXMLFormat(qName));
        switch (tagName) {
            case FATHER:
                student.setFather(father);
                break;
            case FNS_SURNAME:
                father.setSurname(text.toString());
                break;
            case FNS_FIRST_NAME:
                father.setFirstName(text.toString());
                break;
            case FNS_PATRONYMIC:
                father.setPatronymic(text.toString());
                break;
            case FNS_SALARY:
                father.setSalary(Double.parseDouble(text.toString()));
                break;
        }
    }

    private void parserMotherEndElement(String qName) {

        Parameters tagName = Parameters.valueOf(toXMLFormat(qName));
        switch (tagName) {
            case MOTHER:
                student.setMother(mother);
                break;
            case MNS_SURNAME:
                mother.setSurname(text.toString());
                break;
            case MNS_FIRST_NAME:
                mother.setFirstName(text.toString());
                break;
            case MNS_PATRONYMIC:
                mother.setPatronymic(text.toString());
                break;
            case MNS_SALARY:
                mother.setSalary(Double.parseDouble(text.toString()));
                break;
        }
    }

    private String toXMLFormat(String qName) {

        return qName.replaceFirst(":", "_").toUpperCase();
    }
}
