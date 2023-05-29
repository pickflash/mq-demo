package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
