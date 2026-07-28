package by.sapra.tradingservantpositionstorage.testUtils;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class TestDbFacade {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
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

    public <T> T findOneByField(Class<T> entityClass, String fieldName, Object fieldValue) {
        return transaction.execute(status -> {
            CriteriaBuilder cb = entityManager.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root).where(cb.equal(root.get(fieldName), fieldValue));
            try {
                return entityManager.getEntityManager().createQuery(query).getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    /**
     * Находит список сущностей по значению поля.
     */
    public <T> List<T> findByField(Class<T> entityClass, String fieldName, Object fieldValue) {
        return transaction.execute(status -> {
            CriteriaBuilder cb = entityManager.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(entityClass);
            Root<T> root = query.from(entityClass);
            query.select(root).where(cb.equal(root.get(fieldName), fieldValue));
            return entityManager.getEntityManager().createQuery(query).getResultList();
        });
    }

    /**
     * Подсчитывает количество записей по значению поля.
     */
    public <T> Long countByField(Class<T> entityClass, String fieldName, Object fieldValue) {
        return transaction.execute(status -> {
            CriteriaBuilder cb = entityManager.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<T> root = query.from(entityClass);
            query.select(cb.count(root)).where(cb.equal(root.get(fieldName), fieldValue));
            return entityManager.getEntityManager().createQuery(query).getSingleResult();
        });
    }

    public void delete(Object entity) {
        transaction.execute(status -> {
            entityManager.remove(entity);
            return  null;
        });
    }

    public void cleanDatabase() {
        transaction.execute(status -> {
            JdbcTestUtils.deleteFromTables(
                    jdbc, ""
            );
            return null;
        });
    }
}
