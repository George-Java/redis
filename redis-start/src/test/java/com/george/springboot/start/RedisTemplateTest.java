package com.george.springboot.start;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.george.springboot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
@Slf4j
public class RedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        //手动序列化，java对象-->json字符串
        User user = new User("nxy", "123456");
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user", json);

        //手动反序列化，json字符串-->java对象
        String json2 = stringRedisTemplate.opsForValue().get("user");
        User redisUser = mapper.readValue(json2, User.class);
        System.out.println("redisUser:" + redisUser);
    }
}
