package by.tr.web.dao.impl.command.impl;

import by.tr.web.dao.exception.DAOException;
import by.tr.web.dao.impl.command.Command;
import by.tr.web.dao.parser.StudentsSAXParser;
import by.tr.web.domain.ExceptionMessages;
import by.tr.web.domain.FilePath;
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
    public List<Student> execute() throws DAOException {

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(parser);
            reader.parse(new InputSource(getClass().getResourceAsStream(FilePath.XML_FILE)));
            return parser.getStudentList();
        } catch (SAXException e) {
            throw new DAOException(ExceptionMessages.FILE_PARSE, e);
        } catch (IOException e) {
            throw new DAOException(ExceptionMessages.FILE_LOADER, e);
        }
    }
}
