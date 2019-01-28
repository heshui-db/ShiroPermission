package top.bindong.permission.shiro.interceptor;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;
import top.bindong.permission.shiro.interceptor.handler.CustomizeAnnotationHandler;

public class CustomizeAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {

    public CustomizeAnnotationMethodInterceptor() {
        super(new CustomizeAnnotationHandler());
    }

    public CustomizeAnnotationMethodInterceptor(AnnotationResolver resolver) {
        super(new CustomizeAnnotationHandler(), resolver);
    }
}
