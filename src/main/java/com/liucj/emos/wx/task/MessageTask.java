package com.liucj.emos.wx.task;

import com.liucj.emos.wx.db.pojo.MessageEntity;
import com.liucj.emos.wx.db.pojo.MessageRefEntity;
import com.liucj.emos.wx.exception.EmosException;
import com.liucj.emos.wx.service.MessageService;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息任务
 */
@Component
@Slf4j
public class MessageTask {

    @Autowired
    private ConnectionFactory factory;

    @Autowired
    private MessageService messageService;

    /**
     * 发送消息
     *
     * @param topic
     * @param entity
     */
    public void send(String topic, MessageEntity entity) {
        String id = messageService.insertMessage(entity);
        //向MQ发送消息
        try (Connection connection = factory.newConnection();//创建连接
             Channel channel = connection.createChannel();//创建通道
        ) {
            channel.queueDeclare(topic, true, false, false, null);
            HashMap map = new HashMap();
            map.put("messageId", id);
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().headers(map).build();
            channel.basicPublish("", topic, properties, entity.getMsg().getBytes());
            log.debug("消息发送成功");
        } catch (Exception e) {
            log.error("执行异常", e);
            throw new EmosException("向MQ发送消息失败");
        }
    }

    @Async
    public void sendAsync(String topic, MessageEntity entity) {
        send(topic, entity);
    }

    /**
     * 接收消息
     *
     * @param topic
     * @return
     */
    public int receive(String topic) {
        //从MQ接收消息
        int i = 0;
        try (Connection connection = factory.newConnection();//创建连接
             Channel channel = connection.createChannel();//创建通道
        ) {
            channel.queueDeclare(topic, true, false, false, null);
            while (true) {
                GetResponse response = channel.basicGet(topic, false);
                if (response != null) {
                    //提取数据
                    AMQP.BasicProperties properties = response.getProps();
                    Map<String, Object> map = properties.getHeaders();
                    String messageId = map.get("messageId").toString();
                    //消息正文
                    byte[] body = response.getBody();
                    String message = new String(body);
                    log.debug("从RabbitMQ接收的消息：" + message);

                    MessageRefEntity entity = new MessageRefEntity();
                    entity.setMessageId(messageId);
                    entity.setReceiverId(Integer.parseInt(topic));
                    entity.setReadFlag(false);
                    entity.setLastFlag(true);
                    messageService.insertRef(entity);
                    long deliveryTag = response.getEnvelope().getDeliveryTag();
                    channel.basicAck(deliveryTag, false);//应答，表示接收到消息
                    i++;
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            log.error("执行异常", e);
            throw new EmosException("从MQ接收消息失败");
        }
        return i;
    }

    @Async
    public int receiveAsync(String topic) {
        return receive(topic);
    }

    /**
     * 删除消息队列
     *
     * @param topic
     */
    public void deleteQueue(String topic) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.queueDelete(topic);
            log.debug("消息队列成功删除");
        } catch (Exception e) {
            log.error("删除队列失败", e);
            throw new EmosException("删除队列失败");
        }
    }


    @Async
    public void deleteQueueAsync(String topic) {
        deleteQueue(topic);
    }

}
