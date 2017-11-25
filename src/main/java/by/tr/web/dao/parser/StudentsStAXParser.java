package by.tr.web.dao.parser;

import by.tr.web.dao.exception.DAOException;
import by.tr.web.domain.ExceptionMessages;
import by.tr.web.domain.Parameters;
import by.tr.web.domain.Parent;
import by.tr.web.domain.Student;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class StudentsStAXParser {
    private XMLInputFactory xmlInputFactory;
    private List<Student> studentList = new ArrayList<>();

    public StudentsStAXParser() {

        xmlInputFactory = XMLInputFactory.newInstance();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void buildStudentList(String fileName) throws DAOException {

        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        try {
            inputStream = new FileInputStream(new File(getClass().getResource(fileName).toURI()));
            reader = xmlInputFactory.createXMLStreamReader(inputStream);

            String name;
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(Parameters.STUDENT.name().toLowerCase())) {
                        Student student = buildStudent(reader);
                        studentList.add(student);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(ExceptionMessages.FILE_LOADER, e);
        } catch (URISyntaxException e) {
            throw new DAOException(ExceptionMessages.FILE_URI, e);
        } catch (XMLStreamException e) {
            throw new DAOException(ExceptionMessages.FILE_PARSE, e);
        }
    }

    private Student buildStudent(XMLStreamReader reader) throws XMLStreamException, DAOException {

        Student student = new Student();
        boolean next = true;
        while (next && reader.hasNext()){
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    parseStudentStartElement(reader, student);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (Parameters.valueOf(reader.getLocalName().toUpperCase()) == Parameters.STUDENT) {
                        next = false;
                    }
                    break;
            }
        }
        return student;
    }

    private void parseStudentStartElement(XMLStreamReader reader, Student student) throws XMLStreamException, DAOException {

        String elementName = reader.getLocalName();
        switch (Parameters.valueOf(elementName.toUpperCase())) {
            case SURNAME:
                student.setSurname(getParameterContext(reader));
                break;
            case FIRST_NAME:
                student.setFirstName(getParameterContext(reader));
                break;
            case PATRONYMIC:
                student.setPatronymic(getParameterContext(reader));
                break;
            case BROTHERS_AMOUNT:
                student.setBrothersAmount(Integer.parseInt(getParameterContext(reader)));
                break;
            case SISTERS_AMOUNT:
                student.setSistersAmount(Integer.parseInt(getParameterContext(reader)));
                break;
            case FATHER:
                student.setFather(buildFather(reader));
                break;
            case MOTHER:
                student.setMother(buildMother(reader));
                break;
        }

    }

    private Parent buildFather(XMLStreamReader reader) throws XMLStreamException, DAOException {

        Parent mother = new Parent();
        boolean next = true;
        while (next && reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    parseParentStartElement(reader, mother);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (Parameters.valueOf(reader.getLocalName().toUpperCase()) == Parameters.FATHER) {
                        next = false;
                    }
                    break;
            }

        }
        return mother;

    }

    private Parent buildMother(XMLStreamReader reader) throws XMLStreamException, DAOException {

        Parent mother = new Parent();
        boolean next = true;
        while (next && reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    parseParentStartElement(reader, mother);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (Parameters.valueOf(reader.getLocalName().toUpperCase()) == Parameters.MOTHER) {
                        next = false;
                    }
            }


        }
        return mother;
    }

    private void parseParentStartElement(XMLStreamReader reader, Parent parent) throws XMLStreamException, DAOException {

        String elementName = reader.getLocalName();
        switch (Parameters.valueOf(elementName.toUpperCase())) {
            case SURNAME:
                parent.setSurname(getParameterContext(reader));
                break;
            case FIRST_NAME:
                parent.setFirstName(getParameterContext(reader));
                break;
            case PATRONYMIC:
                parent.setPatronymic(getParameterContext(reader));
                break;
            case SALARY:
                parent.setSalary(Double.parseDouble(getParameterContext(reader)));
                break;
        }

    }

    private String getParameterContext(XMLStreamReader reader) throws XMLStreamException, DAOException {

        String text;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        } else {
            throw new DAOException(ExceptionMessages.ELEMENT_DATA);
        }
        return text;
    }

}
