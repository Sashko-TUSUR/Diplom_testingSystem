package gpo.TestingSystem.Service.Reg;

import gpo.TestingSystem.Enumeration.EnumRole;
import gpo.TestingSystem.Models.Role;
import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Repositories.GroupsRepository;
import gpo.TestingSystem.Repositories.RoleRepository;
import gpo.TestingSystem.Repositories.StudentRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Имя", "Фамилия", "Отчество", "Группа" };
    static String SHEET = "users";
    static String name;
    static String surname;
    static String numGroup;

    public static RoleRepository roleRepository;
    public static GroupsRepository groupsRepository;
    public static StudentRepository studentRepository;

    @Autowired
    private ExcelHelper(RoleRepository roleRepository,GroupsRepository groupsRepository,StudentRepository studentRepository)
    {
        ExcelHelper.roleRepository=roleRepository;
        ExcelHelper.groupsRepository=groupsRepository;
        ExcelHelper.studentRepository=studentRepository;
    }


    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }



    public static List<User> excelToUsers(InputStream is) {

        Role role = roleRepository.findByName(EnumRole.ROLE_STUDENT).get();
        try {
            Workbook workbook = WorkbookFactory.create(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<User> users  = new ArrayList<User>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                User user = new User();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();


                    switch (cellIdx) {
                        case 0 -> {
                            name = currentCell.getStringCellValue();
                            user.setNameUser(currentCell.getStringCellValue());
                        }
                        case 1 -> {
                            surname = currentCell.getStringCellValue();
                            user.setSurname(currentCell.getStringCellValue());
                        }
                        case 2 -> user.setPatronymic(currentCell.getStringCellValue());
                        case 3 -> {
                            currentCell.setCellType(CellType.STRING);
                            numGroup = currentCell.getStringCellValue();
                        }
                        default -> {
                        }
                    }

                    cellIdx++;
                }
                user.setRoles(Collections.singleton(role));
                user.setLogin(LoginGeneration.loginGeneration(name, surname));
                user.setPassword(PasswordGeneration.passwordGeneration());
                users.add(user);

            }
            workbook.close();

            return users;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
