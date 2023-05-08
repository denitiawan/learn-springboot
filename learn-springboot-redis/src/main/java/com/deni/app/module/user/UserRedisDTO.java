package com.deni.app.module.user;


import lombok.*;

/**
 * Created by DenPaden on 18/08/2022.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRedisDTO {

    Long id;
    String username;
    String password;
    String roles = "";
    String permissions = "";
    Integer blocked;
    Integer active;


}
