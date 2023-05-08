package com.deni.app.redis.service;

import org.springframework.http.ResponseEntity;

public interface RedisAbstractContract {
    public abstract ResponseEntity getDataByHaskey(String haskey);
}
