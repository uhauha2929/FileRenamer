package com.example.parts.action.parts;

import com.example.utils.ResUtil;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatSearchWithHistoryIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public enum ReplacePane {

    INSTANCE;

    private final JPanel replacePane;

    ReplacePane() {
        replacePane = new JPanel(new MigLayout("inset 20", "[][sizegroup 1]", ""));
        JTextField compsTextField = new JTextField();

        // search history button
        JButton searchHistoryButton = new JButton( new FlatSearchWithHistoryIcon( true ) );
        searchHistoryButton.setToolTipText( "搜索历史" );
        searchHistoryButton.addActionListener( e -> {
            JPopupMenu popupMenu = new JPopupMenu();
            popupMenu.add( "(空)" );
            popupMenu.show( searchHistoryButton, 0, searchHistoryButton.getHeight() );
        } );
        compsTextField.putClientProperty( FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT, searchHistoryButton );

        // match case button
        JToggleButton matchCaseButton = new JToggleButton(ResUtil.getSVGIcon("matchCase.svg"));
        matchCaseButton.setRolloverIcon(ResUtil.getSVGIcon("matchCaseHovered.svg"));
        matchCaseButton.setSelectedIcon(ResUtil.getSVGIcon("matchCaseSelected.svg"));
        matchCaseButton.setToolTipText( "区分大小写" );

        // whole words button
        JToggleButton wordsButton = new JToggleButton(ResUtil.getSVGIcon("words.svg"));
        wordsButton.setRolloverIcon(ResUtil.getSVGIcon("wordsHovered.svg"));
        wordsButton.setSelectedIcon(ResUtil.getSVGIcon("wordsSelected.svg"));
        wordsButton.setToolTipText( "整个单词" );

        // regex button
        JToggleButton regexButton = new JToggleButton(ResUtil.getSVGIcon("regex.svg"));
        regexButton.setRolloverIcon(ResUtil.getSVGIcon("regexHovered.svg"));
        regexButton.setSelectedIcon(ResUtil.getSVGIcon("regexSelected.svg"));
        regexButton.setToolTipText( "正则表达式" );

        // search toolbar
        JToolBar searchToolbar = new JToolBar();
        searchToolbar.add( matchCaseButton );
        searchToolbar.add( wordsButton );
        searchToolbar.addSeparator();
        searchToolbar.add( regexButton );
        compsTextField.putClientProperty( FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, searchToolbar );

        // show clear button (if text field is not empty)
        compsTextField.putClientProperty( FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true );

        replacePane.add(compsTextField, "w 242!,span 2,growx,wrap 15");

        replacePane.add(new JLabel("替换为："));
        JTextField keywordField = new JTextField();
        replacePane.add(keywordField, "wrap 15,growx");

        JCheckBox extCheckBox = new JCheckBox("保留原拓展名");
        extCheckBox.setSelected(true);
        replacePane.add(extCheckBox, "span2,growx");
    }

    public JPanel getInstance() {
        return replacePane;
    }
}
