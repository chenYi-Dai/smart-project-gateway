package com.example.project.shiro;


import com.example.project.service.UserService;
import com.example.project.vo.ActiveUser;
import com.example.project.vo.UserVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有登录的时候才会校验权限
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if(activeUser.getUser().getType()==0){
            authorizationInfo.addStringPermission("*:*");
        }else {
            List<String> permissions = new ArrayList<>(activeUser.getPermissions());
            List<Role> roleList = activeUser.getRoles();
            //授权角色
            if (!CollectionUtils.isEmpty(roleList)) {
                for (Role role : roleList) {
                    authorizationInfo.addRole(role.getRoleName());
                }
            }
            //授权权限
            if (!CollectionUtils.isEmpty(permissions)) {
                for (String  permission : permissions) {
                    if (permission != null && !"".equals(permission)) {
                        authorizationInfo.addStringPermission(permission);
                    }
                }
            }
        }*/
        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        log.info("token | {}", token);
        // 解密获得username，用于和数据库进行对比
        //String username = JWTUtils.getUsername(token);
        String username = "hehe";
        if (username == null) {
            throw new AuthenticationException(" token错误，请重新登入！");
        }

        //User userBean = userService.getUserInfo(username);
        UserVO userInfo = userService.getUserInfo(username);

        if (userInfo == null) {
            throw new AccountException("账号不存在!");
        }
        /*if(JWTUtils.isExpire(token)){
            throw new AuthenticationException(" token过期，请重新登入！");
        }

        if (! JWTUtils.verify(token, username, userInfo.getPassword())) {
            throw new CredentialsException("密码错误!");
        }*/

        //如果验证通过，获取用户的角色
        //List<Role> roles= userService.findRolesById(userBean.getId());
        //查询用户的所有菜单(包括了菜单和按钮)
        //List<Menu> menus=userService.findMenuByRoles(roles);

       /* Set<String> urls=new HashSet<>();
        Set<String> perms=new HashSet<>();
        if(!CollectionUtils.isEmpty(menus)){
            for (Menu menu : menus) {
                String url = menu.getUrl();
                String per = menu.getPerms();
                if(menu.getType()==0&& !StringUtils.isEmpty(url)){
                    urls.add(menu.getUrl());
                }
                if(menu.getType()==1&&!StringUtils.isEmpty(per)){
                    perms.add(menu.getPerms());
                }
            }
        }*/
        //过滤出url,和用户的权限
        ActiveUser activeUser = new ActiveUser();
        //activeUser.setRoles(roles);
        activeUser.setUser(userInfo);
        //activeUser.setMenus(menus);
        Set<String> setList = new HashSet<>();
        activeUser.setUrls(setList);
        activeUser.setPermissions(setList);
        return new SimpleAuthenticationInfo(activeUser, token, getName());
    }
}
