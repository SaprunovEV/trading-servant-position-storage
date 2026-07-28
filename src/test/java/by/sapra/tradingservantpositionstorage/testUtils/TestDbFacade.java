package by.sapra.tradingservantpositionstorage.testUtils;

import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

public class TestDbFacade {
    private TestEntityManager entityManager;
    private JdbcTemplate jdbc;
    private TransactionTemplate transaction;

    public  <T> T find(Object id, Class<T> entityClass) {
        return transaction.execute(status ->
                entityManager.find(entityClass, id));
    }

    public <T> T save(TestDataBuilder<T> builder) {
        return transaction.execute(status -> entityManager
                .persistAndFlush(builder.build()));
    }

    public <T> TestDataBuilder<T> persistOnce(TestDataBuilder<T> builder) {
        return new TestDataBuilder<T>() {
            private T entity;

            @Override
            public T build() {
                if (entity == null) entity = persisted(builder).build();

                return entity;
            }
        };
    }

    public <T> TestDataBuilder<T> persisted(TestDataBuilder<T> builder) {
        return () -> transaction.execute(status -> {

            final var entity = builder.build();
            entityManager.persistAndFlush(entity);

            return entity;
        });
    }

    public void delete(Object entity) {
        transaction.execute(status -> {
            entityManager.remove(entity);
            return  null;
        });
    }

    private void cleanDatabase() {
        transaction.execute(status -> {
            JdbcTestUtils.deleteFromTables(
                    jdbc, ""
            );
            return null;
        });
    }
}
