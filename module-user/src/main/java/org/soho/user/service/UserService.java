package org.soho.user.service;

import org.soho.common.dao.BaseDao;
import org.soho.common.exception.BaseException;
import org.soho.common.service.BaseService;
import org.soho.user.repository.NewInfoDao;
import org.soho.user.repository.UserDao;
import org.soho.user.domain.NewInfo;
import org.soho.user.domain.User;
import org.soho.user.vo.UserNewInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by zhuozl on 17-4-24.
 */

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private NewInfoDao newInfoDao;

    @Override
    public BaseDao<User> getDao() {
        return userDao;
    }

    public int getCount() {
        return userDao.getCount();
    }

    public User getUserByName(String userName){
        return userDao.getUserByName(userName);
    }

    public UserNewInfoVo postUserNewInfo(UserNewInfoVo vo){

        User user = new User();
        NewInfo newInfo = new NewInfo();

        try {
            newInfo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            newInfo.setName(vo.getName());
            newInfo.setContent(vo.getContent());
            newInfo.setTitle(vo.getTitle());
            newInfoDao.insert(newInfo);

            //USER
            //user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setId(vo.getUserId());
            user.setName(vo.getUserName());
            user.setAge(vo.getAge());
            user.setSex(vo.getSex());
            userDao.insert(user);
        }catch (Exception ex){
            throw new BaseException("发布信息错误");
        }

        return vo;
    }

}
