package top.bindong.permission.shiro.advisor;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import top.bindong.permission.shiro.annotation.DPermission;
import top.bindong.permission.shiro.interceptor.CustomizeAopAllianceAnnotationsAuthorizingMethodInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CustomAuthorizationAdvisor extends AuthorizationAttributeSourceAdvisor {
    private static final Class[] AUTH_ANNOTATION_CLASSES =
            new Class[]{
                    RequiresPermissions.class, RequiresRoles.class,
                    RequiresUser.class, RequiresGuest.class, RequiresAuthentication.class, DPermission.class
            };

    public CustomAuthorizationAdvisor() {
        setAdvice(new CustomizeAopAllianceAnnotationsAuthorizingMethodInterceptor());
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        boolean flag = super.matches(method, targetClass);
        if (!flag && isAuthAnnotationPresent(method)) {
            flag = true;
        }
        return flag;
    }

    private boolean isAuthAnnotationPresent(Method method) {
        for (Class<? extends Annotation> annClass : AUTH_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(method, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }
}
