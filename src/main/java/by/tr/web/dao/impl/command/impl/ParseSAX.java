package by.tr.web.dao.impl.command.impl;

import by.tr.web.dao.impl.command.Command;
import by.tr.web.dao.parser.StudentsSAXParser;
import by.tr.web.domain.Student;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class ParseSAX implements Command {
    private StudentsSAXParser parser = new StudentsSAXParser();

    @Override
    public List<Student> execute() {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(parser);
            reader.parse(new InputSource(getClass().getResourceAsStream("/xml/Students.xml")));
            return parser.getStudentList();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
