package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessageSimpleQueue(){
        String queueName="simple_queue";
        String message="hello,spring amqp";

        //循环发送50条消息
        for(int i=0;i<50;i++){
            rabbitTemplate.convertAndSend(queueName,message);
        }

    }
    //workQueue工作队列
    @Test
    public void testSendMessageWorkQueue() throws InterruptedException {
        String queueName="work_queue";
        String message="hello,spring amqp__";

        //循环发送50条消息
        for(int i=0;i<50;i++){
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }
    }

    //fanoutExchange交换机
    @Test
    public void testSendFanoutExchange(){
        String exchangeName="itcast_fanout";

        String msg="hello,fanoutExchange";

        rabbitTemplate.convertAndSend(exchangeName,"",msg);
    }

    //directExchange交换机
    @Test
    public void testSendDirectExchange1(){
        String exchangeName="itcast_direct";

        String msg="hello,blue";

        rabbitTemplate.convertAndSend(exchangeName,"blue",msg);

    }

    @Test
    public void testSendDirectExchange2(){
        String exchangeName="itcast_direct";

        String msg="hello,blue";

        rabbitTemplate.convertAndSend(exchangeName,"red",msg);
    }

    //topicExchange交换机
    @Test
    public void testSendTopicExchange1(){
        String exchangeName="itcast_topic";

        String msg="hello,china.news";

        rabbitTemplate.convertAndSend(exchangeName,"china.news",msg);
    }

    @Test
    public void testSendTopicExchange2(){
        String exchangeName="itcast_topic";

        String msg="hello,china.weather";

        rabbitTemplate.convertAndSend(exchangeName,"china.weather",msg);
    }

    //发送消息序列化
    @Test
    public void testSendObject(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","刘焉");
        map.put("age","57");
        rabbitTemplate.convertAndSend("object_queue",map);
    }
}
