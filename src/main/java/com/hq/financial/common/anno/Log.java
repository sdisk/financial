package com.hq.financial.common.anno;

import java.lang.annotation.*;

/**
 * @program: financial
 * @description: 记录业务日志
 * @author: Mr.Huang
 * @create: 2018-10-17 16:39
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String valus();
}
