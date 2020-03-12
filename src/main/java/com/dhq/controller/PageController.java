package com.dhq.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhq.entity.News;
import com.dhq.entity.NewsBack;
import com.dhq.service.NewsBackService;
import com.dhq.service.NewsService;
import com.dhq.utils.crypt.SM2Utils;
import com.dhq.utils.crypt.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class PageController {
    //秘钥
    private static final String prik = "2B9160F024F2BF577D32DB9E1190EDEDC39BF097C4027A5A43CAE0C49E009F25";

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsBackService backService;
    //打开生成二维码页面。
    @GetMapping(value = "/index")
    public String index(){
        return "index.html";
    }

    @GetMapping(value = "/edit")
    public String edit(ModelMap modelMap) throws IOException {
        String uuid="6fbd9f80f9a74bffa2085f5a538bfbf6";
        News news = newsService.getById(uuid);
        modelMap.addAttribute("entity",news);

        QueryWrapper<NewsBack> backQueryWrapper=new QueryWrapper<>();
        backQueryWrapper.eq("tablename","t_news").eq("businessid",news.getId())
                .eq("fieldname","title");
        NewsBack newsBack = backService.getOne(backQueryWrapper);
        if(newsBack!=null){
            String cipherText=newsBack.getEncryptvalue();
            String plainText = new String(SM2Utils.decrypt(Util.hexToByte(prik), Util.hexToByte(cipherText)));

            modelMap.addAttribute("flag",(plainText.equals(news.getTitle())));
            modelMap.addAttribute("old",plainText);
        }

        return "edit.html";
    }
}
