package com.originfriend.qa.service;

import com.originfriend.qa.dao.ProblemDao;
import com.originfriend.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @author lxy
 * @date 2019/9/11 - 10:56
 */
@Service
public class ProblemSerivce {
    @Autowired
    ProblemDao problemDao;
    @Autowired
    IdWorker idWorker;

    /**
     * 最新回答
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    public Page<Problem> newList(String labelid, int page,int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return problemDao.newList(labelid,pageable);
    }

    /**
     * 热门回答
     * @param labelid
     * @param page
     * @param rows
     * @return
     */
    public Page<Problem> hotList(String labelid, int page,int rows){
        Pageable pageable=PageRequest.of(page-1,rows);
        return problemDao.hotList(labelid,pageable);
    }

    /**
     * 等待回答
     * @param labelid
     * @param page
     * @param rows
     * @return
     */
    public Page<Problem> waitList(String labelid, int page,int rows){
        Pageable pageable=PageRequest.of(page-1,rows);
        return problemDao.waitList(labelid,pageable);
    }

}
