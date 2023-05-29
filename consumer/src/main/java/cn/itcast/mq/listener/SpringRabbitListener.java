package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class SpringRabbitListener {
    /*@RabbitListener(queues = "simple_queue")
    public void listenerSimpleQueue(String msg){
        System.out.println("接收到的消息simple_queue:["+msg+"]");
    }*/

    //WorkQueue
    @RabbitListener(queues = "simple_queue")
    public void listenerSimpleQueue1(String msg) throws InterruptedException {
        System.out.println("消费者11111111接收到的消息simple_queue:["+msg+"]"+ LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple_queue")
    public void listenerSimpleQueue2(String msg) throws InterruptedException {
        System.out.println("消费者22222222接收到的消息simple_queue:["+msg+"]"+LocalTime.now());
        Thread.sleep(200);
    }
    //运行结果显示，50条消息平均分配给了两个consumer----->消息预取机制，如何防止：在yml文件中设置listener.simple.prefetch: 1


    //FanoutQueue
    @RabbitListener(queues = "fanout_queue1")
    public void listenerFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("消费者fanoutQueue1接收到的消息:["+msg+"]"+ LocalTime.now());
    }

    @RabbitListener(queues = "fanout_queue2")
    public void listenerFanoutQueue2(String msg) throws InterruptedException {
        System.out.println("消费者fanoutQueue2接收到的消息:["+msg+"]"+ LocalTime.now());
    }

    //DirectExchange交换机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="direct_queue1"),
            exchange= @Exchange(name="itcast_direct",type = ExchangeTypes.DIRECT),
            key= {"red","blue"}
    ))
    public void listDirectQueue1(String msg){
        System.out.println("消费者directQueue1接收到的消息:["+msg+"]"+ LocalTime.now());
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="direct_queue2"),
            exchange= @Exchange(name="itcast_direct",type = ExchangeTypes.DIRECT),
            key= {"red","yellow"}
    ))
    public void listDirectQueue2(String msg){
        System.out.println("消费者directQueue2接收到的消息:["+msg+"]"+ LocalTime.now());
    }

    //topicExchange交换机
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="topic_queue1"),
            exchange= @Exchange(name="itcast_topic",type = ExchangeTypes.TOPIC),
            key= {"china.#"}
    ))
    public void listTopicQueue1(String msg){
        System.out.println("消费者topicQueue1接收到的消息:["+msg+"]"+ LocalTime.now());
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name="topic_queue2"),
            exchange= @Exchange(name="itcast_topic",type = ExchangeTypes.TOPIC),
            key= {"#.news"}
    ))
    public void listTopicQueue2(String msg){
        System.out.println("消费者topicQueue2接收到的消息:["+msg+"]"+ LocalTime.now());
    }

    //接收消息序列化
    @RabbitListener(queues = "object_queue")
    public void listObjectQueue(Map<String,Object> msg){
        System.out.println("消费者objectQueue接收到的消息:["+msg+"]");
    }
}
