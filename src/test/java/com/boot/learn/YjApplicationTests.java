package com.boot.learn;

import com.boot.learn.bean.User;
import com.boot.learn.service.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhuyao
 * @date 2019/08/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class YjApplicationTests {

    @Autowired
    private IRedisService iRedisService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void testRedis() {
//        iRedisService.set("aaa", "BBB");
        redisTemplate.opsForValue().set("user1", new User(1, "yj", "123", "111"));
        User user1 = (User) redisTemplate.opsForValue().get("user1");
        System.out.println(user1);
    }

    public static void main(String[] args) {

    }
}
