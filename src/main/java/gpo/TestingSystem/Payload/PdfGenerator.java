package gpo.TestingSystem.Payload;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gpo.TestingSystem.Models.Student;
import gpo.TestingSystem.Models.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public void generate(List<Student> userList, HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph paragraph1 = new Paragraph("Список студентов");

        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph1);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3, 3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.lightGray);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("Имя"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Фамилия"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Отчество"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Логин"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Пароль"));
        table.addCell(cell);

        // Iterating the list of students
        for (Student student: userList) {
            // Adding student name
            table.addCell(String.valueOf(student.getUser().getNameUser()));
            // Adding student surname
            table.addCell(student.getUser().getSurname());
            // Adding student middlename
            table.addCell(student.getUser().getPatronymic());
            table.addCell(student.getUser().getLogin());

            table.addCell(student.getUser().getPassword());
        }
            document.add(table);
            // Closing the document
            document.close();





    }


}
