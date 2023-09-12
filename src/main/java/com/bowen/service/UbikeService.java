package com.bowen.service;

import com.bowen.domain.UbikeData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//Ubike服務
@RestController
public class UbikeService {
    //Data Field Injection(Attribute)
    // 將預設這側的application.properties直接使用Value annotation 運算屬性項目
    @Value("${outside.service.ubike}")
    private String ubkieService;
    //服務 提供輸入區域 找出即時資訊
    @GetMapping(path = "/api/ubike/{area}/rawdata",produces = "application/json")
    public List<UbikeData> ubikeQry(@PathVariable(name = "area")String sarea){
        //串結服務架構

        //1. 建構HttpClient RestTemplate
        var client = new RestTemplate(); //同步處理機制
        UbikeData[] result = client.getForObject(ubkieService, UbikeData[].class);

        //使用Stream 篩選機制
        List<UbikeData> lists = Arrays.asList(result);
//        var finalResult = lists.stream().filter((u) -> u.getSarea().equals(sarea)).collect(Collectors.toList());
        //使用foreach 逐筆走訪跟判斷
        List<UbikeData> finalResult =new ArrayList<>();
        for(UbikeData ub:result){
            //判斷區域
            if(ub.getSarea().equals(sarea)){
                finalResult.add(ub);
            }
        }


        return finalResult;
    }

}
