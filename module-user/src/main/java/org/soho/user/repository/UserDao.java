package org.soho.user.repository;

/**
 * Created by zhuozl on 17-4-24.
 */

import org.soho.common.dao.BaseDao;
import org.soho.user.domain.User;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao extends BaseDao<User> {
    public UserDao(){
        TABLE_NAME="T_USER";
        PK_COLUMN ="ID";
        QUERY_ALL = "SELECT * FROM "+TABLE_NAME;
        QUERY_ALL_COUNT = "SELECT COUNT(*) FROM "+  TABLE_NAME;
        INSERT_SQL = "INSERT  INTO "+TABLE_NAME+" (ID,NAME,AGE,SEX)VALUES(:id,:name,:age,:sex)";
        DELETE_SQL = "DELETE FROM "+TABLE_NAME+" WHERE id=?";
        UPDATE_SQL = "UPDATE "+ TABLE_NAME+" SET NAME=:name,AGE=:age,SEX=:sex where ID=:id";
        mapper = new SingleColumnRowMapper<User>() {
            public User  mapRow(ResultSet rs, int rowNum) throws SQLException {
                User tUser =  new User();
                tUser.setId(rs.getString("ID"));
                tUser.setName(rs.getString("NAME"));
                tUser.setAge(rs.getInt("AGE"));
                tUser.setSex(rs.getInt("SEX"));
                return tUser;
            }
        };
    }

    public User getUserByName(String userName) {
        List<User> lists = jdbcTemplate.query(QUERY_ALL + " WHERE NAME like ?",
                new Object[] { "%" + userName +"%" }, mapper);
        if (lists.size() == 0) {
            return null;
        } else
            return lists.get(0);
    }

}
