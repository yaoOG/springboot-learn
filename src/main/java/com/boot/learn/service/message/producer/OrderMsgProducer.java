package com.boot.learn.service.message.producer;

import com.boot.learn.bean.ProductOrder;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author choo
 */
public class OrderMsgProducer {

    private static List<ProductOrder> orderList = null;

    public static void main(String[] args) throws Exception{
        //示例生产者
        DefaultMQProducer producer = new DefaultMQProducer("orderProducerGroup");
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //绑定name server
        producer.setNamesrvAddr("IP:9876");
        //
        producer.setRetryTimesWhenSendFailed(3);
        for (ProductOrder order : orderList) {
            //1、生成消息
            Message message = new Message("ORDER_MSG_TOPIC", "", order.getOrderId(), order.toString().getBytes());
            //2、发送消息是 针对每条消息选择对应的队列
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    //3、arg的值其实就是下面传入 orderId
                    String orderid = (String) arg;
                    //4、因为订单是String类型，所以通过hashCode转成int类型
                    int hashCode = orderid.hashCode();
                    //5、因为hashCode可能为负数 所以取绝对值
                    hashCode = Math.abs(hashCode);
                    //6、保证同一个订单号 一定分配在同一个queue上
                    long index = hashCode % mqs.size();
                    return mqs.get((int) index);
                }
            }, order.getOrderId(),50000);

            System.out.printf("Product：发送状态=%s, 存储queue=%s ,orderid=%s, type=%s\n", sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getQueueId(), order.getOrderId(), order.getType());
        }
        producer.shutdown();

    }
}
