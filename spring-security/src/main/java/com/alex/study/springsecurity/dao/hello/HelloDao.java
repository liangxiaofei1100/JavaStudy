package com.alex.study.springsecurity.dao.hello;

import com.alex.study.springsecurity.domain.hello.db.GreetingEntity;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class HelloDao {

    Logger logger = LoggerFactory.getLogger(HelloDao.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HibernateTemplate hibernateTemplate;

    /**
     * 使用JdbcTemplate
     */
    public GreetingEntity sayHello() {
        final GreetingEntity greeting = new GreetingEntity();

        String sql = getRamdomRecordSql("id", "greeting", 1);
        Object[] params = new Object[]{};
        jdbcTemplate.query(sql, params, (resultSet) -> {
            greeting.message = resultSet.getString("message");
        });

        if (greeting.message == null) {
            greeting.message = "Hello.";
        }

        return greeting;
    }

    /**
     * 使用HibernateTemplate
     */
    public GreetingEntity sayHello2() {
        DetachedCriteria dc = DetachedCriteria.forClass(GreetingEntity.class);
        dc.setProjection(Projections.rowCount());
        List<?> list = hibernateTemplate.findByCriteria(dc);
        int count = 0;
        if (!list.isEmpty()) {
            count = Integer.valueOf(list.get(0).toString());
        }
        logger.debug("count: " + count);

        int firstResult = new Random().nextInt(count);

        DetachedCriteria criteria2 = DetachedCriteria.forClass(GreetingEntity.class);
        List<GreetingEntity> greetingList = (List<GreetingEntity>) hibernateTemplate.findByCriteria(criteria2, firstResult, 1);
        if (!greetingList.isEmpty()) {
            return greetingList.get(0);
        }

        return null;
    }

    public static String getRamdomRecordSql(String idColumn, String table, int count) {
        String sql = "SELECT * FROM " + table + " WHERE " + idColumn + " >= ((SELECT MAX(" + idColumn +
                ") FROM " + table + " )-(SELECT MIN(" + idColumn + ") FROM " + table +
                " )) * RAND() + (SELECT MIN(" + idColumn + ") FROM " + table + " )  LIMIT " + count;
        return sql;
    }
}
