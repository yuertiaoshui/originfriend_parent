package com.originfriend.qa.dao;

import com.originfriend.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author lxy
 * @date 2019/9/11 - 10:51
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    //最新回答
    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid=? order BY replytime desc",nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    //热门回答
    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid=? order BY reply desc",nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);

    //等待回答
    @Query(value = "select * from tb_problem,tb_pl where id=problemid and labelid=? and reply=0 order BY createtime desc",nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);
}
