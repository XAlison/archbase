package org.soho.user.repository;

import org.soho.common.dao.BaseDao;
import org.soho.user.domain.NewInfo;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhuozl on 17-4-24.
 */
@Repository
public class NewInfoDao extends BaseDao<NewInfo> {

    public NewInfoDao(){
        TABLE_NAME="NEW_INFO";
        PK_COLUMN ="ID";
        QUERY_ALL = "SELECT * FROM "+TABLE_NAME;
        QUERY_ALL_COUNT = "SELECT COUNT(*) FROM "+  TABLE_NAME;
        INSERT_SQL = "INSERT  INTO "+TABLE_NAME+" (ID,NAME,TITLE,CONTENT)VALUES(:id,:name,:title,:content)";
        DELETE_SQL = "DELETE FROM "+TABLE_NAME+" WHERE id=?";
        UPDATE_SQL = "UPDATE "+ TABLE_NAME+" SET NAME=:name,TITLE=:title,CONTENT=:content where ID=:id";
        mapper = new SingleColumnRowMapper<NewInfo>() {
            public NewInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                NewInfo newInfo =  new NewInfo();
                newInfo.setId(rs.getString("ID"));
                newInfo.setName(rs.getString("NAME"));
                newInfo.setTitle(rs.getString("TITLE"));
                newInfo.setContent(rs.getString("CONTENT"));
                return newInfo;
            }
        };
    }

    public NewInfo getUserByName(String userName) {
        List<NewInfo> lists = jdbcTemplate.query(QUERY_ALL + " WHERE USER_MAIL=? OR USER_NAME=?",
                new Object[] { userName ,userName}, mapper);
        if (lists.size() == 0) {
            return null;
        } else
            return lists.get(0);
    }
}
