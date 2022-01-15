package com.video.testui.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckAccountModel {
    private String ID;
    private AccountStatus status;
}
