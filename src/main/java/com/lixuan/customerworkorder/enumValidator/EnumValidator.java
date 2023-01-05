package com.lixuan.customerworkorder.enumValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 枚举值校验
 *
 * @author lixuan
 * @date 2022-03-12 13:54
 */
public class EnumValidator implements ConstraintValidator<Enum, Object> {

    private Enum anEnum;

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.anEnum = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if ("".equals(value) || Objects.isNull(value)) {
            return true;
        }
        if (Objects.isNull(anEnum)) {
            return true;
        }
        Object[] objects = anEnum.clazz().getEnumConstants();
        try {
            //获取被校验的方法
            Method method = anEnum.clazz().getMethod(anEnum.method());
            for (Object object : objects) {
                if (value.equals(method.invoke(object))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
