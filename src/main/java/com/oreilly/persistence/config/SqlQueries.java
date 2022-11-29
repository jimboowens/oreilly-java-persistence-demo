package com.oreilly.persistence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sql.queries")
@Configuration("SqlQueries")
public class SqlQueries {

    private String FIND_OFFICER_BY_ID;

    private String FIND_ALL_OFFICERS;

    private String GET_OFFICER_COUNT;

    private String DELETE_OFFICER_BY_ID;

    private String EXISTS_BY_ID;

    private String GET_OFFICER_IDS;

    public String getFindOfficerById() {
        return FIND_OFFICER_BY_ID;
    }

    public void setFindOfficerById(String findOfficerById) {
        FIND_OFFICER_BY_ID = findOfficerById;
    }

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

    public String getDeleteOfficerById() {
        return DELETE_OFFICER_BY_ID;
    }

    public void setDeleteOfficerById(String deleteOfficerById) {
        DELETE_OFFICER_BY_ID = deleteOfficerById;
    }

    public String getExistsById() {
        return EXISTS_BY_ID;
    }

    public void setExistsById(String existsById) {
        EXISTS_BY_ID = existsById;
    }
    public String getGetOfficerIds() {
        return GET_OFFICER_IDS;
    }

    public void setGetOfficerIds(String getOfficerIds) {
        GET_OFFICER_IDS = getOfficerIds;
    }

    @Override
    public String toString() {
        return "SqlQueries{" +
                "FIND_OFFICER_BY_ID='" + FIND_OFFICER_BY_ID + '\'' +
                ", FIND_ALL_OFFICERS='" + FIND_ALL_OFFICERS + '\'' +
                ", GET_OFFICER_COUNT='" + GET_OFFICER_COUNT + '\'' +
                ", DELETE_OFFICER_BY_ID='" + DELETE_OFFICER_BY_ID + '\'' +
                ", EXISTS_BY_ID='" + EXISTS_BY_ID + '\'' +
                '}';
    }

}