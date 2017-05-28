package com.nicefish.controller;

import com.nicefish.utils.PasswordUtil;
import com.nicefish.utils.TokenUtil;
import com.nicefish.exception.EmailConflictException;
import com.nicefish.exception.PasswordRequiredException;
import com.nicefish.exception.UserNameRequiredException;
import com.nicefish.po.POUser;
import com.nicefish.service.UserService;
import com.nicefish.utils.SessionConsts;
import com.nicefish.utils.UUidUtil;
import com.nicefish.vo.VOUserLogin;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/access")
public class AccessController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody POUser poUser) throws Exception {

        if(StringUtils.isEmpty(poUser.getUserName())){
            throw new UserNameRequiredException();
        }
        if(StringUtils.isEmpty(poUser.getPassword())){
            throw new PasswordRequiredException();
        }
        //TODO:这里需要处理登录异常

        SecurityUtils.getSubject().login(new UsernamePasswordToken(poUser.getUserName(),poUser.getPassword()));

        this.userService.ensureUser(poUser);
        VOUserLogin userLogin = new VOUserLogin();
        userLogin.setToken(TokenUtil.generate(UUidUtil.generate(),poUser.getUserId(),"issuer",30*60*1000));
        BeanUtils.copyProperties(userLogin, poUser);

        return userLogin;
    }

    @RequestMapping(value = "/logout/{userId}", method = RequestMethod.GET)
    public Map<String, Object> logout(@PathVariable String userId, HttpSession session) throws Exception {
        session.removeAttribute(SessionConsts.UserInfo);
        session.removeAttribute(SessionConsts.ImageVertifyCode);
        return this.ajaxSuccessResponse();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody POUser poUser) throws Exception {
        if (ObjectUtils.anyNotNull(this.userService.findByEmail(poUser.getEmail()))) {
            throw new EmailConflictException();
        }
        poUser.setUserId(UUidUtil.generate());
        //把email拷贝到userName，因为前端只给了email参数
        poUser.setUserName(poUser.getEmail());
        //把最后一次登录时间设置为系统当前时间
        poUser.setLastLoginTime(new Date());
        //密码再次加密
        poUser.setPassword(PasswordUtil.encryptPassword(poUser));
        this.userService.insert(poUser);

        return poUser;
    }
}
