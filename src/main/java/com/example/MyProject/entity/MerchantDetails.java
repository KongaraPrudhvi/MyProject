package com.example.MyProject.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="merchantdetails")
public class MerchantDetails {

    public MerchantDetails() {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "merchant_id")
    private String merchantId;
    @Column(name = "ani")
    private String ani;
    @Column(name = "merchant_name")
    private String merchantName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "merchantauth_json")
    private String merchantAuthJSON;

    public String getAni() {
        return ani;
    }

    public void setAni(String ani) {
        this.ani = ani;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMerchantAuthJSON() {
        return merchantAuthJSON;
    }

    public void setMerchantAuthJSON(String merchantAuthJSON) {
        this.merchantAuthJSON = merchantAuthJSON;
    }
    public MerchantDetails(Integer id, String merchantId, String ani, String merchantName, String accountNumber, String merchantAuthJSON) {
        this.id = id;
        this.merchantId = merchantId;
        this.ani = ani;
        this.merchantName = merchantName;
        this.accountNumber = accountNumber;
        this.merchantAuthJSON = merchantAuthJSON;
    }

}