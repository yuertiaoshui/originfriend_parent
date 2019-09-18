package com.originfriend.article.dao;

import com.originfriend.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author lxy
 * @date 2019/9/12 - 10:12
 */
public interface ArticleDao
        extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article> {
    //文章审核
    @Modifying
    @Query(value = "update tb_article set state=1 where id=?",nativeQuery = true)
    public void updateState(String id);

    //文章点赞
    @Modifying
    @Query(value = "update tb_article set thumbup=thumbup+1 where id=?",nativeQuery = true)
    public void addThumbup(String id);
}
