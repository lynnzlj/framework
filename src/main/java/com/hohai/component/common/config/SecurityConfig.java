package com.hohai.component.common.config;

import com.hohai.component.security.AuthenticationEntryPointImpl;
import com.hohai.component.security.JwtAuthenticationTokenFilter;
import com.hohai.component.security.LogoutSuccessHandlerImpl;
import com.hohai.component.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Lynn
 * @date 2022/3/10 9:26
 * @description SpringSecurity的配置类，引入JWT
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** 自定义用户认证逻辑 */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /** 自定义认证失败处理类 */
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    /** 自定义退出处理类 */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    /** token认证过滤器 */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;


    /**
     * 解决 无法直接注入 AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    /** 忽略拦截swagger相关请求和静态资源 */
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html/**")
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/webjars/**")
                .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/profile/**");
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                //禁用csrf，因为不适用session
                .csrf().disable()
                //禁用sessionManager
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //过滤请求
                .authorizeRequests()
                //登录请求允许匿名访问
                .antMatchers("/druid/**").anonymous()
                .antMatchers("/register").anonymous()
                .antMatchers("/login").anonymous()
                .antMatchers("/system/**").permitAll()
                //除上面以外的所有请求都需要鉴权认证
                .anyRequest().authenticated();
        // 自定义退出处理
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加JWT filter
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }



    /** 强散列哈希加密实现 */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /** 身份认证接口 */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
