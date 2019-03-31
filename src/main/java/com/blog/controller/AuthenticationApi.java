package com.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.blog.model.User;
import com.blog.service.AuthenticationService;
import com.blog.service.UserService;
import com.blog.utils.BaseResponse;
import com.blog.utils.StatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hc on 2019/3/30.
 */
@RestController
@RequestMapping("/api/login")
public class AuthenticationApi {
    private AuthenticationService authenticationService;
    private UserService userService;


    public AuthenticationApi(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("")
    public Object login(@RequestBody User user) {
        User userInDataBase = userService.findByName(user.getName());
        JSONObject jsonObject = new JSONObject();

        if (userInDataBase == null) {
            BaseResponse baseResponse = new BaseResponse(StatusCode.NotFound);
            jsonObject.put("error", "用户不存在");
            baseResponse.setData(jsonObject);
            return baseResponse;
        } else if (!userService.comparePassword(user, userInDataBase)) {
            BaseResponse baseResponse = new BaseResponse(StatusCode.Fail);
            jsonObject.put("error", "密码不正确");
            baseResponse.setData(jsonObject);
            return baseResponse;
        } else {
            String token = authenticationService.getToken(userInDataBase);
            BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
            jsonObject.put("token", token);
            jsonObject.put("user", userInDataBase);
            baseResponse.setData(jsonObject);
            return baseResponse;
//            jsonObject.put(baseResponse);
//            jsonObject.put("token", token);
//            jsonObject.put("user", userInDataBase);
        }

//        return jsonObject;

    }
}
