package com.testprod.produit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.testprod.produit.entities.AuditLog;


public interface AuditLogRepository  extends JpaRepository<AuditLog, Long>, JpaSpecificationExecutor<AuditLog>{

}
