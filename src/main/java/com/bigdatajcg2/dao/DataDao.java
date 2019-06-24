package com.bigdatajcg2.dao;


import com.bigdatajcg2.entities.Data;
import com.bigdatajcg2.entities.Site;
import com.bigdatajcg2.entities.Student;
import com.bigdatajcg2.entities.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DataDao {


    @Autowired
    private SiteDao siteDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TimeDao timeDao;


    private static Map<Integer, Data> datas = null;

    static{
        datas = new HashMap<Integer, Data>();
        datas.put(0,new Data(0,new Student(0,"权志龙","男",101),new Site(0,"baidu.com"),new Time(0,"5/2 12:30:20"),1));
        datas.put(1,new Data(1,new Student(1,"刘亦菲","女",102),new Site(1,"renren.com"),new Time(1,"1/1 11:01:30"),1));
        datas.put(2,new Data(2,new Student(2,"刘涛","女",103),new Site(2,"jincheng.com"),new Time(2,"2/2 2:20:40"),1));
        datas.put(3,new Data(3,new Student(3,"李易峰","男",104),new Site(3,"souguo.com"),new Time(3,"3/3 3:30:50"),1));
        datas.put(4,new Data(4,new Student(4,"周星驰","男",105),new Site(4,"sunning.com"),new Time(4,"4/4 3:30:55"),1));
    }

    private static Integer initDataId = 5;



    //保存信息内容
    public void save(Data data){
        //如果是添加信息的话  一开始的id为null
        if(data.getId() == null){
            int templen = data.getNum();
            data.setId(initDataId);
            initDataId = initDataId + templen;
        }
        //先把添加的数据放到对应的类里面去 先添加id  然后把内容传入进去
        studentDao.save(data.getStudent());
        siteDao.save(data.getSite());
        timeDao.save(data.getTime());
        //data里面设置内容
        data.setStudent(studentDao.getStudent(data.getStudent().getId()));
        data.setSite(siteDao.getSite(data.getSite().getId()));
        data.setTime(timeDao.getTime(data.getTime().getId()));
        //将数据内容存放到datas map中
        datas.put(data.getId(),data);
    }

    //查询所有数据
    public Collection<Data> getAll(){
        return datas.values();
    }
    //通过id查询内容结果
    public Data get(Integer id){
        return datas.get(id);
    }
    //删除当前id
    public void delete(Integer id){
        datas.remove(id);
    }

    /**
     * 文件追加功能
     * @param data
     */
    public void fileWriting(String data){
        try{
            File file =new File("data.txt");
            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }
            //true = append file
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            //添加输入的数据就换行
            fileWritter.write(data+"\n");
            fileWritter.close();
            System.out.println("文件写入成功");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
