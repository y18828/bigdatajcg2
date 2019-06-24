package com.bigdatajcg2.dao;


import com.bigdatajcg2.entities.Site;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SiteDao {



    private static Map<Integer, Site> sites = null;

    static{
        sites = new HashMap<Integer,Site>();
        sites.put(0,new Site(0,"baidu.com"));
        sites.put(1,new Site(1,"renren.com"));
        sites.put(2,new Site(2,"jincheng.com"));
        sites.put(3,new Site(3,"souguo.com"));
        sites.put(4,new Site(4,"sunning.com"));
    }

    private static Integer initSiteId = 5;

    public void save(Site site){
        //如果Id为null
        if(site.getId() == null){
            site.setId(initSiteId++);
        }
        //把当前的数据内容添加进去
        sites.put(site.getId(),site);
    }

    //获取所有的sites
    public Collection<Site> getAll(){return sites.values();}

    //通过id获取内容
    public Site getSite(Integer id){
        return sites.get(id);
    }

    //通过id去更新当前实例化对象内容
    public void update(Site site){
        //获取id
        int id = site.getId();
        //当前没修改前的对象 重新赋值
        getSite(id).setWeb(site.getWeb());
    }

    //返回一个随机的Site
    public Site randSite(){
        Integer ranNum = (int) (Math.random() * 5);
        return sites.get(ranNum);
    }


}
