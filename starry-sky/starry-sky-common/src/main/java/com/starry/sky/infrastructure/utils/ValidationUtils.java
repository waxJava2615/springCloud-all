package com.starry.sky.infrastructure.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.starry.sky.infrastructure.exception.CustomizeRuntimeException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @author wax
 * @description: 参数检验
 * @date 2021-07-20
 */
public class ValidationUtils {


    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//    private static ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
//        .configure()
//        .failFast( true )
//        .buildValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set constraintViolationSet = validator.validate(t, groups);
        if (constraintViolationSet.isEmpty()) {
            return Collections.emptyMap();
        } else {
            Map<String, String> errorMap = Maps.newHashMap();
            Iterator iterator = constraintViolationSet.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation constraintViolation = (ConstraintViolation) iterator.next();

                errorMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
            return errorMap;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection, Class... groups) {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, groups==null?new Class[0]:groups);
        } while (errors.isEmpty());
        return errors;
    }


    public static Map<String, String> validateObject(Object first,Class ...groups) {
        if (first != null && first instanceof Collection){
            return validateList((Collection)first);
        }else {
            return validate(first, groups==null?new Class[0]:groups);
        }
    }

    public static void check(Object param,Class ...groups) throws CustomizeRuntimeException {
        Map<String, String> mapValidate = ValidationUtils.validateObject(param,groups);
        if (MapUtils.isNotEmpty(mapValidate)) {
            String error = "";
            for (Map.Entry<String, String> entry : mapValidate.entrySet()){
                error = entry.getValue();
                break;
            }
            throw new CustomizeRuntimeException(error);
        }
    }


}
