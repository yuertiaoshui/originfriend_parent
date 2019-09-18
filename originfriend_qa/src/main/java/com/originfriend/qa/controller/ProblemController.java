package com.originfriend.qa.controller;

import com.originfriend.qa.pojo.Problem;
import com.originfriend.qa.service.ProblemSerivce;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author lxy
 * @date 2019/9/11 - 11:00
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    ProblemSerivce problemSerivce;

    /**
     * 最新回答
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/newlist/{label}/{page}/{size}",method = RequestMethod.GET)
    public Result newList(@PathVariable("label") String labelid, @PathVariable int page, @PathVariable int size){
        Page<Problem> problems = problemSerivce.newList(labelid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Problem>(problems.getTotalElements(),problems.getContent()));
    }

    /**
     * 热门回答
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/hotlist/{label}/{page}/{size}",method = RequestMethod.GET)
    public Result hotList(@PathVariable("label") String labelid, @PathVariable int page, @PathVariable int size){
        Page<Problem> problems = problemSerivce.hotList(labelid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Problem>(problems.getTotalElements(),problems.getContent()));
    }

    /**
     * 等待回答
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/waitlist/{label}/{page}/{size}",method = RequestMethod.GET)
    public Result waitList(@PathVariable("label") String labelid, @PathVariable int page, @PathVariable int size){
        Page<Problem> problems = problemSerivce.waitList(labelid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Problem>(problems.getTotalElements(),problems.getContent()));
    }

}
