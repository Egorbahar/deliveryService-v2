package com.exposit.core.aspect;

import com.exposit.core.component.LocalMessageSource;
import com.exposit.persistence.entity.Store;
import com.exposit.persistence.repository.StoreRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
    private final LocalMessageSource messageSource;
    private final StoreRepository storeRepository;

    public ValidationAspect(LocalMessageSource messageSource, StoreRepository storeRepository) {
        this.messageSource = messageSource;
        this.storeRepository = storeRepository;
    }

    @Around("@annotation(com.exposit.core.annotation.ValidateEntity)")
    public Object addEntityValidate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        if (methodArgs[0] instanceof Store) {
            Store store = (Store) methodArgs[0];
            if (store.getId() != null) {
                throw new RuntimeException(messageSource.getMessage("error.store.notHaveId", new Object[]{}));
            }
            if (storeRepository.existsByAddress(store.getAddress()))
                throw new RuntimeException(messageSource.getMessage("error.store.address.notUnique", new Object[]{}));
        }
        proceedingJoinPoint.proceed();
        return methodArgs;
    }
}
