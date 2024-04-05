package com.example.MyProject.service;

import com.example.MyProject.entity.MerchantDetails;

import java.util.List;


public interface IExcelDataService {
    List<MerchantDetails> getExcelDataAslist();

    void saveExcelData();
}
