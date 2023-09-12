package com.bowen.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PropertySource("classpath:service.properties") //註冊resource來源
public class UbikeController {
    //Data Field
    @Value("${app.service.ubikeqry}")
    private String ubikeQryService;
    //提供一個View Page 進行區域的Ubike即時資訊查詢
    @GetMapping("/ubike/qrt")
    public String ubikeQry(){
        System.out.println(this.ubikeQryService);

        return "ubikeqry";
    }

}
