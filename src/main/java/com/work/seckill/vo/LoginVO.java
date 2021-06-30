package com.work.seckill.vo;

import com.work.seckill.util.validator.IsMobile;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVO {
//    校验不能为空
    @NotNull
//     @IsMobile自定义的注解，判断电话格式对不对
    @IsMobile
    private String mobile;
    @NotNull
    private String password;

}

