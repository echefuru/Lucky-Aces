package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * A panel containing two components on each line.
 * The left component is right-aligned at the center, and
 * the right component is left-aligned at the center.
 */
class CenterAlignedPanel extends JPanel {
    private final SpringLayout layout = new SpringLayout();
    private int height;
    private int maximumLength;
    private final int padding;

    private final List<JComponent[]> lines;

    CenterAlignedPanel(int padding, int lineHeight) {
        this.setLayout(this.layout);

        this.height = 0;
        this.maximumLength = 0;

        this.padding = padding;

        this.lines = new ArrayList<>();
    }

    public void addNewLine(JComponent left, JComponent right) {
        this.lines.add(new JComponent[] {left, right});

        if (updateMaximumLength(left, right)) {
            height = 0;
            for (JComponent[] line : this.lines) {
                final int offset = maximumLength / 2 - right.getPreferredSize().width;
                setComponents(line[0], line[1], offset);
            }
        }
        else {
            final int offset = maximumLength / 2 - right.getPreferredSize().width;
            setComponents(left, right, offset);
        }

        this.add(left);
        this.add(right);
        this.setPreferredSize(new Dimension(this.maximumLength + padding, this.height));
    }

    private void setComponents(JComponent left, JComponent right, int offset) {
        final int lineHeight = Math.max(left.getPreferredSize().height, right.getPreferredSize().height);

        layout.putConstraint(SpringLayout.EAST, left, offset, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.WEST, right, padding + offset, SpringLayout.HORIZONTAL_CENTER, this);

        final int topPad = height + lineHeight / 2;
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, left, topPad, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, right, topPad, SpringLayout.NORTH, this);

        height += lineHeight;
    }

    private boolean updateMaximumLength(JComponent left, JComponent right) {
        final int currentLength = left.getPreferredSize().width + padding + right.getPreferredSize().width;
        boolean updated = false;
        if (currentLength > maximumLength) {
            maximumLength = currentLength;
            updated = true;
        }
        return updated;
    }
}
