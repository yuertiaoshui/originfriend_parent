package com.originfriend.base.controller;

import com.originfriend.base.pojo.Label;
import com.originfriend.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lxy
 * @date 2019/9/6 - 12:12
 */
@RestController //ResponseBody+Controller的结合,每一个Controller方法返回的数据都是JSON
@CrossOrigin //微服务: 跨域请求
@RequestMapping("/label")
public class LabelController {
    @Autowired //植入Service层实例
    LabelService labelService;

    //01. 添加标签
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true, StatusCode.OK,"添加标签成功");
    }

    //02. 修改标签
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"修改标签成功");
    }

    //03. 删除标签
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除标签成功");
    }

    //04. 查询所有
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> list = labelService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    //05. 根据主键查询单个标签
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){
        Label label = labelService.findById(labelId);
        return new Result(true, StatusCode.OK,"查询成功",label);
    }

    //06. 模糊查询
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    //07.分页查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@PathVariable int page,@PathVariable int size,@RequestBody Label label){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        System.out.println(pageData);
        return new Result(true, StatusCode.OK,"查询成功",
                new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }

    /*//07.分页查询(不带条件)
    @RequestMapping(value = "/searchAll/{page}/{size}",method = RequestMethod.POST)
    public Result findSearchAll(@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.pageQueryAll(page,size);
        System.out.println(pageData);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }*/

}
