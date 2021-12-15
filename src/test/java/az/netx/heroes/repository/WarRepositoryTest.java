package az.netx.heroes.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:/war.sql")
class WarRepositoryTest {

    @Autowired
    private WarRepository warRepository;

    @Test
    void findAllByActiveStatus() {
        var actual = warRepository.findAllByStatus("ACTIVE").size();
        var expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    void findAllByDeletedStatus() {
        var actual = warRepository.findAllByStatus("DELETED").size();
        var expected = 1;
        assertEquals(actual, expected);
    }

    @Test
    void findAllByIdNotIn() {

    }

}