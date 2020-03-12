package com.dhq.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhq.entity.News;
import com.dhq.entity.NewsBack;
import com.dhq.service.NewsBackService;
import com.dhq.service.NewsService;
import com.dhq.utils.RestResult;
import com.dhq.utils.crypt.SM2Utils;
import com.dhq.utils.crypt.Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dhq
 * @since 2020-03-05
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsBackService backService;


    //公钥
    private static final String pubk = "04D83206821A558EE3FF21C16CD7C20FB57532278F7347EA057B75C83CD13293C3AAE7813A2AAC86ED2B963A4CC1F5529C9F643AB62068D4DFFE58E09A902612A8";

    @GetMapping("/list")
    public List<News> list(){
        QueryWrapper<News> queryWrapper=new QueryWrapper<>();
        List<News> list = newsService.list(queryWrapper);
        return list;
    }

    @GetMapping("/add")
    public RestResult add(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        News news = getNews(uuid);
        newsService.save(news);
        return RestResult.warperMsgOk("新增成功",news);
    }


    //更新后插入防篡改备份表
    @PostMapping("/update")
    public RestResult update(@RequestBody News news) throws IOException {
        String uuid = news.getId();//主键值
        //传递过来的实体信息 news
        if(StringUtils.isEmpty(uuid)){
            return RestResult.error("主键值没有传递过来");
        }
        newsService.updateById(news);

        /**
         * 1、根据表名+字段名 查找防篡改表中是否有数据，有的话就修改，没有就新建
         * 2、重新将 字段对应的 值 加密
         * 3、保存更新 防篡改表
         */
        QueryWrapper<NewsBack> backQueryWrapper=new QueryWrapper<>();
        backQueryWrapper.eq("tablename","t_news").eq("businessid",news.getId())
                .eq("fieldname","title");
        NewsBack newsBack = backService.getOne(backQueryWrapper);
        if(newsBack==null){
            newsBack=new NewsBack();
            String backUuid = UUID.randomUUID().toString().replaceAll("-", "");
            newsBack.setId(backUuid);
        }
        newsBack.setCreatetime(LocalDateTime.now());
        newsBack.setTablename("t_news");
        newsBack.setFieldname("title");
        newsBack.setBusinessid(news.getId());
        String fieldvalue=news.getTitle();
        String cipherText = SM2Utils.encrypt(Util.hexToByte(pubk), fieldvalue.getBytes());
        newsBack.setEncryptvalue(cipherText);
        backService.saveOrUpdate(newsBack);

        return RestResult.warperOk("保存成功");
    }

    public News getNews(String uuid){
        News news = new News();
        news.setId(uuid);
        news.setTitle("新冠肺炎情况通报");
        news.setCreateuser("张三");
        news.setRemark("尽快清除病毒");
        return news;
    }

    public Map<String ,String> mappingToMap(News news){
        Map<String ,String> map=new HashMap<>();
        map.put("id",news.getId());
        map.put("title",news.getTitle());
        map.put("createuser",news.getCreateuser());
        map.put("remark",news.getRemark());
        return map;
    }

}

