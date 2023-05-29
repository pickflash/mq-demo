package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

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
}
