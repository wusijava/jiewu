package com.linq.news.task.pipeline;

import com.linq.common.constant.UserConstants;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.service.LinqNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: 林义清
 * @Date: 2020/8/31 8:35 下午
 * @Description: WebMagic用于保存结果的组件叫做Pipeline。
 * 我们现在通过“控制台输出结果”这件事也是通过一个内置的Pipeline完成的，它叫做ConsolePipelin
 * @Version: 1.0.0
 */
@Slf4j
@Component
public class NewsPipeline implements Pipeline {
    @Autowired
    private LinqNewsService newsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // 获取封装好的news
        LinqNews peNews = resultItems.get("peNews");
        LinqNews entertainmentNews = resultItems.get("entertainmentNews");
        LinqNews csdnNews = resultItems.get("csdnNews");
        log.info("获取封装好的news->>>>>>>>{}", peNews);
        if (StringUtils.isNotNull(peNews)) {
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(peNews))) {
                //如果不为空把数据保存到数据库中
                newsService.insertLinqNews(peNews);
            }
        }else if (StringUtils.isNotNull(entertainmentNews)){
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(entertainmentNews))) {
                //如果不为空把数据保存到数据库中
                newsService.insertLinqNews(entertainmentNews);
            }
        }else if (StringUtils.isNotNull(csdnNews)){
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(csdnNews))) {
                //如果不为空把数据保存到数据库中
                newsService.insertLinqNews(csdnNews);
            }
        }
    }
}
