package com.originfriend.base.dao;
import com.originfriend.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lxy
 * @date 2019/9/6 - 10:57
 */
public interface LabelDAO extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {
}
