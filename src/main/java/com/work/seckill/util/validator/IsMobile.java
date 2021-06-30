package com.work.seckill.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

// 自定义注解 在自定义注解中，其实现部分只能定义一个东西：注解类型元素（annotation type element）

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER})
//@Retention作用是定义被它所注解的注解保留多久，
// source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
//class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
//runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Retention(RetentionPolicy.RUNTIME)
//@Documented注解只是用来做标识，没什么实际作用，
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface IsMobile {
    boolean required() default true;//默认不能为空

    String message() default "手机号码格式错误";//校验不通过输出信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

