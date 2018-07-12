package com.hubu.baby.controller;

import com.hubu.baby.entity.User;
import com.hubu.baby.service.IUserService;
import com.hubu.baby.vo.BabyResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 用户api
 * @Source: JDK 1.8
 * @Author: ZhaoKunsong
 * @Date: 2018-04-22 14:48
 * @Since: version 1.0
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class IndexForUserController {

    @Autowired
    private IUserService userService;

    /**
     * 启动小程序的时候调用的api
     *
     * @param encrypted_data
     * @param iv
     * @param code
     * @return
     */
    @GetMapping("/start")
    public BabyResult startApp(@RequestParam("encryptedData") String encrypted_data,
                               @RequestParam("iv") String iv,
                               @RequestParam("code") String code) {

        try {
            BabyResult result =  userService.startApp(encrypted_data, iv, code);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return BabyResult.build(500, "启动错误");
        }

    }

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUser/{userId}")
    public BabyResult getUserById(@PathVariable("userId") Integer userId) {

        BabyResult result = userService.getUserById(userId);
        return result;
    }

    /**
     * 更新用户普通信息
     *
     * @param user
     * @return
     */
    @GetMapping("/updateInfo")
    public BabyResult updateUserInfo(User user, String birthdayStr) {

        if (null == user) {
            return BabyResult.build(400, "用户参数为空");
        }
        if (birthdayStr == null || "".equals(birthdayStr)) {
            return BabyResult.build(400, "字符串参数为空");
        }

        BabyResult result = null;
        try {
            result = userService.updateUserInfo(user,birthdayStr);
        } catch (Exception e) {
            result = BabyResult.build(400, "日期出现错误");
        } finally {
            return  result;
        }


    }

    /**
     * 更新用户头像信息
     *
     * @param userId
     * @param multipartFile
     * @return
     */
    @RequestMapping("/updatePicture")
    public BabyResult updateUserPicture(@RequestParam("userId") String userId, MultipartFile multipartFile) {

        if (multipartFile == null || StringUtils.isBlank(multipartFile.getOriginalFilename())){
            BabyResult.build(500, "头像信息不能为空");
        }

        BabyResult result = userService.updateUserPicture(userId, multipartFile);
        return result;

    }

    /**
     * 注销登录
     *
     * @param openid
     * @return
     */
    @GetMapping("/logout/{openid}")
    public BabyResult logout(@PathVariable("openid") String openid) {
        BabyResult result = userService.logout(openid);
        return result;
    }

}
