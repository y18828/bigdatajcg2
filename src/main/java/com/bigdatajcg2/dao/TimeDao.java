package com.bigdatajcg2.dao;


import com.bigdatajcg2.entities.Time;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TimeDao {
    private static Map<Integer, Time> times = null;

    /**
     * "5-2 12:30"
     * "1-1 11:01"
     * "2-2 2:20"
     * "3-3 3:30"
     * "4-4 4:40"
     */
    static{
        times = new HashMap<Integer,Time>();

        times.put(0,new Time(0,"5/2 12:30:20"));
        times.put(1,new Time(1,"1/1 11:01:30"));
        times.put(2,new Time(2,"2/2 2:20:40"));
        times.put(3,new Time(3,"3/3 3:30:50"));
        times.put(4,new Time(4,"4/4 3:30:55"));

    }
    private static Integer initTimeId = 5;
    public void save(Time time){
        //如果Id为null
        if(time.getId() == null){
            time.setId(initTimeId++);
        }
        //把当前的数据内容添加进去
        times.put(time.getId(),time);
    }
    //获取所有的sites
    public Collection<Time> getAll(){return times.values();}
    //通过id获取内容
    public Time getTime(Integer id){
        return times.get(id);
    }
    //通过id去更新当前实例化对象内容
    public void update(Time time){
        //获取id
        int id = time.getId();
        //当前没修改前的对象 重新赋值
        getTime(id).setDate(time.getDate());
    }

    //返回一个随机的Time
    public Time randTime(){
        Integer ranNum = (int) (Math.random() * 5);
        return times.get(ranNum);
    }
}
