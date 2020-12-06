package com.example.demo.service;

import com.example.demo.dto.UserEvent;

public interface AuditService {
    void audit(UserEvent userEvent);
}
