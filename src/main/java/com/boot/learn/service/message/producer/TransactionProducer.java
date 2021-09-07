package com.boot.learn.service.message.producer;

import com.boot.learn.service.message.consumer.SelfTransactionListener;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author choo
 */
public class TransactionProducer {

    public static void main(String[] args) {
        try {
            TransactionMQProducer producer = new TransactionMQProducer("transactionMQProducer");
            producer.setNamesrvAddr("10.0.133.29:9876");
            producer.setTransactionListener(new SelfTransactionListener());
            producer.start();
            for (int i = 1; i < 6; i++) {
                Message message = new Message("TransactionTopic", "transactionTest","msg-" + i, ("Hello" + ":" +  i).getBytes());
                try {
                    SendResult result = producer.sendMessageInTransaction(message, "Hello" + ":" +  i);
                    System.out.printf("Topic:%s send success, misId is:%s%n", message.getTopic(), result.getMsgId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(Integer.MAX_VALUE);
            producer.shutdown();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
