package com.linq.file.assistant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linq.file.assistant.domain.UserFile;
import org.apache.ibatis.annotations.Param;

public interface UserFileMapper extends BaseMapper<UserFile> {

    /**
     * 根据用户id查询文档列表
     *
     * @param file  文档
     * @param userId 用户id
     *
     * @return 新闻集合
     */
    IPage<UserFile> findPageByUserId(Page<UserFile> userFilePage, @Param("file") UserFile file, @Param("userId") Long userId);
}
