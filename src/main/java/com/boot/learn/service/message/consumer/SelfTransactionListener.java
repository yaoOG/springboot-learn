package com.boot.learn.service.message.consumer;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author choo
 */
public class SelfTransactionListener implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);
    private AtomicInteger checkTimes = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
    /**
     * 执行本地事务
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        String msgKey = message.getKeys();
        System.out.println("start execute local transaction " + msgKey);
        LocalTransactionState state;
        if (msgKey.contains("1")) {
            // 第一条消息让他通过
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else if (msgKey.contains("2")) {
            // 第二条消息模拟异常，明确回复回滚操作
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        } else {
            // 第三条消息无响应，让它调用回查事务方法
            state = LocalTransactionState.UNKNOW;
            // 给剩下3条消息，放1，2，3三种状态
            localTrans.put(msgKey, transactionIndex.incrementAndGet());
        }
        System.out.println("executeLocalTransaction:" + message.getKeys() + ",execute state:" + state + ",current time：" + System.currentTimeMillis());
        return state;
    }

    /**
     * 回查本地事务结果
     *
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String msgKey = messageExt.getKeys();
        System.out.println("start check local transaction " + msgKey);
        Integer state = localTrans.get(msgKey);
        switch (state) {
            case 1:
                System.out.println("check result unknown 回查次数" + checkTimes.incrementAndGet());
                return LocalTransactionState.UNKNOW;
            case 2:
                System.out.println("check result commit message, 回查次数" + checkTimes.incrementAndGet());
                return LocalTransactionState.COMMIT_MESSAGE;
            case 3:
                System.out.println("check result rollback message, 回查次数" + checkTimes.incrementAndGet());
                return LocalTransactionState.ROLLBACK_MESSAGE;

            default:
                return LocalTransactionState.COMMIT_MESSAGE;
        }
    }
}
