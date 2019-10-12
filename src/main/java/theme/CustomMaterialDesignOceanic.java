package main.java.theme;

import java.awt.*;

public class CustomMaterialDesignOceanic {
    private static Color background = new Color(38,50,56);
    private static Color foreground = new Color(176,190,197);
    private static Color text = new Color(96,125,139);
    private static Color selectionBackground = new Color(84,110,122);
    private static Color selectionForeground = new Color(255,255,255);
    private static Color buttons = new Color(46,60,67);
    private static Color secondBackground = new Color(50,66,74);
    private static Color disabled = new Color(65,89,103);
    private static Color contrast = new Color(30,39,44);
    private static Color active = new Color(49,69,73);
    private static Color border = new Color(42,55,62);
    private static Color highlight = new Color(66,91,103);
    private static Color tree = new Color(84,110,122);
    private static Color notifications = new Color(30,39,44);
    private static Color accent = new Color(0,150,136);
    private static Color excludedFiles = new Color(46,60,67);
    private static Color comments = new Color(84,110,122);
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
