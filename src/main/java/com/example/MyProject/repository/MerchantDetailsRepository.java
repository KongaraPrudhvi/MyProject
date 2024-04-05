package com.example.MyProject.repository;

import com.example.MyProject.entity.MerchantDetails;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantDetailsRepository extends JpaRepository<MerchantDetails,Integer> {

}
