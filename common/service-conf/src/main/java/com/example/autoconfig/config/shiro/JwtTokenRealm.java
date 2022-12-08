package com.example.autoconfig.config.shiro;

import com.example.util.JwtEntity;
import com.example.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaozhiwei
 */
@Slf4j
@Component
public class JwtTokenRealm extends AuthorizingRealm {


    public JwtTokenRealm() {
        //CredentialsMatcher，自定义匹配策略（即验证jwt token的策略）
        super(new CredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                //我都能解析出来用户信息了,token还能是假的?
                return true;
                //如果要把token放redis的话,这一步就很有意义了
            }
        });
    }


    @Override//权限管理
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        JwtEntity user = (JwtEntity) SecurityUtils.getSubject().getPrincipal();
        log.info("权限验证,用户:{}", user.getUsername());
        log.debug("用户具有如下角色:{}", user.getPermissions());
        log.debug("用户具有如下权限:{}", user.getPermissions());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(user.getRoles());

        //这里选择的是token存用户权限,如果建议改成token只存用户角色,每次用的时候查一次权限
        Set<String> stringPermissions = new HashSet<>(user.getPermissions());
        simpleAuthorizationInfo.addStringPermissions(stringPermissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 从token中解析出用户信息
     * 不过能解析出用户信息
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("获取token信息");
        BearerToken bearerToken = (BearerToken) authenticationToken;
        String bearerTokenString = bearerToken.getToken();
        //检查token是否有效
        if (!JwtUtil.checkToken(bearerTokenString)) {
            throw new AuthenticationException("token无效或已过期!");
        }

        //解析出用户信息
        JwtEntity jwtEntity = JwtUtil.getUserInfo(bearerTokenString);
        //设置用户id,方便controller直接获取
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            //0表示范围为当前请求
            requestAttributes.setAttribute("userId", jwtEntity.getUserId(), 0);
        }

        //principal直接放对象,credential放token
        SimpleAuthenticationInfo res = new SimpleAuthenticationInfo(jwtEntity, bearerTokenString, this.getName());
        return res;
    }


    @Override
    public String getName() {
        return "JwtTokenRealm";
    }

    @Override
    public Class getAuthenticationTokenClass() {
        //设置由本realm处理的token类型。BearerToken是在filter里自动装配的。
        return BearerToken.class;
    }


}
