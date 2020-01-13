package com.zhiyou100.hospital.security;

import com.zhiyou100.hospital.pojo.Power;
import com.zhiyou100.hospital.pojo.Role;
import com.zhiyou100.hospital.pojo.User;
import com.zhiyou100.hospital.service.IRolePowerService;
import com.zhiyou100.hospital.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: ShiroRealm
 */
@EnableTransactionManagement
@Service
public class ShiroRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRolePowerService rolePowerService;
    /**
     * 授权：注意
     * 并不是认证完直接授权，而是用户在做操作时判断有没有这个权限才走授权
     *
     * @param principalCollection
     * @return AuthorizationInfo对象
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("--------开始授权------------");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取用户
        User user = (User) principalCollection.getPrimaryPrincipal();
        //TODO:我们需要通过用户将角色和权限查出来
        Set<String> roles = new HashSet<>();
        Set<String> Powers = new HashSet<>();
        //根据用户id查询角色
        User user1 = userService.queryAll(user.getAccount(), user.getPassword());
        roles.add(user1.getRole().getName());
        List<Power> powers = user1.getRole().getPowers();
        for (Power p:powers
             ) {
            Powers.add(p.getName());
        }
        logger.info("permission==>"+Powers);
        logger.info("role==>"+roles);
        //封装权限和角色
        authorizationInfo.setStringPermissions(Powers);
        authorizationInfo.setRoles(roles);
        logger.info("-----------授权结束---------------------");
        return authorizationInfo;
    }

    /**
     * 认证方法---》先认证，再走上边授权方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("--------------------开始认证----------------");
        UsernamePasswordToken token = null;
        //将authenticationToken强转成UsenamePasswordToken；
        if(!(authenticationToken instanceof UsernamePasswordToken)){
             throw new AuthenticationException("类型不匹配");
        }
        token= (UsernamePasswordToken)authenticationToken;
        //获取用户名和密码
        String name = token.getUsername();

        String pass = new String(token.getPassword());
        //判断用户名和密码是否正确
        //根据用户名查询数据库，是否有用户返回
        //TODO 连接数据库
        User userExits = userService.findByName(name);
        System.out.println(userExits);
        if(userExits == null){
            throw new UnknownAccountException("账号不存在");
        }else if(!pass.equals(userExits.getPassword())){
            throw new IncorrectCredentialsException("账号或密码错误");
        }
        //返回AuthenticationInfo对象
        //返回用户-----》SecutiryUtils对象getSubject
        return new SimpleAuthenticationInfo(userExits, pass, this.getClass().getName());
    }
}
