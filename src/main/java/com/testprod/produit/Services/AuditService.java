package com.testprod.produit.Services;

import com.testprod.produit.entities.AuditLog;
import com.testprod.produit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    @Transactional
    public void logAction(String entityType, Long entityId, String action, String oldValue, String newValue) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityType(entityType);
        auditLog.setEntityId(entityId);
        auditLog.setAction(action);
        auditLog.setOldValue(oldValue);
        auditLog.setNewValue(newValue);
      //  auditLog.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        
        auditLogRepository.save(auditLog);
    }

	
}