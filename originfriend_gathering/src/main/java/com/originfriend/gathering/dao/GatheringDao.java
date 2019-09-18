package com.originfriend.gathering.dao;

import com.originfriend.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GatheringDao extends JpaRepository<Gathering,String>,JpaSpecificationExecutor<Gathering> {
   
}