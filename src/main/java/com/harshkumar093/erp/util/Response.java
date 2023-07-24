package com.harshkumar093.erp.util;

public record Response<Res>(int statusCode,String message,Res data) {
}
