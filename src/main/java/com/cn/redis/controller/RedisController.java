package com.cn.redis.controller;

import com.alibaba.fastjson.JSON;
import com.cn.redis.service.RedisService;
import com.cn.redis.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hehaixia
 * Date: 2018-06-27 下午2:53
 */
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private RedisService redisService;

    @Value("${expire.time}")
    private long expireTime;

    @RequestMapping(value = "/set/{cxt}", method = RequestMethod.GET)
    public String set(@PathVariable("cxt") String cxt) {
        redisService.set("key", JsonUtils.toJSon(cxt),expireTime);
        return "success";
    }

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public String get(@PathVariable("key") String key) {
        String res = JsonUtils.objecTOjson(redisService.get(key));
        return res+"success";
    }
}
