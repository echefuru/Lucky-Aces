package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * A panel containing a label and a text field on each line. The text fields are aligned.
 */
class LabelTextPanel extends JPanel {
    private final SpringLayout layout = new SpringLayout();
    private int height;
    private int maximumLength;
    private final int padding;
    private final int lineHeight;

    private final List<JComponent[]> lines;

    LabelTextPanel(JLabel label, JTextField textField, int padding, int lineHeight) {
        this.setLayout(layout);
        this.lines = new ArrayList<>();

        this.lines.add(new JComponent[] {label, textField});

        this.padding = padding;
        this.lineHeight = lineHeight;
        this.height = 0;
        this.maximumLength = label.getPreferredSize().width + padding + textField.getPreferredSize().width;
        setComponents(label, textField, this.maximumLength / 2 - label.getPreferredSize().width);

        this.add(label);
        this.add(textField);
        this.setMaximumSize(new Dimension(ViewConstants.WINDOW_WIDTH, this.height));
    }

    public void addLabelText(JLabel label, JTextField textField) {
        this.lines.add(new JComponent[] {label, textField});

        if (updateMaximumLength(label, textField)) {
            height = 0;
            for (JComponent[] line : this.lines) {
                setComponents(line[0], line[1], maximumLength / 2 - label.getPreferredSize().width);
            }
        }
        else {
            setComponents(label, textField, maximumLength / 2 - label.getPreferredSize().width);
        }

        this.add(label);
        this.add(textField);
        this.setMaximumSize(new Dimension(ViewConstants.WINDOW_WIDTH, height));
    }

    private void setComponents(JComponent label, JComponent textField, int offset) {
        layout.putConstraint(SpringLayout.EAST, label, -offset, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, textField, padding - offset, SpringLayout.HORIZONTAL_CENTER, this);
        System.out.println(offset);

        final int topPad = height + lineHeight / 2;
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, label, topPad, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, textField, topPad, SpringLayout.NORTH, this);

        height += lineHeight;
    }

    private boolean updateMaximumLength(JLabel label, JTextField textField) {
        final int currentLength = label.getPreferredSize().width + padding + textField.getPreferredSize().width;
        boolean updated = false;
        if (currentLength > maximumLength) {
            maximumLength = currentLength;
            updated = true;
        }
        return updated;
    }
}
