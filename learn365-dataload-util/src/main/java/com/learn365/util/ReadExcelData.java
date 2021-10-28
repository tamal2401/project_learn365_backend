package com.learn365.util;

import com.learn365.ExcelSheetName;
import com.learn365.LoadConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ReadExcelData {

    public Workbook getWorkBookSheet(File excelpath, int position) throws IOException, InvalidFormatException {


        Workbook workbook = WorkbookFactory.create(excelpath);
        return workbook;
    }


}
