//package az.netx.heroes.service;
//
//import az.netx.heroes.component.mapper.ObjectMapper;
//import az.netx.heroes.repository.WarRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//class WarServiceTest {
//
//    @InjectMocks
//    private WarService warService;
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private WarRepository warRepository;
//
//
//    @BeforeEach
//    void init() {
//        warService = new WarService(warRepository, objectMapper);
//    }
//
//
//    @Test
//    void injectedComponentsAreNotNull() {
//        assertThat(warRepository).isNotNull();
//        assertThat(warService).isNotNull();
//    }
//
//    @Test
//    @Sql("classpath:/war.sql")
//    void getAllActiveWars() {
//        System.out.println(warRepository.findAllByStatus("ACTIVE"));
//    }
//
//}
//
