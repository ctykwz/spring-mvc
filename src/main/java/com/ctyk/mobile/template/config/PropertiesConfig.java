package com.ctyk.mobile.template.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * properties配置文件
 * Created by ctyk on 2016/11/30.
 */
@Configuration
public class PropertiesConfig {

    @Value("${swagger_title}")
    private String swaggerTitle;

    @Value("${swagger_serviceUrl}")
    private String swaggerServiceUrl;

    @Value("${swagger_contact_name}")
    private String swaggerContactName;

    @Value("${swagger_contact_url}")
    private String swaggerContactUrl;

    @Value("${swagger_contact_email}")
    private String swaggerContactEmail;

    @Value("${swagger_version}")
    private String swaggerVersion;

    @Value("${swagger_enabled}")
    private boolean swaggerEnabled;

    @Value("${swagger_login_name}")
    private String swaggerLoginName;

    @Value("${swagger_login_password}")
    private String swaggerLoginPassword;

    @Value("${swagger_scan_package}")
    private String swaggerScanPackage;

    public String getSwaggerTitle() {
        return swaggerTitle;
    }

    public void setSwaggerTitle(String swaggerTitle) {
        this.swaggerTitle = swaggerTitle;
    }

    public String getSwaggerServiceUrl() {
        return swaggerServiceUrl;
    }

    public void setSwaggerServiceUrl(String swaggerServiceUrl) {
        this.swaggerServiceUrl = swaggerServiceUrl;
    }

    public String getSwaggerContactName() {
        return swaggerContactName;
    }

    public void setSwaggerContactName(String swaggerContactName) {
        this.swaggerContactName = swaggerContactName;
    }

    public String getSwaggerContactUrl() {
        return swaggerContactUrl;
    }

    public void setSwaggerContactUrl(String swaggerContactUrl) {
        this.swaggerContactUrl = swaggerContactUrl;
    }

    public String getSwaggerContactEmail() {
        return swaggerContactEmail;
    }

    public void setSwaggerContactEmail(String swaggerContactEmail) {
        this.swaggerContactEmail = swaggerContactEmail;
    }

    public String getSwaggerVersion() {
        return swaggerVersion;
    }

    public void setSwaggerVersion(String swaggerVersion) {
        this.swaggerVersion = swaggerVersion;
    }

    public boolean isSwaggerEnabled() {
        return swaggerEnabled;
    }

    public void setSwaggerEnabled(boolean swaggerEnabled) {
        this.swaggerEnabled = swaggerEnabled;
    }

    public String getSwaggerLoginName() {
        return swaggerLoginName;
    }

    public void setSwaggerLoginName(String swaggerLoginName) {
        this.swaggerLoginName = swaggerLoginName;
    }

    public String getSwaggerLoginPassword() {
        return swaggerLoginPassword;
    }

    public void setSwaggerLoginPassword(String swaggerLoginPassword) {
        this.swaggerLoginPassword = swaggerLoginPassword;
    }

    public String getSwaggerScanPackage() {
        return swaggerScanPackage;
    }

    public void setSwaggerScanPackage(String swaggerScanPackage) {
        this.swaggerScanPackage = swaggerScanPackage;
    }
}
