package by.tr.web.dao.impl;

import by.tr.web.dao.PersonalAffairsDAO;
import by.tr.web.dao.parser.StudentsSAXParser;
import by.tr.web.domain.Student;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class XMLPersonalAffairsDAO implements PersonalAffairsDAO {
    @Override
    public List<Student> parseXML() {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            StudentsSAXParser parser = new StudentsSAXParser();
            reader.setContentHandler(parser);
            reader.parse(new InputSource(getClass().getResourceAsStream("/Students.xml")));
            return parser.getStudentList();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
