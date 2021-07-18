package com.zfg.learn.controller.portal;

import com.zfg.learn.common.Const;
import com.zfg.learn.config.BeanContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 下载控制器
 */
@Controller
@RequestMapping("/Download")
public class DownloadController {
    @Autowired
    RedisTemplate redisTemplate;
    public final String REDIS_KEY = "dynamic:lock:";

    @GetMapping("/chromeExtend")
    public ResponseEntity<byte[]> chromeExtend() throws IOException {
        File file = new File(Const.Path.CHROME_EXTENSION);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("chrome插件.zip", "UTF-8"));
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/redis1")
    public String redis1(){
        RedisLock redisLock = new RedisLock(REDIS_KEY);
        redisLock.lock();
        String interview = "success";
        redisLock.unlock();
        return interview;
    }

    @GetMapping("/redis2")
    public String redis2(){
        RedisLock redisLock = new RedisLock(REDIS_KEY);
        redisLock.lock();
        String interview = "success";
        redisLock.unlock();
        return interview;
    }

    @GetMapping("/redis3")
    public String redis3(){
        RedisLock redisLock = new RedisLock(REDIS_KEY);
        redisLock.lock();
        String interview = "success";
        redisLock.unlock();
        return interview;
    }


    public class RedisLock{
        private final String key;
        private final String value;
        private static final String DEL_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        RedisTemplate redisTemplate = BeanContext.getBean("redisTemplate");
        public RedisLock(String key){
            this.key = key;
            this.value = System.currentTimeMillis()+"z"+Math.random();
        }

        public RedisLock(String key, String value){
            this.key = key;
            this.value = value;
        }

        public void lock(){
            long start = System.currentTimeMillis();
            System.out.println("线程"+Thread.currentThread().getId()+"正在努力获取锁");
            while (!redisTemplate.opsForValue().setIfAbsent(key, value, 60, TimeUnit.SECONDS)){
            }
            long spendTime = System.currentTimeMillis() - start;
            System.out.println("线程"+Thread.currentThread().getId()+"经过" +spendTime+ "获取锁成功!!!");
        }

        public void unlock(){
            DefaultRedisScript defaultRedisScript = new DefaultRedisScript(DEL_SCRIPT, Long.class);
            Long execute = (Long) redisTemplate.execute(defaultRedisScript , Collections.singletonList(key), value);
            System.out.println(execute);
            System.out.println("线程"+Thread.currentThread().getId()+"释放锁");
        }
    }

}
