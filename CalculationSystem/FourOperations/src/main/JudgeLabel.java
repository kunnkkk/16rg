package main;

import javax.swing.*;
import java.awt.*;

public class JudgeLabel extends JLabel{
    private String imageUrl;

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon(imageUrl);
        System.out.println("xxx");
        g.drawImage(icon.getImage(), 0, 0, getWidth(),getHeight(), icon.getImageObserver());
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
