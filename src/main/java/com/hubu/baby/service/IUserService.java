package com.hubu.baby.service;

import com.hubu.baby.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.hubu.baby.vo.BabyResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public interface IUserService extends IService<User> {

    /**
     * 启动小程序的时候调用
     *
     * @param code
     * @return
     */
    BabyResult startApp(String encrypted_data, String iv, String code);

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    BabyResult getUserById(Integer userId);

    /**
     * 更新用户信息
     *
     * @param user
     * @param birthdayStr
     * @return
     */
    BabyResult updateUserInfo(User user, String birthdayStr) throws Exception;

    /**
     * 更新用户头像信息
     *
     * @param userId
     * @param multipartFile
     * @return
     */
    BabyResult updateUserPicture(String userId, MultipartFile multipartFile);

    /**
     * 退出登录
     *
     * @param openid
     * @return
     */
    BabyResult logout(String openid);
}
