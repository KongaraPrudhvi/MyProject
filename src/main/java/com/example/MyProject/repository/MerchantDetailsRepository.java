package com.example.MyProject.repository;

import com.example.MyProject.entity.MerchantDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantDetailsRepository extends JpaRepository<MerchantDetails,Integer> {

    @Query(value="SELECT * FROM merchantdetails", nativeQuery=true)
    List<MerchantDetails> findMerchantDetails();

    @Query(value="SELECT * FROM merchantdetails where merchant_id=?1", nativeQuery=true)
    List<MerchantDetails> findDetailsByMerchantId(String merchantId);

    @Modifying
    @Transactional
    @Query(value="UPDATE merchantdetails i set i.merchant_name= :merchantName, i.ani= :ani, i.account_number= :accountNumber, i.merchantauth_json= :merchantAuthJson WHERE merchant_id= :merchantId",nativeQuery = true )
    void updateDetailsByMerchantId(String merchantId, String merchantName, String ani, String accountNumber, String merchantAuthJson);
}
