package com.originfriend.article.service;

import com.originfriend.article.dao.ArticleDao;
import com.originfriend.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author lxy
 * @date 2019/9/12 - 10:16
 */
@Transactional
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 文章审核
     * @param id
     */
    public void updateState(String id){
        articleDao.updateState(id);
    }

    /**
     * 文章点赞
     * @param id
     */
    public void addThumbup(String id){
        articleDao.addThumbup(id);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    public Article findById(String id){
        //先从缓存中查询当前对象
        Article article= (Article) redisTemplate.opsForValue().get("article_"+id);
        //如果没有取到，从数据库中查询
        if(article == null){
            article=articleDao.findById(id).get();
            //存入到缓存中
            redisTemplate.opsForValue().set("article_"+id,article,10, TimeUnit.SECONDS);
        }
        return article;
    }


    /**
     * 修改
     * @param article
     */
    public void update(Article article) {
        redisTemplate.delete("article_"+article.getId());
        articleDao.save(article);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        redisTemplate.delete("article_"+id);
        articleDao.deleteById(id);
    }


}
