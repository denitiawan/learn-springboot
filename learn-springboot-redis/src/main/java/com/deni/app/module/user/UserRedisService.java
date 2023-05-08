package com.deni.app.module.user;


import com.deni.app.common.constants.Messages;
import com.deni.app.common.controller.ResponseHandler;
import com.deni.app.redis.dto.RedisDto;
import com.deni.app.redis.service.RedisAbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserRedisService extends RedisAbstractService {

    public ResponseEntity getDataByHaskey(String haskey) {
        RedisDto redis = redisRepo.getDataByHaskeyForEntity(haskey, UserRedisDTO.class);
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    redis,
                    HttpStatus.OK);

        }
        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                redis,
                HttpStatus.NOT_FOUND);

    }

}
