package com.neo.strategy.handler;

import cn.hutool.core.map.MapUtil;
import com.neo.strategy.common.OrderTypeEnum;
import com.neo.strategy.utils.ClassScaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>实现BeanFactoryPostProcessor 在spring beanFactory初始化完成之后会被调用</p>
 *
 * @author neo
 * @date 2019/12/4 13:58
 */
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {

    /**
     * 扫描该路径下的所有类
     */
    private static final String HANDLER_PACKAGE = "com.neo.strategy.handler.biz";

    /**
     * 扫描@HandlerType，初始化HandlerContext，将其注册到spring容器
     *
     * @param beanFactory bean工厂
     * @see HandlerType
     * @see HandlerContext
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<OrderTypeEnum, Class> handlerMap = MapUtil.newHashMap(3);
        ClassScaner.scan(HANDLER_PACKAGE, HandlerType.class).forEach(clazz -> {
            OrderTypeEnum type = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(type, clazz);
        });
        HandlerContext context = new HandlerContext(handlerMap);
        beanFactory.registerSingleton(HandlerContext.class.getName(), context);
    }
}
