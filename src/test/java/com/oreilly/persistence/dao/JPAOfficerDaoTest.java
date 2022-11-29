package com.oreilly.persistence.dao;

import com.oreilly.persistence.config.JpaQueries;
import com.oreilly.persistence.config.SqlQueries;
import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.OfficerEntity;
import com.oreilly.persistence.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JPAOfficerDaoTest {
    @Autowired
    private JpaOfficerDAO dao;

    @Autowired
    private JdbcTemplate template;

    @Autowired
    SqlQueries sqlQueries;

    @Autowired
    JpaQueries jpaQueries;

    private final RowMapper<Integer> idRowMapper = (rs, num) -> rs.getInt("id");

    private List<Integer> getIds() {
        return template.query(sqlQueries.getGetOfficerIds(), idRowMapper);
    }

    @Test
    public void testSave() {
        OfficerEntity officer = new OfficerEntity("Wesley", "Crusher", Rank.LIEUTENANT);
        dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findOneThatExists() {
        getIds().forEach(id -> {
            Optional<OfficerEntity> officer = dao.findById(id);
            assertAll(
                    () -> assertTrue(officer.isPresent()),
                    () -> assertEquals(id, officer.get().getId())
            );
        });
    }

    @Test
    public void findOneThatDoesNotExist() {
        Optional<OfficerEntity> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void findAll() {
        List<String> dbNames = dao.findAll()
                .stream().map(OfficerEntity::getLastName)
                .collect(Collectors.toList());
        assertThat(dbNames, containsInAnyOrder("Archer", "Picard", "Janeway", "Kirk", "Sisko"));
    }

    @Test
    public void count() {
        assertEquals(5, dao.count());
    }

    @Test
    public void delete() {
        getIds().forEach(id -> {
            Optional<OfficerEntity> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            dao.delete(officer.get());
        });
//        log.info("here: " + dao.findAll());
        assertEquals(0, dao.count());
    }

    @Test
    public void getById(){
        getIds().forEach(id->assertTrue(dao.existsById(id)));
    }
}
