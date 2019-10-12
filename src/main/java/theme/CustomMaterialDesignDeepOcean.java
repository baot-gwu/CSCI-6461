package main.java.theme;

import java.awt.*;

public class CustomMaterialDesignDeepOcean {
    private static Color background = new Color(15,17,26);
    private static Color foreground = new Color(143,147,162);
    private static Color text = new Color(75,82,109);
    private static Color selectionBackground = new Color(35,38,50);
    private static Color selectionForeground = new Color(255,255,255);
    private static Color buttons = new Color(25,26,33);
    private static Color secondBackground = new Color(24,26,31);
    private static Color disabled = new Color(70,75,93);
    private static Color contrast = new Color(9,11,16);
    private static Color active = new Color(26,28,37);
    private static Color border = new Color(15,17,26);
    private static Color highlight = new Color(31,34,51);
    private static Color tree = new Color(113,124,180);
    private static Color notifications = new Color(9,11,16);
    private static Color accent = new Color(132,255,255);
    private static Color excludedFiles = new Color(41,45,62);
    private static Color comments = new Color(113,124,180);
    private static Color variable = new Color(255,255,255);
    private static Color links = new Color(128,203,196);
    private static Color functions = new Color(130,170,255);
    private static Color keywords = new Color(199,146,234);
    private static Color tags = new Color(240,113,120);
    private static Color strings = new Color(195,232,141);
    private static Color operators = new Color(137,221,255);
    private static Color attributes = new Color(255,203,107);
    private static Color numbers = new Color(247,140,108);
    private static Color parameters = new Color(245,151,98);

    public static Theme getTheme() {
        return new Theme(background, foreground, text, selectionBackground, selectionForeground, buttons, secondBackground, disabled, contrast, active, border, highlight, tree, notifications, accent, excludedFiles, comments, variable, links, functions, keywords, tags, strings, operators, attributes, numbers, parameters);
    }
}
