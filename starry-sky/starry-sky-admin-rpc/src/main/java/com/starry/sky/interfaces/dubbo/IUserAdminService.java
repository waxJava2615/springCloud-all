package com.starry.sky.interfaces.dubbo;


import com.starry.sky.interfaces.vo.UserDetailDTO;

/**
 * @author wax
 * @description: 获取用户信息
 * @date 2021-11-10
 */
public interface IUserAdminService {


    /**
     * 根据用户ID 获取用户信息
     * @param userName
     * @return
     */
    UserDetailDTO loadByUserName(String userName);


    /**
     * 根据手机号获取用户信息
     * @param phone
     * @return
     */
    UserDetailDTO loadByPhone(String phone);

}
