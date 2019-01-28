package top.bindong.permission.shiro.interceptor.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import top.bindong.permission.shiro.annotation.DPermission;

import java.lang.annotation.Annotation;

public class CustomizeAnnotationHandler extends AuthorizingAnnotationHandler {

    public CustomizeAnnotationHandler() {
        super(DPermission.class);
    }

    @Override
    public void assertAuthorized(Annotation annotation) throws AuthorizationException {
        DPermission permission = (DPermission) annotation;
        getSubject().checkPermission(permission.id());
    }
}
