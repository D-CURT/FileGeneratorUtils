package com.files.generator;

import com.files.generator.utils.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Optional;

public class Validator {

    public static final String LINES_NUMBER_ERROR_MESSAGE = "Lines number was not specified, default will be set...";
    public static final String PATH_ERROR_MESSAGE = "File path was not specified, default will be set...";
    private static Logger log = LoggerFactory.getLogger(Validator.class);

    public static <T> T getNonNullValue(T value, T defaultValue, String errorMessage) {
        return Optional.ofNullable(value).orElseGet(() -> {
            if (StringUtils.isNotEmpty(errorMessage)) {
                log.info(errorMessage);
            }
            return defaultValue;
        });
    }

    public static Long getValidNumber(Long value, Long defaultValue, String errorMessage) {
        Long nonNullValue = getNonNullValue(value, defaultValue, errorMessage);
        return nonNullValue < 0 ? 0 : nonNullValue;
    }

    public static Integer getValidNumber(Integer value, Integer defaultValue, String errorMessage) {
        Integer nonNullValue = getNonNullValue(value, defaultValue, errorMessage);
        return nonNullValue < 0 ? 0 : nonNullValue;
    }

}
