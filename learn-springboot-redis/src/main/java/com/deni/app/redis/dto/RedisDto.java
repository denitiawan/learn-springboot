package com.deni.app.redis.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedisDto {
    String key;
    Object value;
}
