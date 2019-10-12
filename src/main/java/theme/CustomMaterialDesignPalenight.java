package main.java.theme;

import java.awt.*;

public class CustomMaterialDesignPalenight {
    private static Color background = new Color(41,45,62);
    private static Color foreground = new Color(166,172,205);
    private static Color text = new Color(103,110,149);
    private static Color selectionBackground = new Color(60,67,94);
    private static Color selectionForeground = new Color(255,255,255);
    private static Color buttons = new Color(48,51,72);
    private static Color secondBackground = new Color(52,50,74);
    private static Color disabled = new Color(81,87,114);
    private static Color contrast = new Color(32,35,49);
    private static Color active = new Color(65,72,99);
    private static Color border = new Color(43,42,62);
    private static Color highlight = new Color(68,66,103);
    private static Color tree = new Color(103,110,149);
    private static Color notifications = new Color(32,35,49);
    private static Color accent = new Color(171,71,188);
    private static Color excludedFiles = new Color(47,46,67);
    private static Color comments = new Color(103,110,149);
    private static Color variable = new Color(255,255,255);
    private static Color links = new Color(128,203,196);
    private static Color functions = new Color(130,170,255);
    private static Color keywords = new Color(199,146,234);
    private static Color tags = new Color(240,113,120);
    private static Color strings = new Color(195,232,141);
    private static Color operators = new Color(137,221,255);
    private static Color attributes = new Color(255,203,107);
    private static Color numbers = new Color(247,140,108);
    private static Color parameters = new Color(247,140,108);

    public static Theme getTheme() {
        return new Theme(background, foreground, text, selectionBackground, selectionForeground, buttons, secondBackground, disabled, contrast, active, border, highlight, tree, notifications, accent, excludedFiles, comments, variable, links, functions, keywords, tags, strings, operators, attributes, numbers, parameters);
    }
}
