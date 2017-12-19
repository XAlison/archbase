package org.soho.common.service;

import org.soho.common.ResultInfo;
import org.soho.common.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public abstract class BaseService<T>{
	

	abstract public BaseDao<T> getDao();

	/**
	 * 返回表格中某1列的最大值
	 * @param column
	 * @return 列column的最大值
	 */
	
	public int getMax(String column){
		return getDao().getMax(column);
	}
	
	/**
	 * 返回表中的记录数
	 * @return 返回记录数
	 */
	public int getCount(){
		return getDao().getCount();
	}
	
	/**
	 *  返回表中的记录数
	 * @param param 检索条件
	 * @return 返回满足param条件的记录数
	 */
	@SuppressWarnings("unchecked")
	public int getCountParam(Map<String, Object> param){
		return getDao().getCount(param);
	}
	
	/**
	 * 返回表中满足condition条件的记录数
	 * @param condition
	 * @return 返回满足的记录数
	 */
	public int getCountCondition(String condition){
		return getDao().getCount(condition);
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 */
	public boolean executeSql(String sql) {
		boolean flag=false;
		try {
			getDao().execute(sql);
			flag=true;
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

	/**
	 * 返回根据sql语句检索得到的第1个数据
	 * @param sql
	 * @return 得到的数据
	 */
	public T query(String sql){
		return getDao().query(sql);
	}
	
	/**
	 * 返回根据sql语句检索得到的数据集
	 * @param sql
	 * @return 得到的数据集
	 */
	public List<T>  queryList(String sql){
		return getDao().queryList(sql);
	}

	/**
	 * 
	 * @return 所有数据集
	 */
	public List<T> getAll(){
		return getDao().getAll();
	}
	/**
	 * 返回满足键值对条件的数据集
	 * @param param
	 * @return 满足的数据集
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllList(Map<String, Object> param){
		return getDao().getAll(param);
	}
	/**
	 * 返回满足condition条件的数据集
	 * @param condition
	 * @return 满足的数据集
	 */
	public List<T> getAll(String condition){
		return getDao().getAll(condition);
	}
	
	/**
	 * 返回对应id的bean
	 * @param id 记录id
	 * @return  对应id的实体
	 */
	public T getByID(String id){
		return getDao().getByID(id);
	}
	/**
	 * 获取第currentPage页的数据，每页大小为pageSize
	 * @param currentPage
	 * @param pageSize
	 * @return  该页的数据集
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByPage(int currentPage, int pageSize){
		return getDao().getByPage(currentPage,pageSize);
	}
	/**
	 * 根据键值对的条件获取第currentPage页的数据，每页大小为pageSize
	 * @param currentPage
	 * @param pageSize
	 * @param param
	 * @return  该页的数据集
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListByPage(int currentPage, int pageSize, Map<String, Object> param){
		return getDao().getByPage(currentPage,pageSize,param);		
	}
	/**
	 * 根据条件获取第currentPage页的数据，每页大小为pageSize
	 * @param currentPage
	 * @param pageSize
	 * @param condition
	 * @return 该页的数据集
	 */
	public List<T> getCondPage(int currentPage, int pageSize, String condition){
		return getDao().getCondPage(currentPage,pageSize,condition);		
	}
	
	
	/**
	 * 插入bean到数据库
	 * @param bean
	 */
	public ResultInfo insert(T bean){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().insert(bean);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;		
	}

	/**
	 * 根据sql将bean中的信息插入数据库
	 * @param sql
	 * @param bean
	 */
	public ResultInfo insert(String sql,T bean){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().insert(sql,bean);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 更新bean
	 * @param bean
	 */
	public ResultInfo update(T bean){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().update(bean);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;		
	}	

	/**
	 * 根据sql更新bean
	 * @param sql
	 * @param bean
	 */
	
	public ResultInfo update(String sql,T bean){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().update(sql,bean);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;		
	}	

	/**
	 * 更新键值对列出的信息
	 * @param id 记录id
	 * @param param
	 */
	
//	public boolean update(String id, Map<String, Object> param){
//		boolean flag=false;
//		try {
//			getDao().update(id,param);
//			flag=true;
//		} catch (Exception e) {
//			flag=false;
//		}
//		return flag;
//	}	
	
	/**
	 * 删除实体
	 * @param bean
	 */
	
	public ResultInfo delete(T bean){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().delete(bean);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;		
	}	

	/**
	 * 根据记录id删除记录
	  * @param id 记录id
	 */
	
	@SuppressWarnings("unchecked")
	public ResultInfo deleteById(String id){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().deleteByID(id);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;		
	}

	/**
	 * 根据条件删除记录
	 * @param condition 删除条件
	 */
	
	public ResultInfo deleteCondition(String condition){
		ResultInfo resultInfo = new ResultInfo();
		try {
			getDao().delete(condition);
			resultInfo.setFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setFlag(false);
			resultInfo.setMessage(e.getMessage());
		}
		return resultInfo;			
	}
	


	
}
