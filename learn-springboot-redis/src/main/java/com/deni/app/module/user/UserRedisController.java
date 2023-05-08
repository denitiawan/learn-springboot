package com.deni.app.module.user;


import com.deni.app.common.controller.Response;
import com.deni.app.redis.controller.RedisAbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/redis/user")
public class UserRedisController extends RedisAbstractController {
    @Autowired
    UserRedisService service;

    @GetMapping(value = "/find/{haskey}")
    public ResponseEntity<Response> getDataByHaskey(@PathVariable String haskey) {
        return service.getDataByHaskey(haskey);
    }


}
