package com.hubu.baby.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hubu.baby.constant.WxConstant;
import com.hubu.baby.entity.User;
import com.hubu.baby.mapper.UserMapper;
import com.hubu.baby.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hubu.baby.util.*;
import com.hubu.baby.vo.BabyResult;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private Map<String, Object> userMap;

    @Value("${baby.public.ftp_address}")
    private String FTP_ADDRESS;
    @Value("${baby.public.ftp_port}")
    private Integer FTP_PORT;
    @Value("${baby.public.ftp_username}")
    private String FTP_USERNAME;
    @Value("${baby.public.ftp_password}")
    private String FTP_PASSWORD;
    @Value("${baby.public.ftp_base_path}")
    private String FTP_BASE_PATH;
    @Value("${baby.public.image_address}")
    private String IMAGE_BASE_URL;


    @Override
    public BabyResult startApp(String encrypted_data, String iv, String code) {

        try {
            //获取session_key
            String accessJson = this.getSessionKey(code);
            if ("".equals(accessJson)) {
                return BabyResult.build(401, "获取session_key失败");
            }
            JsonObject accessJsonObject = new JsonParser().parse(accessJson).getAsJsonObject();
            String openid = accessJsonObject.get("openid").getAsString();

            if(!userMap.containsKey(openid)) {
                /**
                 * 第一次授权
                 */
                //解析用户微信信息
                String session_key = accessJsonObject.get("session_key").getAsString();;
                String userWxInfoJson = WxDataUtil.DecryptDataGetUserInfoJson(encrypted_data, session_key, iv);
                if (null == userWxInfoJson || "".equals(userWxInfoJson)) {
                    logger.debug("获取用户微信信息失败");
                    return BabyResult.build(401, "获取用户微信信息失败");
                }
                //保存用户信息
                User user = this.saveUserInfo(userWxInfoJson, openid);
                //将用户信息放入缓存
                userMap.put(openid, user);
                //将用户信息返回给客户端
                return BabyResult.ok(user);
            } else {
                /**
                 * 开启小程序,之前已经授权
                 */
                User user = (User) userMap.get(openid);
                return BabyResult.ok(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("拉取登录信息失败");
            return BabyResult.build(500, "拉取登录信息失败");
        }

    }

    @Override
    public BabyResult getUserById(Integer userId) {

        User user = this.selectById(userId);
        if (user == null) {
            return BabyResult.build(400, "没有此用户信息");
        }
        return BabyResult.ok(user);

    }

    @Override
    public BabyResult updateUserInfo(User user, String birthdayStr) throws Exception{

        user.setBirthday(FormatDateUtil.fomatDate2(birthdayStr));
        user.setUpdated(new Date());
        boolean result = this.insertOrUpdate(user);
        if (!result) {
            return BabyResult.build(400, "更新用户信息失败");
        }
        //更新缓存信息
        userMap.put(user.getOpenid(), user);

        return BabyResult.ok(user);
    }

    @Override
    public BabyResult updateUserPicture(String userId, MultipartFile multipartFile) {

        //查询用户信息
        User user = this.selectById(userId);
        if (null == user) {
            return BabyResult.build(400, "用户不存在");
        }
        //处理文件
        String filePath = this.uploadFile(multipartFile);
        if ("".equals(filePath)) {
            return BabyResult.build(500, "上传头像信息失败");
        }
        //更新用户信息
        user.setPicture(filePath);
        boolean result = this.insertOrUpdate(user);
        if (!result) {
            return BabyResult.build(400, "更新用户头像信息失败");
        }
        //更新缓存
        userMap.put(user.getOpenid(), user);

        return BabyResult.ok(user);
    }

    @Override
    public BabyResult logout(String openid) {

        if (userMap.get(openid) != null) {
            userMap.remove(openid);
        }
        return BabyResult.ok();
    }

    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     */
    private String uploadFile(MultipartFile multipartFile) {

        /**
         * #FTP相关配置
         #FTP的ip地址
         FTP_ADDRESS=123.207.59.152
         FTP_PORT=21
         FTP_USERNAME=ftpuser
         FTP_PASSWORD=zhao2018
         FTP_BASE_PATH=/home/ftpuser/ftp/image
         #图片服务器的相关配置
         #图片服务器的基础url
         IMAGE_BASE_URL=http://123.207.59.152/image

         */
        try {
            //取原始文件名
            String oldName = multipartFile.getOriginalFilename();
            //生成一个新的文件名
            String newName = IDUtil.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            //String imagePath = new DateTime().toString("/yyyy/MM/dd");
            //String imagePath =  IDUtil.genImageName();
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD,
                    FTP_BASE_PATH, "/image/upload", newName,
                    multipartFile.getInputStream());
            //返回结果
            if (!result) {
                return "";
            }
            return IMAGE_BASE_URL + newName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("上传头像信息失败");
            return "";
        }
    }

    /**
     * 获取session_key
     *
     * @param code
     * @return
     */
    private String getSessionKey(String code) {

        //通过code换取网页授权access_token
        String access_token_url = WxConstant.ACCESS_TOKEN_BASE_URL
                + "appid=" + WxConstant.APPID
                + "&secret=" + WxConstant.APPSECRET
                + "&js_code=" + code
                + "&grant_type=authorization_code";

        //发送请求
        String jsonResult = HttpClientUtil.doGet(access_token_url);
        if (jsonResult == null || "".equals(jsonResult)) {
            logger.debug("获取session_key失败");
            return "";
        }

        return jsonResult;
    }

    /**
     * 保存用户微信信息到数据库
     *
     * @param userWxInfoJson
     * @param openid
     * @return
     */
    private User saveUserInfo(String userWxInfoJson, String openid) {

        //查询是否有该用户
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.where("openid={0}", openid);
        User oldUser = this.selectOne(wrapper);
        if (oldUser != null) {
            return oldUser;
        }

        //解析Json数据
        JsonObject userInfoJsonObject = new JsonParser().parse(userWxInfoJson).getAsJsonObject();

        //保存用户信息
        String nickname = userInfoJsonObject.get("nickName").getAsString();
        String picture = userInfoJsonObject.get("avatarUrl").getAsString();

        User user = new User();
        user.setNickname(nickname);
        user.setPicture(picture);
        user.setBirthday(new Date());
        user.setOpenid(openid);
        user.setHight(new BigDecimal(160));
        user.setWeight(new BigDecimal(50));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        this.baseMapper.insert(user);

        return user;
    }
}
