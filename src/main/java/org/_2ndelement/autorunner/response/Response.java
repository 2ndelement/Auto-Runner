package org._2ndelement.autorunner.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> ok(T data) {
        return new Response<>(200, "success", data);
    }

    public static <T> Response<T> ok() {
        return new Response<>(200, "success", null);
    }

    public static <T> Response<T> fail(int code, String message) {
        return new Response<>(code, message, null);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(400, message, null);
    }

}
