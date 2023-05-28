package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {
    @RabbitListener(queues = "simple_queue")
    public void listenerSimpleQueue(String msg){
        System.out.println("接收到的消息simple_queue:["+msg+"]");
    }
}
