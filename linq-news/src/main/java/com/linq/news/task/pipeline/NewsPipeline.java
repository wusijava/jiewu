package com.linq.news.task.pipeline;

import com.linq.common.constant.UserConstants;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.service.LinqNewsService;
import com.linq.news.task.config.rabbitmq.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private LinqNewsService newsService;


    @Override
    public void process(ResultItems resultItems, Task task) {
        // 获取封装好的news
        LinqNews peNews = resultItems.get("peNews");
        LinqNews entertainmentNews = resultItems.get("entertainmentNews");
        LinqNews csdnNews = resultItems.get("csdnNews");
        Map<String, Object> newsMap = new HashMap<>(2);
        if (StringUtils.isNotNull(peNews) && StringUtils.isNotEmpty(peNews.getNewsContent())) { // 内容都不能为空
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(peNews))) {
                newsMap.put("peNews", peNews);
            }
        } else if (StringUtils.isNotNull(entertainmentNews) && StringUtils.isNotEmpty(entertainmentNews.getNewsContent())) {
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(entertainmentNews))) {
                newsMap.put("entertainmentNews", entertainmentNews);
            }
        } else if (StringUtils.isNotNull(csdnNews) && StringUtils.isNotEmpty(csdnNews.getNewsContent())) {
            if (UserConstants.UNIQUE.equals(newsService.checkNewsTitleUnique(csdnNews))) {
                newsMap.put("csdnNews", csdnNews);
            }
        }
        // 放入消息队列
        rabbitTemplate.convertAndSend("", RabbitConfig.NEWS_QUEUE, newsMap, message -> {
            log.info("发送消息时间---->>>>{}", System.currentTimeMillis());
            // 设置超时时间  毫秒
            message.getMessageProperties().setExpiration((5 * 60 * 1000) + "");
            return message;
        });
    }

}
