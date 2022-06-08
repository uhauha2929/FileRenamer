package com.example.parts.bottom.listeners;

import com.example.parts.action.ActionPane;
import com.example.parts.action.parts.*;
import com.example.parts.bottom.parts.ComboBoxItem;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 底部下拉框选择监听，用于切换右侧功能面板
 */
public class FunctionListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
        // 只对选中时触发
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JPanel actionPane = ActionPane.INSTANCE.getInstance();
            actionPane.removeAll(); // 移除所有组件
            ComboBoxItem comboBoxItem = (ComboBoxItem) e.getItem();
            if ("sequence".equals(comboBoxItem.getId())) {
                actionPane.add(SequencePane.INSTANCE.getInstance());
            } else if ("extension".equals(comboBoxItem.getId())) {
                actionPane.add(ExtensionPane.INSTANCE.getInstance());
            } else if ("replace".equals(comboBoxItem.getId())) {
                actionPane.add(ReplacePane.INSTANCE.getInstance());
            } else if ("insert".equals(comboBoxItem.getId())) {
                actionPane.add(InsertPane.INSTANCE.getInstance());
            } else if ("remove".equals(comboBoxItem.getId())) {
                actionPane.add(RemovePane.INSTANCE.getInstance());
            } else if ("prefix".equals(comboBoxItem.getId())) {
                actionPane.add(PrefixPane.INSTANCE.getInstance());
            } else if ("suffix".equals(comboBoxItem.getId())) {
                actionPane.add(SuffixPane.INSTANCE.getInstance());
            } else {
                actionPane.add(CasePane.INSTANCE.getInstance());
            }
            actionPane.repaint();
            actionPane.revalidate();
        }
    }
}
