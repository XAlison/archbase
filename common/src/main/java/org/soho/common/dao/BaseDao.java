package org.soho.common.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class BaseDao<Bean> {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	protected SingleColumnRowMapper<Bean> mapper=null;
	
	protected String PK_COLUMN=null;
	
	protected String TABLE_NAME=null;
	
	protected String QUERY_ALL = "select * from "+TABLE_NAME;

	protected  String QUERY_ALL_COUNT = "select count(*) from "+  TABLE_NAME;
	
	protected  String INSERT_SQL = "insert  into "+TABLE_NAME+" (" +
			"id,name, parent)VALUES(" +
			":id,:name, :parent.id)";

	protected  String DELETE_SQL = "delete from "+ TABLE_NAME +" where "+ PK_COLUMN +"=?";

	protected  String UPDATE_SQL = "UPDATE "+ TABLE_NAME+" set " +
			"parent=:parent.id, name=:name" +
			" where id=:id";
	
	protected  String ORDER_BY = "";
	
	
	/**
	 * 返回表格中某1列的最大值
	 * @param column
	 * @return 列column的最大值
	 */
	public int getMax(String column){
		String sql="select MAX("+column+") from " + TABLE_NAME ;
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}
	/**
	 * 返回表中的记录数
	 * @return 返回记录数
	 */
	public int getCount(){
		String sql=QUERY_ALL_COUNT;
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}
	
	/**
	 *  返回表中的记录数
	 * @param param 检索条件
	 * @return 返回满足param条件的记录数
	 */
	public int getCount(Map<String, Object> param){
		if(param==null){
			return getCount();
		}
		String sql=QUERY_ALL_COUNT;
		int len = param.size();
		Iterator<String> keyIt = param.keySet().iterator();
		Object values[] = new Object[len];
		int i=0;
		while(keyIt.hasNext()){
			String key = keyIt.next();
			if(i==0){
				sql+=" where " + key +"=?";
			}else{
				sql+=" and " + key +"=?";
			}
			values[i++]=param.get(key);
		}
		return jdbcTemplate.queryForObject(sql,values,Integer.class);
	}
	
	/**
	 * 返回表中满足condition条件的记录数
	 * @param condition
	 * @return 返回满足的记录数
	 */
	public int getCount(String condition){
		if((null==condition) || (condition.trim().length()==0)){
			return getCount();
		}
		
		String sql=QUERY_ALL_COUNT + " where "+ condition;
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 */
	public void  execute(String sql){
		jdbcTemplate.execute(sql);
	}
	/**
	 * 返回根据sql语句检索得到的第1个数据
	 * @param sql
	 * @return 得到的数据
	 */
	public Bean  query(String sql){
		List<Bean> beans = jdbcTemplate.query(sql,mapper);
		if (beans.size() == 0) {
			return null;
		} else
			return beans.get(0);
	}
	
	/**
	 * 返回根据sql语句检索得到的数据集
	 * @param sql
	 * @return 得到的数据集
	 */
	public List<Bean>  queryList(String sql){
		return jdbcTemplate.query(sql,mapper);
	}

	/**
	 * 
	 * @return 所有数据集
	 */
	public List<Bean> getAll(){
		return jdbcTemplate.query(QUERY_ALL+ORDER_BY, mapper);
	}
	/**
	 * 返回满足键值对条件的数据集
	 * @param param
	 * @return 满足的数据集
	 */
	public List<Bean> getAll(Map<String, Object> param){
		String sql=QUERY_ALL;
		int len = param.size();
		Iterator<String> keyIt = param.keySet().iterator();
		Object values[] = new Object[len];
		int i=0;
		while(keyIt.hasNext()){
			String key = keyIt.next();
			if(i==0){
				sql+=" where " + key +"=? ";
			}else{
				sql+=" and " + key +"=? ";
			}
			values[i++]=param.get(key);

		}
		sql+=ORDER_BY;
		return jdbcTemplate.query(sql,values, mapper);
	}
	/**
	 * 返回满足condition条件的数据集
	 * @param condition
	 * @return 满足的数据集
	 */
	public List<Bean> getAll(String condition){
		String sql=QUERY_ALL + " where " + condition+ORDER_BY;
		return jdbcTemplate.query(sql, mapper);
	}
	
	/**
	 * 返回对应id的bean
	 * @param id 记录id
	 * @return  对应id的实体
	 */
	public Bean getByID(String id){
		List<Bean> beans = jdbcTemplate.query(QUERY_ALL + " where "+ PK_COLUMN +"=?",
				new Object[] { id }, mapper);
		if (beans.size() == 0) {
			return null;
		} else
			return beans.get(0);
	}
	/**
	 * 获取第currentPage页的数据，每页大小为pageSize
	 * @param currentPage
	 * @param pageSize
	 * @return  该页的数据集
	 */
	public List<Bean> getByPage(int currentPage, int pageSize){
		if(currentPage<1){
			currentPage=1;
		}
		
		StringBuilder sql = new StringBuilder(QUERY_ALL);
		
		sql.append(ORDER_BY);
	    int pageNum=((currentPage - 1) * pageSize);
		List<Bean> lists = jdbcTemplate.query(QUERY_ALL + " limit ?,?",
				new Object[] { pageNum, pageSize}, mapper);
		return lists;
	}
	/**
	 * 根据键值对的条件获取第currentPage页的数据，每页大小为pageSize
	 * @param currentPage
	 * @param pageSize
	 * @param param
	 * @return  该页的数据集
	 */
	public List<Bean> getByPage(int currentPage, int pageSize, Map<String, Object> param){
		if(currentPage<1){
			currentPage=1;
		}
		
		StringBuilder sql = new StringBuilder(QUERY_ALL);
		int len = 0;
		Object values[];
		if(param!=null){
			len = param.size();
			Iterator<String> keyIt = param.keySet().iterator();
			values = new Object[len+2];
			int i=0;
			while(keyIt.hasNext()){
				String key = keyIt.next();
				if(i==0){
					sql.append(" where ").append(key).append("=? ");
				}else{
					sql.append(" and ").append(key).append("=? ");
				}
				values[i++]=param.get(key);
			}
		}else{
			values = new Object[len+2];
		}
		values[len]=(currentPage-1)*pageSize;
		values[len+1]=pageSize;

		sql.append(ORDER_BY);
		
		values[len]=((currentPage - 1) * pageSize);
		values[len+1]=pageSize;
		List<Bean> lists = jdbcTemplate.query(sql + " limit ?,?",values, mapper);
		return lists;
	}
	/**
	 * 根据条件获取第currentPage页的数据，每页大小为pageSize
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return 该页的数据集
	 */
	public List<Bean> getCondPage(int currentPage, int pageSize, String condition){
		if(currentPage<1){
			currentPage=1;
		}
		StringBuilder sql = new StringBuilder(QUERY_ALL);
		if(StringUtils.isNotEmpty(condition)){
			sql.append(" where ").append(condition);
		}
		sql.append(ORDER_BY);
		
		List<Bean> lists = jdbcTemplate.query(sql + " limit ?,?",
				new Object[] { ((currentPage - 1) * pageSize), pageSize}, mapper);
		return lists;
	}
	
	/**
	 * 插入bean到数据库
	 * @param bean
	 */
	public int insert(Bean bean){
		SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
		NamedParameterJdbcTemplate npTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		return npTemplate.update(INSERT_SQL, ps);
	}
	/**
	 * 根据sql将bean中的信息插入数据库
	 * @param sql
	 * @param bean
	 */
	public int insert(String sql,Bean bean){
		SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
		NamedParameterJdbcTemplate npTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		return npTemplate.update(sql, ps);
	}
	/**
	 * 更新bean
	 * @param bean
	 */
	public int update(Bean bean){
		return update(UPDATE_SQL, bean);
	}
	/**
	 * 根据sql更新bean
	 * @param sql
	 * @param bean
	 */
	public int update(String sql, Bean bean){
		SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
		NamedParameterJdbcTemplate npTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		return npTemplate.update(sql, ps);
	}
	/**
	 * 更新键值对列出的信息
	 * @param id 记录id
	 * @param param
	 */
	public int update(String id, Map<String, Object> param) {
        StringBuffer sb = new StringBuffer("UPDATE " + TABLE_NAME + " set ");
        int len = 0;
        Object values[];
        if (param != null) {
            len = param.size();
            Iterator<String> keyIt = param.keySet().iterator();
            values = new Object[len + 1];
            int i = 0;
            while (keyIt.hasNext()) {
                String key = keyIt.next();
                if (i == 0) {
                    sb.append(key).append("=? ");
                } else {
                    sb.append(" ,").append(key).append("=? ");
                }
                values[i++] = param.get(key);
            }
        } else {
            values = new Object[len + 1];
        }
        sb.append(" where ").append(PK_COLUMN).append("=?");
        values[len] = id;
        return jdbcTemplate.update(sb.toString(), values);
    }
	/**
	 * 删除实体
	 * @param bean
	 */
	public int delete(Bean bean){
		SqlParameterSource ps = new BeanPropertySqlParameterSource(bean);
		NamedParameterJdbcTemplate npTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate.getDataSource());
		return npTemplate.update(DELETE_SQL, ps);
	}
	/**
	 * 根据记录id删除记录
	  * @param id 记录id
	 */
	public int deleteByID(String id){
		String sql ="delete from "+ TABLE_NAME +" where "+ PK_COLUMN +"=?";
		return jdbcTemplate.update(sql, id);
	}
	
	
	public int deleteMore(String ids) {
		String delete_MORE_SQL = "delete from "+TABLE_NAME+" where id in ("+ids+")";	
		return jdbcTemplate.update(delete_MORE_SQL);
	}
	
	/**
	 * 根据条件删除记录
	 * @param condition 删除条件
	 */
	public int delete(String condition){
		String sql ="delete from "+ TABLE_NAME +" where "+ condition;
		return jdbcTemplate.update(sql);
	}


	public SingleColumnRowMapper<Bean> getMapper() {
		return mapper;
	}
	
	public void setOrderBy(String s){
		ORDER_BY = s;
	}
	
	
	
	/**
	 * 调试SQL预编译
	 * @param sql
	 * @param param
	 * @return
	 */
	public String getPreparedStatement(String sql, Object[] param) {
	    for (int i = 0; i < param.length; i++) {
	        sql = sql.replaceFirst("\\u003F", param[i].toString());
	    }
	    return sql;
	}
	
}
