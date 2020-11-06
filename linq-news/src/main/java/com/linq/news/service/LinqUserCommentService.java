package com.linq.news.service;

import com.linq.news.domain.LinqUserComment;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @Author: 林义清
 * @Date: 2020/8/28 8:16 下午
 * @Description: 
 * @Version: 1.0.0 
 */
    
public interface LinqUserCommentService extends IService<LinqUserComment>{

    /**
     * 新增新闻与用户关系
     * @param linqUserComment
     * @return
     */
    boolean insertLinqUserComment(LinqUserComment linqUserComment);

}
