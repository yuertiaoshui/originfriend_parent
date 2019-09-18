package com.originfriend.base.service;

import com.originfriend.base.dao.LabelDAO;
import com.originfriend.base.pojo.Label;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.Expression;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import util.IdWorker;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxy
 * @date 2019/9/6 - 10:59
 */
@Transactional()
@Service
//将service公开成spring中的一个bean
public class LabelService {
    @Autowired
    LabelDAO labelDAO;
    @Autowired
    private IdWorker idWorker;

    //01. 添加标签
    public void add(Label label){
        label.setId(idWorker.nextId()+"");
        labelDAO.save(label);
    }

    //02. 修改标签
    public void update(Label label){
        labelDAO.save(label);
    }

    //03. 删除标签
    public void deleteById(String labelId){
        labelDAO.deleteById(labelId);
    }

    //04. 查询所有
    public List<Label> findAll(){
        return labelDAO.findAll();
    }

    //05. 根据主键查询单个对象
    public Label findById(String labelId){
        return labelDAO.findById(labelId).get();
    }

    //06. 模糊查询
    public List<Label> findSearch(Label label){
        return labelDAO.findAll(new Specification<Label>() { //接口实现类
            @Nullable
            @Override
            /**
             * root:当前对象
             * criteriaQuery:Criteria查询执行器
             * criteriaBuilder:构建器
             */
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //模糊查询  labelname ,state
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class),
                            "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class),
                            "%" + label.getState() + "%");
                    list.add(predicate);
                }
                //集合转数组
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        });
    }

    //07. 分页查询
    /**
     * @param label: 实体类型
     * @param page: 当前页面
     * @param size: 每页数据量
     * @return
     */
    public Page<Label> pageQuery(Label label,int page,int size){
        //页码从0开始，底层Hibernate框架约定好的
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDAO.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<Predicate>();
                if (label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (label.getState()!=null&&!"".equals(label.getState())){
                    Predicate  predicate = criteriaBuilder.like(root.get("state").as(String.class), "%" + label.getState() + "%");
                    list.add(predicate);
                }
                //如下集合转数组代码
                Predicate[] predicates=new Predicate[list.size()];
                predicates= list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        },pageable);

    }

    /*//07. 分页查询(不带条件)
    public Page<Label> pageQueryAll(int page, int size){
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDAO.findAll(pageable);
    }*/

}
