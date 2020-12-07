package com.example.demo.service;

import com.example.demo.dto.AuditEvent;

public interface AuditService {
    void audit(AuditEvent auditEvent);
}
