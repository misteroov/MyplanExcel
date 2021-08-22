package ru.pnsk.myplan.demo.controller;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.pnsk.myplan.demo.entity.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyPlanController {

    @PostMapping("/import")
    public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

        List<Test> tempStudentList = new ArrayList<Test>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=0;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Test tempStudent = new Test();

            XSSFRow row = worksheet.getRow(i);

            tempStudent.setId((int) row.getCell(0).getNumericCellValue());
            tempStudent.setContent(row.getCell(1).getStringCellValue());
            tempStudentList.add(tempStudent);

            System.out.println(tempStudentList);
        }
    }
    @GetMapping("/import")
    public String startPage(){

        return "import";

    }


}
