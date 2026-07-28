package by.sapra.tradingservantpositionstorage.config;

import by.sapra.tradingservantpositionstorage.testUtils.TestDbFacade;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@ContextConfiguration(classes = AbstractDataConfig.class)
public class AbstractDataTest {

    @Autowired
    private TestDbFacade facade;

    public TestDbFacade getFacade() {
        return facade;
    }

    protected static PostgreSQLContainer container;

    static {
        DockerImageName postgres = DockerImageName.parse("postgres:14.5");
        container = new PostgreSQLContainer(postgres);

        container.withReuse(true);
        container.withDatabaseName("positions");
        container.withInitScript("static/schema.sql");
    }

    @DynamicPropertySource
    public static void registryProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.jpa.generate-ddl", () -> true);
        registry.add("spring.datasource.driver-class-name", () -> container.getDatabaseName());
    }

    @AfterEach
    void tearDown() {
        facade.cleanDatabase();
    }

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }
}
