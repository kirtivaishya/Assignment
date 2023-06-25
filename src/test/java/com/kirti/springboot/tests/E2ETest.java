package com.kirti.springboot.tests;

import com.kirti.springboot.annotations.E2E;
import com.kirti.springboot.annotations.LazyAutowired;
import lombok.Getter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

@E2E
@Getter
public class E2ETest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeEach
    public void setup() {
    }

    @LazyAutowired
    public ApplicationContext applicationContext;

    @AfterEach
    public void teardown() {
        this.applicationContext
                .getBean(WebDriver.class)
                .quit();
    }
}
