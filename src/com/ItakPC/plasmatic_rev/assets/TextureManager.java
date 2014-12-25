package com.ItakPC.plasmatic_rev.assets;

import javax.swing.*;
import java.awt.*;

public class TextureManager {

    public static Image loadTexture(String resourceId) {
        return new ImageIcon("res/"+ resourceId + ".png").getImage();
    }
}
