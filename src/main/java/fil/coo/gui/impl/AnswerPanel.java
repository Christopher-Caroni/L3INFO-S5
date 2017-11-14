package fil.coo.gui.impl;

import fil.coo.controller.IAnswerController;
import fil.coo.gui.AbstractAnswerView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

/**
 * Wrapper for a {@link JPanel} to further customise according to the specific type of answer.
 */
public abstract class AnswerPanel extends AbstractAnswerView {

    protected final List<String> possibleAnswers;
    protected JPanel rootPanel;

    public AnswerPanel(IAnswerController answerController, List<String> possibleAnswers) {
        super(answerController);
        this.possibleAnswers = possibleAnswers;

        rootPanel = new JPanel();

        initCustomView();
    }

    protected abstract void initCustomView();

    @Override
    public void add(Component component) {
        rootPanel.add(component);
    }

    @Override
    public void setBorder(Border border) {
        rootPanel.setBorder(border);
    }

    @Override
    public Component getView() {
        return rootPanel;
    }
}
