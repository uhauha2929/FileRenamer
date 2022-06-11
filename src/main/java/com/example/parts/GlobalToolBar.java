package com.example.parts;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public enum GlobalToolBar {

    INSTANCE;

    private final JToolBar jToolBar;

    GlobalToolBar() {
        jToolBar = new JToolBar();
        jToolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(209,209,209)));
        JButton sortBtn = new JButton();
        sortBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/sort.svg"))));
        sortBtn.setToolTipText("排序方式");
        jToolBar.add(sortBtn);
        jToolBar.addSeparator();
        JButton addBtn = new JButton();
        addBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/add.svg"))));
        addBtn.setToolTipText("添加文件");
        jToolBar.add(addBtn);
        JButton removeBtn = new JButton();
        removeBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/remove.svg"))));
        removeBtn.setToolTipText("移除文件");
        jToolBar.add(removeBtn);
        JButton clearBtn = new JButton();
        clearBtn.setIcon(new FlatSVGIcon(Objects.requireNonNull(this.getClass().getResource("/icons/clear.svg"))));
        clearBtn.setToolTipText("清除所有");
        jToolBar.add(clearBtn);
    }

    public JToolBar getInstance() {
        return jToolBar;
    }
}
