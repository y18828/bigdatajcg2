package com.bigdatajcg2.controller;


import com.bigdatajcg2.dao.DataDao;
import com.bigdatajcg2.dao.SiteDao;
import com.bigdatajcg2.dao.StudentDao;
import com.bigdatajcg2.dao.TimeDao;
import com.bigdatajcg2.entities.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ListController {

    @Autowired
    SiteDao siteDao;
    @Autowired
    DataDao dataDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TimeDao timeDao;

    /**
     * 按钮编辑
     * @param
     * @return
     */
    @GetMapping("/data/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        System.out.println("ListController中   id为："+id);
        //获取当前数据内容
        Data data = dataDao.get(id);
        model.addAttribute("shuju",data);
        //回到修改页面(add是一个修改添加2合1的界面)
        System.out.println("shuju里面保存的内容为"+model.toString());
        return "data/add";
    }

    /**
     * 修改界面提交
     * @param
     * @return
     */
    @PutMapping("/add")
    public String updateData(Data data){
        dataDao.save(data);
        return "redirect:/datas";
    }

    /**
     * 按钮删除
     * @param id
     * @return
     */
    @DeleteMapping("/data/{id}")
    public String deleteData(@PathVariable("id") Integer id){
        dataDao.delete(id);
        return "redirect:/datas";
    }








}
