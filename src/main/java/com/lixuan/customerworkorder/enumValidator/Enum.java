package com.lixuan.customerworkorder.enumValidator;



import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 枚举注解
 *
 * @author lixuan
 * @date 2022-03-12 13:51
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface Enum {


    String message() default "{*.validation.constraints.Enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        NotBlank[] value();
    }

    /**
     * the enum's class-type
     *
     * @return Class
     */
    Class<?> clazz();

    /**
     * the method's name ,which used to validate the enum's value
     * 指定枚举校验具体方法
     * 默认校验 getValue方法
     *
     * @return method's name
     */
    String method() default "getValue";


}
