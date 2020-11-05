package com.linq.file.assistant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linq.file.assistant.domain.UserFile;
import com.linq.file.assistant.mapper.UserFileMapper;
import com.linq.file.assistant.service.FileAssistantService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: vue-news
 * @description:
 * @author: Mr.Wang
 * @create: 2020-11-05 17:48
 **/

@Service
public class FileAssistantServiceImpl  extends ServiceImpl<UserFileMapper, UserFile> implements FileAssistantService {



    @Override
    public IPage<UserFile> findPageAll(Page<UserFile> userFilePage, UserFile file) {
        return null;
    }

    @Override
    public UserFile findFileById(Long fileId) {
        return baseMapper.selectById(fileId);
    }

    @Override
    public boolean insertFile(UserFile userFile) {
        return save(userFile);
    }

    @Override
    public boolean updateFile(UserFile userFile) {
        return saveOrUpdate(userFile);
    }

    @Override
    public boolean deleteFileByIds(List<Long> fileIds) {
        return removeByIds(fileIds);
    }

    @Override
    public boolean changeStatus(Long fileId,String statusCode) {
        return false;
    }
}
