package com.oreilly.persistence.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jpa.queries")
@Configuration("JpaQueries")
public class JpaQueries {


    private String FIND_ALL_OFFICERS;

    private String GET_OFFICER_COUNT;

    private String EXISTS_BY_ID;

    public String getFindAllOfficers() {

        return FIND_ALL_OFFICERS;
    }

    public void setFindAllOfficers(String findAllOfficers) {
        FIND_ALL_OFFICERS = findAllOfficers;
    }

    public String getGetOfficerCount() {
        return GET_OFFICER_COUNT;
    }

    public void setGetOfficerCount(String getOfficerCount) {
        GET_OFFICER_COUNT = getOfficerCount;
    }

    public String getExistsById() {
        return EXISTS_BY_ID;
    }

    public void setExistsById(String existsById) {
        EXISTS_BY_ID = existsById;
    }

    @Override
    public String toString() {
        return "JpaQueries{" +
                ", FIND_ALL_OFFICERS='" + FIND_ALL_OFFICERS + '\'' +
                ", GET_OFFICER_COUNT='" + GET_OFFICER_COUNT + '\'' +
                ", EXISTS_BY_ID='" + EXISTS_BY_ID + '\'' +
                '}';
    }
}
