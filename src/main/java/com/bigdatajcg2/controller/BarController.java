package com.bigdatajcg2.controller;


import com.bigdatajcg2.dao.*;
import com.bigdatajcg2.entities.Data;
import com.bigdatajcg2.entities.Site;
import com.bigdatajcg2.entities.Student;
import com.bigdatajcg2.entities.Time;
import com.tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

//侧边框
@Controller
public class BarController {
    @Autowired
    DataDao dataDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    SiteDao siteDao;
    @Autowired
    TimeDao timeDao;

    //起始页面管理

    /**
     * 首跳到列表页面
     */
    @GetMapping("/first")
    public String firstPage() {
        //跳到dashboard页面
        return "dashboard";
    }
    /**
     * 退出当前页面
     */
    @GetMapping("/signOut")
    public String signOutPage() {
        return "first";
    }
    /**
     * 数模统计
     */
    @GetMapping("/shumo")
    public String mainPage() {
        return "dashboard";
    }
    /**
     * 历史记录
     */
    @GetMapping("/datas")
    public String list(Model model) {
        //获取到所有的学生信息
        Collection<Data> datas = dataDao.getAll();
        model.addAttribute("shujus", datas);
        // thymeleaf默认就会拼串
        // classpath:/temlates/xxx.html
        // classpath:/temlates/data/list.html
        return "data/list";
    }
    /**
     * 信息添加
     */
    @GetMapping("/addpage")
    public String toAddPage() {
        return "data/add";
    }

    /**
     * 将设置规则的数据 存入到hdfs上面或者txt文档
     * 存入数据
     * 成功随机数
     * (int) (Math.random() * siteList.size())
     */
    @GetMapping("/save")
    public String toSavePage() {
//        //标题
//        String title = "姓名\t学号\t性别\t访问网站";
//        //标题写入到文档
//        dataDao.fileWriting(title);
        String suiji = "随机";
        //获取到所有的data  datas2是放到txt里面的内容。
        Collection<Data> datas = dataDao.getAll();
        //遍历datas
        for (Data data : datas) {
            //保存一个临时数据
            Data tempData = new Data(data.getId(),
                    new Student(data.getStudent().getId(),data.getStudent().getName(),data.getStudent().getSex(),data.getStudent().getSno()),
                    new Site(data.getSite().getId(),data.getSite().getWeb()),
                    new Time(data.getTime().getId(),data.getTime().getDate()),
                    data.getNum());
            //num为1
            if(tempData.getNum() == 1) {
                //包含随机数
                if (tempData.getStudent().getName().equals(suiji) || tempData.getSite().getWeb().equals(suiji) || tempData.getTime().getDate().equals(suiji)) {
                    //student
                    if (data.getStudent().getName().equals(suiji)) {
                        data.setStudent(studentDao.randStudent());
                    }
                    //Site
                    if (data.getSite().getWeb().equals(suiji)) {
                        data.setSite(siteDao.randSite());
                    }
                    //Time
                    if (data.getTime().getDate().equals(suiji)){
                        data.setTime(timeDao.randTime());
                    }
                    //num
                    data.setNum(1);
                    //输出
                    //写入
                    dataDao.fileWriting(data.toString());
                }
                //如果不包含随机数
                else {
                    dataDao.fileWriting(data.toString());
                }
            }
            //如果num值大于1
            else{
                boolean flag  = true;
                //遍历num值
                for(int i = 0; i< tempData.getNum();i++){
                    //包含随机数
                    //且>1
                    if(tempData.getStudent().getName().equals(suiji) || tempData.getSite().getWeb().equals(suiji) || tempData.getTime().getDate().equals(suiji)){
                        //新建一个Data  rand
                        Data rand = new Data();
                        //id
                        rand.setId(tempData.getId()+i);
                        //student
                        if(tempData.getStudent().getName().equals(suiji)){
                            rand.setStudent(studentDao.randStudent());
                        }
                        //site
                        if(tempData.getSite().getWeb().equals(suiji)){
                            rand.setSite(siteDao.randSite());
                        }
                        //Time
                        if(tempData.getTime().getDate().equals(suiji)){
                            rand.setTime(timeDao.randTime());
                        }
                        //num
                        rand.setNum(1);
                        System.out.println("#############222+"+rand);
                        //保存
                        dataDao.save(rand);
                        //写入
                        dataDao.fileWriting(rand.toString());
                    }
                    //如果不包含随机数
                    //且>1
                    else{
                        if(i  < tempData.getNum()-1 ){
                            if(flag){
                                //num
                                data.setNum(1);
                                dataDao.fileWriting(data.toString());
                            }
                            //new data
                            Data newdata = new Data(data.getId(),
                                    new Student(data.getStudent().getId(),data.getStudent().getName(),data.getStudent().getSex(),data.getStudent().getSno()),
                                    new Site(data.getSite().getId(),data.getSite().getWeb()),
                                    new Time(data.getTime().getId(),data.getTime().getDate()),
                                    data.getNum());
                            //id值
                            newdata.setId(data.getId()+i+1);
                            //保存
                            dataDao.save(newdata);
                            //写入
                            dataDao.fileWriting(data.toString());
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        //存入数据完成， 跳转到data目录下的save.html
        return "data/save";
    }




    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi")
    public String jisuan(Model model) {
        //网站
        String temp = tools.max_Web();
        //男生
        String temp2 = tools.max_man_web();
        //女生
        String temp3 = tools.max_woman_web();
        Result result = new Result(temp,temp2,temp3);
        model.addAttribute("results",result);
        return "data/max_web";
    }

    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi2")
    public String jisuan2() {

        return "data/max_web2";
    }



    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi3")
    public String jisuan3(String tt1, String tt2,Model model) {
        System.out.println("##########"+tt1+"$$$$$$$$$$$$$$$"+tt2);
        String jieguo1 = tools.max_time_stu(tt1,tt2);
        model.addAttribute("tt",jieguo1);
        return "data/max_web2";
    }


    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi5")
    public String jisuan5() {

        return "data/max_web3";
    }

    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi6")
    public String jisuan6(String tt1, String tt2,Model model) {
        System.out.println("##########"+tt1+"$$$$$$$$$$$$$$$"+tt2);
        String jieguo1 = tools.max_time_man(tt1,tt2);
        model.addAttribute("tt",jieguo1);
        return "data/max_web3";
    }


    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi8")
    public String jisuan8() {

        return "data/max_web4";
    }

    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi9")
    public String jisuan9(String tt1, String tt2,Model model) {
        System.out.println("##########"+tt1+"$$$$$$$$$$$$$$$"+tt2);
        String jieguo1 = tools.max_time_woman(tt1,tt2);
        model.addAttribute("tt",jieguo1);
        return "data/max_web4";
    }


    /**
     * #######################################################################################
     */
    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi11")
    public String jisuan11() {

        return "data/max_web5";
    }

    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi12")
    public String jisuan12(String tt1, Model model) {
        int t1 = Integer.parseInt(tt1);
        String jieguo1 = tools._7(t1);
        model.addAttribute("tt",jieguo1);
        return "data/max_web5";
    }



    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi15")
    public String jisuan15() {

        return "data/max_web6";
    }

    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi16")
    public String jisuan16(String tt1, String tt2,Model model) {
        String jieguo1 = tools._8(tt1,tt2);

        System.out.println("########################"+tt1+"$$$$$$$$$"+tt2);
        System.out.println("jieguo1:"+jieguo1);
        model.addAttribute("tt",jieguo1);
        return "data/max_web6";
    }






    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi20")
    public String jisuan20() {

        return "data/max_web7";
    }

    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi21")
    public String jisuan21(String tt1, String tt2,Model model) {
        String jieguo1 = tools._9(tt1,tt2);

        model.addAttribute("tt",jieguo1);
        return "data/max_web7";
    }






    /**
     * 分布式计算结果
     */
    @GetMapping("/fenbushi30")
    public String jisuan30() {

        return "data/max_web8";
    }

    /**
     * 分布式计算结果
     */
    @PostMapping("/fenbushi31")
    public String jisuan31(String tt1, String tt2,Model model) {
        String jieguo1 = tools._10(tt1,tt2);

        model.addAttribute("tt",jieguo1);
        return "data/max_web8";
    }


}

