package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
//import java.util.logging.Logger;

import com.oreilly.persistence.config.SqlQueries;

@SuppressWarnings("ConstantConditions")
@Repository
public class JdbcOfficerDAO implements OfficerDAO<Officer> {

    @Autowired
    SqlQueries sqlQueries;

//    @Autowired
//    Logger log;

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert insertOfficer;

    private final RowMapper<Officer> officerMapper = (rs, rowNum) ->
            new Officer(
                    rs.getInt("id"),
                    Rank.valueOf(rs.getString("rank")),
                    rs.getString("first_name"),
                    rs.getString("last_name")
            );

    @Deprecated
    private final RowMapper<Officer> officerMapper() {
        return new RowMapper<Officer>() {
            @Override
            public Officer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Officer(
                        rs.getInt("id"),
                        Rank.valueOf(rs.getString("rank")),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
        };
    }


    //better to autowire the argument (here, the jdbcTemplate), because testing is simplified.
    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Officer save(Officer officer) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("rank", officer.getRank());
        params.put("first_name", officer.getFirstName());
        params.put("last_name", officer.getLastName());
        Integer newId = insertOfficer.executeAndReturnKey(params).intValue();
        officer.setId(newId);
        return officer;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
//        log.info("sqlQueries: [" + sqlQueries + "]");
        return (!existsById(id))
                ? Optional.empty()
                : Optional.of(jdbcTemplate.queryForObject(sqlQueries.getFindOfficerById(), officerMapper, id));
    }

    @Override
    public List<Officer> findAll() {
        return jdbcTemplate.query(sqlQueries.getFindAllOfficers(), officerMapper);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject(sqlQueries.getGetOfficerCount(), Long.class);
    }

    @Override
    public void delete(Officer officer) {
        jdbcTemplate.update(sqlQueries.getDeleteOfficerById(), officer.getId());
    }

    @Override
    public boolean existsById(Integer id) {
        return jdbcTemplate.queryForObject(sqlQueries.getExistsById(), Boolean.class, id);
    }

    @Override
    public String toString() {
        return "JdbcOfficerDAO{" +
                "jdbcTemplate=" + jdbcTemplate +
                ", insertOfficer=" + insertOfficer +
                ", officerMapper=" + officerMapper +
                '}';
    }
}
