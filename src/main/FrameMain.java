package main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Student;
import util.BrutalForce;
import view.DialogStatus;
import view.PanelLoading;
import view.PanelSignIn;

public class FrameMain extends JFrame {

    // cards' names
    public static final String PANEL_SIGN_IN = "PANEL_SIGN_IN";
    public static final String PANEL_LOADING = "PANEL_LOADING";
    public static final String PANEL_CONFIRM = "PANEL_CONFIRM";
    public static final String PANEL_INFO = "PANEL_INFO";
    public static final String DIALOG_STATUS = "DIALOG_STATUS";

    // main container
    JPanel mainPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();

    // different types of cards (Scenes)
    PanelSignIn panelSignIn;
    PanelLoading panelLoading;

    // map of name -> components
    HashMap<String, Component> componentMap = new HashMap<>();

    public FrameMain() {        
        new Thread(() -> {BrutalForce.killRunningApps();}).start();
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        final int SCREEN_WIDTH = (int) tk.getScreenSize().width;
        final int SCREEN_HEIGHT = (int) tk.getScreenSize().height;
        final int DECK_WIDTH = 1600;
        final int DECK_HEIGHT = 900;

        mainPanel.setLayout(cardLayout);
        mainPanel.setPreferredSize(new Dimension(DECK_WIDTH, DECK_HEIGHT));
        mainPanel.setSize(DECK_WIDTH, DECK_HEIGHT);

        panelSignIn = new PanelSignIn(this);
        panelLoading = new PanelLoading(this);
        addComponent(PANEL_SIGN_IN, panelSignIn);
        addComponent(PANEL_LOADING, panelLoading);

        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.pack();
//        this.setLocationRelativeTo(null);
        this.setLocation(-Math.abs((SCREEN_WIDTH - DECK_WIDTH)) / 2, -Math.abs((SCREEN_HEIGHT - DECK_HEIGHT)) / 2);
        
        changeSceneTo(PANEL_SIGN_IN);
    }

    public void showStatusDialog(Student student, int transactionId) {
        DialogStatus statusDialog = new DialogStatus(this, true, student, transactionId);
        statusDialog.setVisible(true);
    }

    public void hideDown() {
        this.setVisible(false);
        BrutalForce.stop();
    }

    public void showUp() {
        this.setVisible(true);
        BrutalForce.start();
    }

    public void changeSceneTo(String sceneName) {
        cardLayout.show(mainPanel, sceneName);
    }

    public void addComponent(String name, Component component) {
        removeComponent(name);
        mainPanel.add(name, component);
        componentMap.put(name, component);
    }

    public boolean removeComponent(String name) {
        if (componentMap.containsKey(name)) {
            mainPanel.remove(componentMap.get(name));
            componentMap.remove(name);
            return true;
        }
        return false;
    }

    // program's main entry 
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new FrameMain();
            frame.setVisible(true);
            BrutalForce.start();
        });
    }

}
