package com.jf.controller;

import com.jf.service.SourceNicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SourceNicheController {

    @Autowired
    private SourceNicheService sourceNicheService;

    @ResponseBody
    @RequestMapping("/sourceNiche/addProduct")
    public String addProduct(HttpServletRequest request) {
        try {
            sourceNicheService.addSourceNicheProduct();
       } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
        return "添加成功";
    }

}
