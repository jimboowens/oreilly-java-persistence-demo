package com.oreilly.persistence.dao;

import com.oreilly.persistence.entities.Officer;
import com.oreilly.persistence.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JdbcOfficerDAOTest {
    @Autowired
    JdbcOfficerDAO dao;

//    @Autowired
//    Logger log;

    @Test
    public void testSave() {
        Officer officer = new Officer("Wesley", "Crusher", Rank.ENSIGN);
        dao.save(officer);
        assertNotNull(officer.getId());
    }

    @Test
    public void findByIdThatExists() {
        Optional<Officer> officer = dao.findById(1);
        assertAll(
                () -> assertTrue(officer.isPresent()),
                () -> assertEquals(1, officer.get().getId().intValue())
        );

    }

    @Test
    public void findByIdThatDoesNotExist() {
        Optional<Officer> officer = dao.findById(999);
        assertFalse(officer.isPresent());
    }

    @Test
    public void count() {
        assertEquals(5, dao.count());
    }

    @Test
    public void findAll() {
        List<String> dbNames = dao.findAll().stream()
                .map(Officer::getLastName)
                .collect(Collectors.toList());
        assertThat(dbNames, containsInAnyOrder("Archer", "Picard", "Janeway", "Kirk", "Sisko"));
    }

    @Test
    public void delete() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> {
                    Optional<Officer> officer = dao.findById(id);
                    assertTrue(officer.isPresent());
                    dao.delete(officer.get());
                });
//        log.info("here: " + dao.findAll());
        assertEquals(0, dao.count());
    }

    @Test
    public void existsById() {
        IntStream.rangeClosed(1, 5)
                .forEach(id -> assertTrue(dao.existsById(id)));
    }
}
