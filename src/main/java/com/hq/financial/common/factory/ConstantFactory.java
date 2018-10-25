package com.hq.financial.common.factory;

import com.hq.financial.util.SpringContextHolder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @program: financial
 * @description: 常量生成工厂
 * @author: Mr.Huang
 * @create: 2018-10-22 09:29
 **/
@Component
@DependsOn("springContextHolder")
//使用DependsOn让spring先加载该类，用于间接依赖
public class ConstantFactory {

}
