package com.raven.framework.controller;


import com.raven.framework.dto.LoginDto;
import com.raven.framework.enums.ResultEnum;
import com.raven.framework.exception.CommonException;
import com.raven.framework.service.LoginService;
import com.raven.framework.util.JwtUtil;
import com.raven.framework.vo.ResultVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录管理控制器
 *
 * @author Raven
 * @since 2020-02-12
 * @since 2020-02-12 15:26
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody LoginDto loginDto) {
        try {
            return new ResultVO<>(ResultEnum.SUCCESS, loginService.doLogin(loginDto));
        } catch (Exception e) {
            throw new CommonException(ResultEnum.COMMON_ERROR, e);
        }
    }


    @GetMapping("/userLogout")
    public ResponseEntity<User> logout(@AuthenticationPrincipal User user, HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            loginService.blacklist(user.getUsername(), token);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new CommonException(ResultEnum.COMMON_ERROR, e);
        }
    }

    @GetMapping("/resetToken")
    public ResultVO<String> resetToken(@AuthenticationPrincipal User user) {
        try {
            return new ResultVO<>(ResultEnum.SUCCESS, JwtUtil.generateToken(user));
        } catch (Exception e) {
            throw new CommonException(ResultEnum.COMMON_ERROR, e);
        }
    }


    @GetMapping("/permissionUser")
    public ResultVO<String> loginTest(@AuthenticationPrincipal User authUser) {
        return new ResultVO<>(ResultEnum.SUCCESS, "你成功访问了该api，这代表你已经登录，你是： " + authUser);
    }


    @GetMapping("/permissionRole")
    @PreAuthorize("hasRole('admin')")
    public ResultVO<String> loginTest() {
        return new ResultVO<>(ResultEnum.SUCCESS, "你成功访问了需要有 admin 角色的api。");
    }
}
