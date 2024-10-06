package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

import static entity.Player.ROCKET_SIZE;
import static entity.Player.money;

public class ShopWindow extends JFrame {
    private static final int MAX_HEALTH_COST = 100;
    private static final int MAX_ROCKET_COST = 100;
    private static final int MAX_NUCLEAR_BOMB_COST = 5000;

    private JLabel moneyLabel;
    private JSlider healthSlider;
    private JSlider rocketSlider;
    private JSlider nuclearBombSlider;
    private JLabel healthCountLabel; // Label for health count
    private JLabel rocketCountLabel; // Label for rocket count
    private JLabel nuclearBombCountLabel; // Label for nuclear bomb count

    public ShopWindow() {
        setTitle("Shop");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        moneyLabel = new JLabel("Money: $" + money);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(moneyLabel, gbc);

        // Health slider
        JLabel healthLabel = new JLabel("Health - $100 each");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(healthLabel, gbc);

        healthSlider = createSlider(MAX_HEALTH_COST);
        healthCountLabel = new JLabel("0"); // Initialize label for health count
        gbc.gridx = 1;
        panel.add(healthSlider, gbc);
        gbc.gridx = 2; // Position the count label next to the slider
        panel.add(healthCountLabel, gbc);

        healthSlider.addChangeListener(e -> healthCountLabel.setText(String.valueOf(healthSlider.getValue())));

        // Rocket slider
        JLabel rocketLabel = new JLabel("Rockets - $100 each");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(rocketLabel, gbc);

        rocketSlider = createSlider(MAX_ROCKET_COST);
        rocketCountLabel = new JLabel("0"); // Initialize label for rocket count
        gbc.gridx = 1;
        panel.add(rocketSlider, gbc);
        gbc.gridx = 2; // Position the count label next to the slider
        panel.add(rocketCountLabel, gbc);

        rocketSlider.addChangeListener(e -> rocketCountLabel.setText(String.valueOf(rocketSlider.getValue())));

        // Nuclear bomb slider
        JLabel nuclearBombLabel = new JLabel("Nuclear Bomb - $5000 each");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(nuclearBombLabel, gbc);

        nuclearBombSlider = createSlider(MAX_NUCLEAR_BOMB_COST);
        nuclearBombCountLabel = new JLabel("0"); // Initialize label for nuclear bomb count
        gbc.gridx = 1;
        panel.add(nuclearBombSlider, gbc);
        gbc.gridx = 2; // Position the count label next to the slider
        panel.add(nuclearBombCountLabel, gbc);

        nuclearBombSlider.addChangeListener(e -> nuclearBombCountLabel.setText(String.valueOf(nuclearBombSlider.getValue())));

        JButton purchaseButton = new JButton("Purchase");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3; // Update grid width for button
        panel.add(purchaseButton, gbc);

        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePurchase();
                dispose(); // Ascunde fereastra în loc să o închizi complet
            }
        });

        add(panel);
    }

    private JSlider createSlider(int costPerItem) {
        int maxItems = money / costPerItem;
        int majorTickSpacing = Math.max(1, maxItems / 10);
        JSlider slider = new JSlider(0, maxItems, 0);
        slider.setPreferredSize(new Dimension(200, 50));
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMinorTickSpacing(majorTickSpacing / 2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        setSliderLabelMargin(slider);
        return slider;
    }

    private void setSliderLabelMargin(JSlider slider) {
        Hashtable<Integer, JComponent> labelTable = slider.createStandardLabels(slider.getMajorTickSpacing());
        Enumeration<Integer> keys = labelTable.keys();
        while (keys.hasMoreElements()) {
            Integer key = keys.nextElement();
            JComponent label = labelTable.get(key);
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        }
        slider.setLabelTable(labelTable);
    }

    private void handlePurchase() {
        int healthCount = healthSlider.getValue();
        int rocketCount = rocketSlider.getValue();
        int nuclearBombCount = nuclearBombSlider.getValue();
        int totalCost = (healthCount * MAX_HEALTH_COST) + (rocketCount * MAX_ROCKET_COST) + (nuclearBombCount * MAX_NUCLEAR_BOMB_COST);
        if (totalCost <= money) {
            money -= totalCost;
            moneyLabel.setText("Money: $" + money);
            Player.rocketsinMagazine += rocketCount;
            if (Player.rocketsinMagazine > ROCKET_SIZE) {
                ROCKET_SIZE = Player.rocketsinMagazine;
            }
            JOptionPane.showMessageDialog(this, "Purchase successful!");
            updateSliders();
        } else {
            JOptionPane.showMessageDialog(this, "Not enough money!");
        }
    }

    private void updateSliders() {
        if (money >= MAX_HEALTH_COST) {
            healthSlider.setMaximum(money / MAX_HEALTH_COST);
        } else {
            healthSlider.setMaximum(0);
        }

        if (money >= MAX_ROCKET_COST) {
            rocketSlider.setMaximum(money / MAX_ROCKET_COST);
        } else {
            rocketSlider.setMaximum(0);
        }

        if (money >= MAX_NUCLEAR_BOMB_COST) {
            nuclearBombSlider.setMaximum(money / MAX_NUCLEAR_BOMB_COST);
        } else {
            nuclearBombSlider.setMaximum(0);
        }

        healthSlider.setValue(0);
        rocketSlider.setValue(0);
        nuclearBombSlider.setValue(0);

        healthCountLabel.setText("0");
        rocketCountLabel.setText("0");
        nuclearBombCountLabel.setText("0");
    }
}
