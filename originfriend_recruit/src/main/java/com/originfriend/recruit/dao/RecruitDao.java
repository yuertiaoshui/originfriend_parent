package com.originfriend.recruit.dao;

import com.originfriend.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author lxy
 * @date 2019/9/10 - 12:27
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit> {

    //推荐职位
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);
    //where state=? order by createtime
}
