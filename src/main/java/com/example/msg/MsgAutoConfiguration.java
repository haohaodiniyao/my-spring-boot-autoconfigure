package com.example.msg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration//配置类
@ConditionalOnClass(MsgService.class)//MsgService在classpath中
@EnableConfigurationProperties(MsgProperties.class)//将application.properties中属性设置到MsgProperties
public class MsgAutoConfiguration {
    @Resource
    private MsgProperties msgProperties;

    @Bean//该方法实例化的对象会被加载到容器中
    @ConditionalOnMissingBean(MsgService.class)//容器不存在MsgService才会实例化
    //配置文件中msg.enabled=true才会实例化
    @ConditionalOnProperty(prefix = "msg",value = "enabled",havingValue = "true")
    public MsgService msgService(){
        return new MsgService(msgProperties.getUrl(),msgProperties.getAccessKeyId(),msgProperties.getAccessKeySecret());
    }
}
