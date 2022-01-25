//package com.starry.sky.infrastructure.oauth.extension.sms;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import java.util.HashSet;
//
///**
// * @author wax
// * @description: 短信认证授权提供者
// * @date 2021-10-09
// */
//public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
//
//    private UserDetailsService userDetailsService;
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
//        String mobile = (String) authenticationToken.getPrincipal();
//        String code = (String) authenticationToken.getCredentials();
//
//        String codeKey = AuthConstants.SMS_CODE_PREFIX + mobile;
//        String correctCode = redisTemplate.opsForValue().get(codeKey);
//        // 短信验证码 000000
//        if (!code.equals("000000")) {
//            if (StrUtil.isBlank(correctCode) || !code.equals(correctCode)) {
//                throw new BizException("验证码不正确");
//            } else {
//                redisTemplate.delete(codeKey);
//            }
//        }
//        UserDetails userDetails = ((MemberUserDetailsServiceImpl) userDetailsService).loadUserByMobile(mobile);
//        SmsCodeAuthenticationToken result = new SmsCodeAuthenticationToken(userDetails, new HashSet<>());
//        result.setDetails(authentication.getDetails());
//        return result;
//    }
//
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//}
