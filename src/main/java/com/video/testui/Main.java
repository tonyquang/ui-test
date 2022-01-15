/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.video.testui;

import com.video.testui.controller.CheckAccountController;
import com.video.testui.service.CheckAccountService;
import com.video.testui.service.CheckAccountServiceImp;

/**
 *
 * @author Admin
 */
public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        CheckAccountService checkAccountService = new CheckAccountServiceImp();
        CheckAccountController checkAccountController = CheckAccountController.builder()
                .checkAccountService(checkAccountService)
                .mainFrame(mainFrame)
                .build();
        checkAccountController.init();

        mainFrame.setVisible(true);
    }
}
