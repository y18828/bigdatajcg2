package com.bigdatajcg2.controller;


import com.bigdatajcg2.dao.DataDao;
import com.bigdatajcg2.entities.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddController {


    @Autowired
    DataDao dataDao;

    /**
     * 来到添加页面
     * @return
     */
    @GetMapping("/add")
    public String toAddPage(){
        return "data/add";
    }

    /**
     * 信息添加
     * 接收请求参数自动绑定
     */
    @PostMapping("/add")
    public String addData(Data data){
        //保存到数据库
        dataDao.save(data);
        //我们点击添加完成之后来到数据列表页面
        //如果我们写的 reutrn "datas"； thymeleaf默认就会拼串 会来到classpath:/temlates/datas.html
        //所以此处我们写的是   1) return "forward:XXX"; 转发到一个地址   2) return "redirect:XXX"; 重定向到一个地址
        //return "redirect:/datas"   /代表的当前项目路径   datas
        return "redirect:/datas";
    }





}
