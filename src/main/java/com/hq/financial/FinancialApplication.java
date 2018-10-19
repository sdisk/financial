package com.hq.financial;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hq.financial.web.dao.mapping")
public class FinancialApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(FinancialApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FinancialApplication.class, args);
        LOGGER.info("FinancialApplication is success!");
    }
}
