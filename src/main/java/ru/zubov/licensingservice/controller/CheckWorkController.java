package ru.zubov.licensingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RefreshScope
@RestController
public class CheckWorkController {

    @Value("${spring.database.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${example.property}")
    private String property;

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/showConfig")
    @ResponseBody
    public String showConfig() {
        String configInfo = "<br/>spring.datasource.driver-class-name=" + driverClassName
                + "<br/>spring.datasource.url=" + url
                + "<br/>spring.datasource.username=" + userName
                + "<br/>spring.datasource.password=" + password
                + "<br/>example.property=" + property;

        return configInfo;
    }

    @RequestMapping("/pingDataSource")
    @ResponseBody
    public String pingDataSource() {
        try {
            return this.dataSource.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
