package com.video.testui.controller;

import com.video.testui.MainFrame;
import com.video.testui.model.AccountStatus;
import com.video.testui.model.CheckAccountModel;
import com.video.testui.service.CheckAccountService;
import lombok.Builder;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


@Builder
public class CheckAccountController {
    private CheckAccountService checkAccountService;
    private MainFrame mainFrame;

    public void init(){
        mainFrame.getBtn_check().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread(() -> checkLiveDieIDs()).start();
            }
        });

        mainFrame.getBtn_get_id_selected().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getAndSetSelectedIDs();
            }
        });

        mainFrame.getBtn_get_spinner_val().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getValSpinner();
            }
        });

        mainFrame.getToggle().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {
                if(ev.getStateChange()==ItemEvent.SELECTED){
                    mainFrame.getCb_nu().setSelected(true);
                    mainFrame.getToggle().setText("Check Nam");
                } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                    mainFrame.getCb_nam().setSelected(true);
                    mainFrame.getToggle().setText("Check Nu");
                }
            }
        });

        mainFrame.getBtn_check_gt().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkMaleOrFemale();
            }
        });
    }

    private void checkLiveDieIDs(){
        // lay input tu ui
        String[] ids = mainFrame.getIds();

        // Goi xuong service de check id
        int index = 0;
        for (String id : ids) {
            AccountStatus accountStatus = checkAccountService.isValid(id);
            CheckAccountModel checkAccountModel = CheckAccountModel.builder()
                    .ID(id)
                    .status(accountStatus)
                    .build();
            mainFrame.fillDataTable(++index, checkAccountModel);
        }
    }

    private void getAndSetSelectedIDs(){
        List<String> idsSelected = mainFrame.getIdsSelected(1);
        if (!idsSelected.isEmpty()){
            mainFrame.setIdsSelected(idsSelected);
        }
    }

    private void getValSpinner(){
        Integer val = Integer.parseInt(this.mainFrame.getSpinner_test().getValue().toString());
        JOptionPane.showMessageDialog(mainFrame, "Value set "+val);
    }

    private void checkMaleOrFemale(){
        if (mainFrame.getCb_nam().isSelected()){
            JOptionPane.showMessageDialog(mainFrame, mainFrame.getCb_nam().getText());
        }else{
            JOptionPane.showMessageDialog(mainFrame, mainFrame.getCb_nu().getText());
        }
    }

}
