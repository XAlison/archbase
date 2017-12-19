package org.soho.pay.repository;

import org.soho.common.dao.BaseDao;
import org.soho.pay.domain.ExPay;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExPayDao extends BaseDao<ExPay> {
    public ExPayDao() {
        TABLE_NAME = "EX_PAY";
        PK_COLUMN = "ID";
        QUERY_ALL = "SELECT * FROM " + TABLE_NAME;
        QUERY_ALL_COUNT = "SELECT COUNT(*) FROM " + TABLE_NAME;
        INSERT_SQL =
                "INSERT  INTO " + TABLE_NAME + " (ID,USER_ID,AMOUNT)VALUES(:id,:userId,:amount)";
        DELETE_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id=?";
        UPDATE_SQL = "UPDATE " + TABLE_NAME + " SET USER_ID=:userId,AMOUNT=:amount where ID=:id";
        mapper = new SingleColumnRowMapper<ExPay>() {
            public ExPay mapRow(ResultSet rs, int rowNum) throws SQLException {
                ExPay exPay = new ExPay();
                exPay.setId(rs.getString("ID"));
                exPay.setUserId(rs.getString("USER_ID"));
                exPay.setAmount(rs.getDouble("AMOUNT"));
                return exPay;
            }
        };
    }


    public ExPay getPayByUserId(String userId) {
        List<ExPay> lists = jdbcTemplate.query(QUERY_ALL + " WHERE USER_ID=?",
                new Object[] { userId}, mapper);
        if (lists.size() == 0) {
            return null;
        } else
            return lists.get(0);
    }
}
