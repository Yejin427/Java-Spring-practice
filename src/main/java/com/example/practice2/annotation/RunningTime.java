package com.example.practice2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) //annotation 대상
@Retention(RetentionPolicy.RUNTIME) //annotation 유지 기간
public @interface RunningTime {
}
