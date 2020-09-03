package com.linq.news.task.listener;

import com.linq.common.exception.CustomException;
import com.linq.common.utils.string.StringUtils;
import com.linq.news.domain.LinqNews;
import com.linq.news.service.LinqNewsService;
import com.linq.news.service.NewsDocumentService;
import com.linq.news.task.config.rabbitmq.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: 林义清
 * @Date: 2020/9/1 9:52 下午
 * @Description: 消息消费
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class NewsConsumer {
    @Autowired
    private LinqNewsService newsService;
    @Autowired
    private NewsDocumentService newsDocumentService;

    //处理数据业务方法 方法参数跟生产者发送数据类型一致
    @RabbitListener(queues = RabbitConfig.NEWS_QUEUE) //指定监听哪个队列
    public void receiveNews(Message<HashMap<String, Object>> message, Channel channel) throws IOException {
        HashMap<String, Object> newsMap = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            log.info("接收到消息的时间----->>> {}\n消费tag->>> {}\n从消息队列中拿到数据----------->>>> {}"
                    , System.currentTimeMillis(), tag, newsMap);
            LinqNews peNews = (LinqNews) newsMap.get("peNews");
            LinqNews entertainmentNews = (LinqNews) newsMap.get("entertainmentNews");
            LinqNews csdnNews = (LinqNews) newsMap.get("csdnNews");
            log.info("获取peNews--->>>{}", peNews);
            log.info("获取entertainmentNews--->>>{}", entertainmentNews);
            log.info("获取csdnNews--->>>{}", csdnNews);
            // 分别保存到全局检索 和 数据库
            if (StringUtils.isNotNull(peNews)) {
                newsService.insertLinqNews(peNews);
                newsDocumentService.saveNews(peNews);
            } else if (StringUtils.isNotNull(entertainmentNews)) {
                newsService.insertLinqNews(entertainmentNews);
                newsDocumentService.saveNews(entertainmentNews);
            } else if (StringUtils.isNotNull(csdnNews)) {
                newsService.insertLinqNews(csdnNews);
                newsDocumentService.saveNews(csdnNews);
            }
            //确认消息已消费
            channel.basicAck(tag, false);
        } catch (Exception e) {
            /**
             * TOOD 这里可能有个问题就是当抛出异常 是标题重复造成的话 就会会到消息队列一直等待消费 这里 我们的特殊处理一下手动直接消费---->已处理
             */
            if (e.getMessage().contains("新闻标题已存在")) {
                log.info("因为新闻标题重复,直接消费---->{}", tag);
                //确认消息已消费
                channel.basicAck(tag, false);
                return;
            }
            channel.basicNack(tag, false, true);
            throw new CustomException("消息队列消费异常" + e.getMessage());
        }
    }
}
