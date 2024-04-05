package com.example.MyProject.controller;

import com.example.MyProject.entity.MerchantDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.MyProject.repository.MerchantDetailsRepository;
import com.example.MyProject.service.IExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMerchantDetails {

    @Autowired
    IExcelDataService excelService;

    @GetMapping("/readData")
    public ResponseEntity<List<MerchantDetails>> readAllData(){
        List<MerchantDetails> excelDataAsList=excelService.getExcelDataAslist();
        return new ResponseEntity<>(excelDataAsList, HttpStatus.OK);
    }

    @PostMapping("/readAndUpdate")
    public ResponseEntity<String> updateAllData(){
       excelService.saveExcelData();
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }



}
