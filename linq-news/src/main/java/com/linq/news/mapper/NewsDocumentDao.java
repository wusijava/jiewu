package com.linq.news.mapper;

import com.linq.news.domain.NewsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: 林义清
 * @Date: 2020/9/2 3:54 下午
 * @Description:
 * @Version: 1.0.0
 */
public interface NewsDocumentDao extends ElasticsearchRepository<NewsDocument, Long> {
}
