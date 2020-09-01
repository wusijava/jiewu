package com.linq.news.task.pipeline;

import com.linq.common.constant.UserConstants;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.service.LinqNewsService;
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
@Component
public class NewsPipeline implements Pipeline {
    @Autowired
    private LinqNewsService newsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        // 获取封装好的news
        LinqNews news = resultItems.get("peNews");
        System.err.println("获取封装好的news->" + news);
        if (StringUtils.isNotNull(news)) {
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(news))) {
                //如果不为空把数据保存到数据库中
                newsService.insertLinqNews(news);
            }
        }
    }
}
