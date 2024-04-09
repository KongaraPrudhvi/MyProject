package com.example.MyProject.controller;

import com.example.MyProject.entity.MerchantDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.MyProject.service.IExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GetMerchantDetails {

    @Autowired
    IExcelDataService excelService;

    @GetMapping("/readdata")
    public ResponseEntity<List<MerchantDetails>> readAllData(){
        List<MerchantDetails> excelDataAsList=excelService.getExcelDataAslist();

        return new ResponseEntity<>(excelDataAsList, HttpStatus.OK);
    }
    @GetMapping("/readdatafromdb")
    public ResponseEntity<List<MerchantDetails>> readAllDBData(){
        List<MerchantDetails> merchantList=excelService.getDbDetails();
        return new ResponseEntity<>(merchantList,HttpStatus.OK);
    }

    @GetMapping(value = "/readdata",params = {"merchantId"})
    public ResponseEntity<List<MerchantDetails>> readData(@RequestParam String merchantId) {
        List<MerchantDetails> excelDataAsList=excelService.getDetailsById(merchantId);
        return new ResponseEntity<>(excelDataAsList, HttpStatus.OK);
    }

    @GetMapping(value="/readdatafromdb",params={"merchantId"})
    public ResponseEntity<List<MerchantDetails>> readDBData(@RequestParam String merchantId){
        List<MerchantDetails> merchantList=excelService.getDbDetailsById(merchantId);
            return new ResponseEntity<>(merchantList,HttpStatus.OK);
        }

    @PostMapping("/readandinsert")
    public ResponseEntity<String> InsertData(){
         excelService.saveExcelData();
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateList(@RequestBody MerchantDetails model){
        excelService.updateDetails(model);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}



