package com.video.testui.service;

import com.video.testui.model.AccountStatus;

public interface CheckAccountService {
    AccountStatus isValid(String id);
}
