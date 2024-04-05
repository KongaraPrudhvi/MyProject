package com.example.MyProject.service;

import com.example.MyProject.entity.MerchantDetails;
import com.example.MyProject.repository.MerchantDetailsRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelDataServiceImpl implements IExcelDataService {

    @Value("${app.upload.file:${user.home}}")
    public String EXCEL_FILE_PATH;

    @Autowired
    public MerchantDetailsRepository repo;

    Workbook workbook;

    public List<MerchantDetails> getExcelDataAslist() {

        List<String> merchantList = new ArrayList<String>();

        DataFormatter dataFormatter = new DataFormatter();

        try {
            workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("There are" + workbook.getNumberOfSheets() + " Merchant Details");

        Sheet sheet = workbook.getSheetAt(0);

        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String Cellvalue = dataFormatter.formatCellValue(cell);
                merchantList.add(Cellvalue);

            }
        }

        List<MerchantDetails> invList = createList(merchantList, noOfColumns);

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return invList;
    }

    private List<MerchantDetails> createList(List<String> excelData, int noOfColumns) {

        ArrayList<MerchantDetails> invList = new ArrayList<MerchantDetails>();

        int i = noOfColumns;
        do {
            MerchantDetails inv = new MerchantDetails();
            inv.setId(null);
            inv.setMerchantId(excelData.get(i + 1));
            inv.setAni(excelData.get(i + 2));
            inv.setAccountNumber(excelData.get(i + 3));
            inv.setMerchantAuthJSON(excelData.get(i + 4));

            invList.add(inv);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return invList;
    }

    public void saveExcelData() {

        List<MerchantDetails> merchantList = getExcelDataAslist();
        try {
            repo.saveAll(merchantList);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
