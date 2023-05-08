package com.deni.app.redis.service;

import com.deni.app.common.constants.Messages;
import com.deni.app.common.controller.ResponseHandler;
import com.deni.app.redis.dto.RedisDto;
import com.deni.app.redis.repo.RedisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * this class for abstraction the redis service
 * for generally all function for redis service needed
 */
public abstract class RedisAbstractService implements RedisAbstractContract {
    @Autowired
    public RedisRepo redisRepo;

    // save or update data redis
    public ResponseEntity saveOrUpdateData(RedisDto redisDto) {
        Object redis = redisRepo.saveOrUpdate(redisDto.getKey(), redisDto.getValue());
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_SAVE_SUCCESS,
                    redisDto,
                    HttpStatus.OK);
        }
        return ResponseHandler.createHttpResponse(
                "save redis failed",
                redisDto,
                HttpStatus.BAD_REQUEST);
    }

    // delete data redis by haskey from redis

    public ResponseEntity deleteDataByHaskey(String haskey) {

        Object redis = redisRepo.getDataByHaskeyForString(haskey);
        if (redis != null) {
            redisRepo.deleteDataByHaskey(haskey);
            return ResponseHandler.createHttpResponse(
                    String.format("Delete %s success", haskey),
                    "",
                    HttpStatus.OK);

        }

        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                haskey,
                HttpStatus.OK);

    }


    // get data by hash key from redis
    public ResponseEntity getDataByHaskey(String haskey) {
        Object redis = redisRepo.getDataByHaskeyForString(haskey);
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    redis,
                    HttpStatus.OK);
        }
        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                haskey,
                HttpStatus.OK);

    }


    // get all data from redis
    public ResponseEntity getAllData() {
        Object redis = redisRepo.getAllData();
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    redis,
                    HttpStatus.OK);
        }
        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                "",
                HttpStatus.OK);
    }

}
