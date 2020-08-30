package com.linq.news.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.news.mapper.LinqUserCommentMapper;
import com.linq.news.domain.LinqUserComment;
import com.linq.news.service.LinqUserCommentService;
/**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
@Service
public class LinqUserCommentServiceImpl extends ServiceImpl<LinqUserCommentMapper, LinqUserComment> implements LinqUserCommentService{

}
