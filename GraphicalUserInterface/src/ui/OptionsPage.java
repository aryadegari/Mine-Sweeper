package ui;

import data.*;
import javafx.util.Pair;
import utils.UserInterfaceConsts;
import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hp on 08/02/2016.
 */
public class OptionsPage {

    private static final Pair<Integer, Integer> WIDTH_MIN_MAX = new Pair<>(10, 20);
    private static final Pair<Integer, Integer> HEIGHT_MIN_MAX = new Pair<>(10, 20);
    private static final Pair<Integer, Integer> MINES_MIN_MAX = new Pair<>(10, WIDTH_MIN_MAX.getValue() * HEIGHT_MIN_MAX.getValue());
    private static JFrame frame;
    private JPanel mainPanel;
    private JRadioButton beginnerRadioButton;
    private JRadioButton intermediateRadioButton;
    private JRadioButton expertRadioButton;
    private JRadioButton customRadioButton;
    private JTextField customWidthTextField;
    private JTextField customHeightTextField;
    private JTextField customMinesTextField;
    private JComboBox<SettingsData.Theme> themesComboBox;
    private JComboBox<SettingsData.Language> languagesComboBox;
    private JCheckBox playSoundsCheckBox;
    private JButton saveButton;
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JLabel minesLabel;
    private ButtonGroup levelsRadioGroup;

    public OptionsPage() {

        //TODO: delete next line for release
        setAccountToGuest();

        initComponents();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean sound = playSoundsCheckBox.isSelected();
                SettingsData.Language language = (SettingsData.Language) languagesComboBox.getSelectedItem();
                SettingsData.Theme theme = (SettingsData.Theme) themesComboBox.getSelectedItem();
                GameLevel gameLevel = radioGroupToGameLevel(levelsRadioGroup);
                if (gameLevel == null) return;
                SettingsData settings = new SettingsData(language, sound, gameLevel, theme);
                SettingsManager.getInstance().save(settings);
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {

        frame = new JFrame("Options");
        frame.setContentPane(new OptionsPage().mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        ScreenUtils.setCenterLocation(frame);
        frame.setVisible(true);
    }

    private void setAccountToGuest() {
        AccountManager accountManager = AccountManager.getInstance();
        accountManager.setAccountInfo(new AccountInfo("username17", "username17"));
    }

    private void initComponents() {

        // make group of levels radio buttons
        levelsRadioGroup = new ButtonGroup();
        levelsRadioGroup.add(beginnerRadioButton);
        levelsRadioGroup.add(intermediateRadioButton);
        levelsRadioGroup.add(expertRadioButton);
        levelsRadioGroup.add(customRadioButton);

        LevelBase[] levelsObjs = {new BeginnerLevel(), new IntermediateLevel(), new ExpertLevel()};
        JRadioButton[] levelRadioButtons = {beginnerRadioButton, intermediateRadioButton, expertRadioButton};
        // show width, height & mines of levels
        for (int i = 0; i < levelsObjs.length; i++) {
            LevelBase obj = levelsObjs[i];
            levelRadioButtons[i].setText(levelRadioButtons[i].getText()+" (" + obj.rows + " X " + obj.cols + " Mines: " + obj.mines + ")");
        }

        // disable custom fields when custom is not selected
        customRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                boolean customEnable = customRadioButton.isSelected();
                customWidthTextField.setEnabled(customEnable);
                customHeightTextField.setEnabled(customEnable);
                customMinesTextField.setEnabled(customEnable);
            }
        });

        // show min max values for custom fields
        widthLabel.setText(widthLabel.getText() + " (" + WIDTH_MIN_MAX.getKey() + "-" + WIDTH_MIN_MAX.getValue() + ")");
        heightLabel.setText(heightLabel.getText() + " (" + HEIGHT_MIN_MAX.getKey() + "-" + HEIGHT_MIN_MAX.getValue() + ")");
        minesLabel.setText(minesLabel.getText() + " (" + MINES_MIN_MAX.getKey() + "-" + MINES_MIN_MAX.getValue() + ")");

        // set models for combo boxes
        themesComboBox.setModel(new DefaultComboBoxModel<>(SettingsData.Theme.values()));
        languagesComboBox.setModel(new DefaultComboBoxModel<>(SettingsData.Language.values()));

        // load saved settings data
        SettingsData settingsData = SettingsManager.getInstance().load();
        gameLevelToRadioButton(settingsData.gameLevel).setSelected(true);
        playSoundsCheckBox.setSelected(settingsData.sound);
        languagesComboBox.setSelectedItem(settingsData.language);
        themesComboBox.setSelectedItem(settingsData.theme);
    }

    private JRadioButton gameLevelToRadioButton(GameLevel gameLevel) {

        String gameLevelClass = gameLevel.level.getClass().getName();
        if (BeginnerLevel.class.getName().equals(gameLevelClass)) {
            return beginnerRadioButton;
        } else if (IntermediateLevel.class.getName().equals(gameLevelClass)) {
            return intermediateRadioButton;
        } else if (ExpertLevel.class.getName().equals(gameLevelClass)) {
            return expertRadioButton;
        } else {
            customWidthTextField.setText(String.valueOf(gameLevel.level.cols));
            customHeightTextField.setText(String.valueOf(gameLevel.level.rows));
            customMinesTextField.setText(String.valueOf(gameLevel.level.mines));
            return customRadioButton;
        }
    }

    private GameLevel radioGroupToGameLevel(ButtonGroup group) {

        resetCustomLabels();

        ButtonModel selectionModel = group.getSelection();
        if (beginnerRadioButton.getModel().equals(selectionModel)) {
            return new GameLevel(new BeginnerLevel());
        } else if (intermediateRadioButton.getModel().equals(selectionModel)) {
            return new GameLevel(new IntermediateLevel());
        } else if (expertRadioButton.getModel().equals(selectionModel)) {
            return new GameLevel(new ExpertLevel());
        } else {
            CustomLevel level = new CustomLevel();
            int width = 0;
            int height = 0;
            int mines = 0;
            boolean hasError = false;
            try {
                width = Integer.valueOf(customWidthTextField.getText());
                if (width < WIDTH_MIN_MAX.getKey() || width > WIDTH_MIN_MAX.getValue()) {
                    throw new Exception("Out of bounds");
                }
            } catch (Exception e) {
                alertCustomTextField(customWidthTextField, widthLabel);
                hasError = true;
            }

            try {
                height = Integer.valueOf(customHeightTextField.getText());
                if (height < HEIGHT_MIN_MAX.getKey() || height > HEIGHT_MIN_MAX.getValue()) {
                    throw new Exception("Out of bounds");
                }
            } catch (Exception e) {
                alertCustomTextField(customHeightTextField, heightLabel);
                hasError = true;
            }

            try {
                mines = Integer.valueOf(customMinesTextField.getText());
                if (mines < MINES_MIN_MAX.getKey() || mines > MINES_MIN_MAX.getValue() || mines >= width * height) {
                    throw new Exception("Out of bounds");
                }
            } catch (Exception e) {
                alertCustomTextField(customMinesTextField, minesLabel);
                hasError = true;
            }

            if (hasError) return null;

            level.rows = height;
            level.cols = width;
            level.mines = mines;

            return new GameLevel(level);
        }
    }

    private void alertCustomTextField(JTextField textField, JLabel label) {
        textField.setForeground(UserInterfaceConsts.ERROR_COLOR);
        label.setForeground(UserInterfaceConsts.ERROR_COLOR);
    }

    private void resetCustomLabels() {
        customWidthTextField.setForeground(Color.BLACK);
        customHeightTextField.setForeground(Color.BLACK);
        customMinesTextField.setForeground(Color.BLACK);
        widthLabel.setForeground(Color.BLACK);
        heightLabel.setForeground(Color.BLACK);
        minesLabel.setForeground(Color.BLACK);
    }
}
