package com.jiangwensi.busarrival.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Jiang Wensi on 17/7/2020
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Bus Not Found")
public class NotFoundException extends Exception{
    public NotFoundException(String value) {
        super("Unable to find bus no "+value);
    }
}
