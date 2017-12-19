package org.soho.order.repository;

import org.soho.common.dao.BaseDao;
import org.soho.order.domain.ExOrder;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExOrderDao extends BaseDao<ExOrder> {
    public ExOrderDao(){
        TABLE_NAME="EX_ORDER";
        PK_COLUMN ="ID";
        QUERY_ALL = "SELECT * FROM "+TABLE_NAME;
        QUERY_ALL_COUNT = "SELECT COUNT(*) FROM "+  TABLE_NAME;
        INSERT_SQL = "INSERT  INTO "+TABLE_NAME+" (ID,USER_ID,ORDER_NAME,AMOUNT)VALUES(:id,:userId,:orderName,:amount)";
        DELETE_SQL = "DELETE FROM "+TABLE_NAME+" WHERE id=?";
        UPDATE_SQL = "UPDATE "+ TABLE_NAME+" SET USER_ID=:userId,ORDER_NAME=:orderName,AMOUNT=:amount where ID=:id";
        mapper = new SingleColumnRowMapper<ExOrder>() {
            public ExOrder  mapRow(ResultSet rs, int rowNum) throws SQLException {
                ExOrder exOrder =  new ExOrder();
                exOrder.setId(rs.getString("ID"));
                exOrder.setUserId(rs.getString("USER_ID"));
                exOrder.setOrderName(rs.getString("ORDER_NAME"));
                exOrder.setAmount(rs.getDouble("AMOUNT"));
                return exOrder;
            }
        };
    }
}
