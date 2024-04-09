package com.example.MyProject.service;

import com.example.MyProject.entity.MerchantDetails;

import java.util.List;


public interface IExcelDataService {

    List<MerchantDetails> getExcelDataAslist();

    List<MerchantDetails> getDbDetails();

    void saveExcelData();

    List<MerchantDetails> getDetailsById(String merchantId);

    void updateDetails(MerchantDetails model);

    List<MerchantDetails> getDbDetailsById(String merchantId);
}
