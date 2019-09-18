package com.originfriend.recruit.dao;

import com.originfriend.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author lxy
 * @date 2019/9/10 - 12:17
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise> {
    public List<Enterprise> findByIshot(String ishot);
}
