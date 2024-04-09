package com.example.MyProject.service;

import com.example.MyProject.entity.MerchantDetails;
import com.example.MyProject.repository.MerchantDetailsRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.Array;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
            inv.setMerchantId(excelData.get(i + 1));
            inv.setMerchantName(excelData.get(i+2));
            inv.setAni(excelData.get(i + 3));
            inv.setAccountNumber(excelData.get(i + 4));
            inv.setMerchantAuthJSON(excelData.get(i + 5));

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


    public List<MerchantDetails> getDetailsById(String merchantId) {
        List<MerchantDetails> invList = new ArrayList<MerchantDetails>();
        List<MerchantDetails> foundList=new ArrayList<>();

        invList=getExcelDataAslist();

        int size=invList.size();

        for(int i=0;i<size;i++){

            if(Objects.equals(invList.get(i).getMerchantId(), merchantId)){
                MerchantDetails inv = new MerchantDetails();
                inv.setMerchantId(invList.get(i).getMerchantId());
                inv.setMerchantName(invList.get(i).getMerchantName());
                inv.setAni(invList.get(i).getAni());
                inv.setAccountNumber(invList.get(i).getAccountNumber());
                inv.setMerchantAuthJSON(invList.get(i).getMerchantAuthJSON());
                foundList.add(inv);
            }
        }
        return foundList;
    }

    public void updateDetails(MerchantDetails model){

        try {
            repo.updateDetailsByMerchantId(model.getMerchantId(), model.getMerchantName(), model.getAni(), model.getAccountNumber(), model.getMerchantAuthJSON());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<MerchantDetails> getDbDetailsById(String merchantId){

        List<MerchantDetails> merchantList = new ArrayList<>();
        try {

            merchantList=repo.findDetailsByMerchantId(merchantId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    return merchantList;
    }
    public List<MerchantDetails> getDbDetails(){

        List<MerchantDetails> merchantList = new ArrayList<>();
        try {

            merchantList=repo.findMerchantDetails();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return merchantList;
    }








}
