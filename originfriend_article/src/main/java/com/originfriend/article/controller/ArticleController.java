package com.originfriend.article.controller;

import com.originfriend.article.pojo.Article;
import com.originfriend.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxy
 * @date 2019/9/12 - 11:09
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    //审核文章
    @RequestMapping(value = "/examine/{articleId}",method = RequestMethod.PUT)
    public Result examine(@PathVariable String articleId){
        articleService.updateState(articleId);
        return new Result(true, StatusCode.OK,"审核成功");
    }

    //点赞
    @RequestMapping(value = "/thumbup/{articleId}",method =RequestMethod.PUT)
    public Result thumbup(@PathVariable String articleId){
        articleService.addThumbup(articleId);
        return new Result(true, StatusCode.OK,"点赞成功");
    }


    @RequestMapping(value = "/{articleId}",method =RequestMethod.GET)
    public Result findById(@PathVariable String articleId){
        Article article = articleService.findById(articleId);
        return new Result(true,StatusCode.OK,"查询文章对象成功",article);
    }

    @RequestMapping(value = "/{articleId}",method =RequestMethod.PUT)
    public Result update(@PathVariable String articleId, @RequestBody Article article){
        article.setId(articleId);
        articleService.update(article);
        return new Result(true,StatusCode.OK,"修改文章对象成功");
    }

    @RequestMapping(value = "/{articleId}",method =RequestMethod.DELETE)
    public Result deleteById(@PathVariable String articleId){
        articleService.deleteById(articleId);
        return new Result(true,StatusCode.OK,"删除文章对象成功");
    }



}
