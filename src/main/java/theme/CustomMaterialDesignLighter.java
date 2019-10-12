package main.java.theme;

import java.awt.*;

public class CustomMaterialDesignLighter {
    private static Color background = new Color(250,250,250);
    private static Color foreground = new Color(84,110,122);
    private static Color text = new Color(148,167,176);
    private static Color selectionBackground = new Color(128,203,196);
    private static Color selectionForeground = new Color(84,110,122);
    private static Color buttons = new Color(243,244,245);
    private static Color secondBackground = new Color(234,232,232);
    private static Color disabled = new Color(210,212,213);
    private static Color contrast = new Color(244,244,244);
    private static Color active = new Color(231,231,232);
    private static Color border = new Color(211,225,232);
    private static Color highlight = new Color(231,231,232);
    private static Color tree = new Color(128,203,196);
    private static Color notifications = new Color(234,232,232);
    private static Color accent = new Color(0,188,212);
    private static Color excludedFiles = new Color(234,232,232);
    private static Color comments = new Color(170,191,201);
    private static Color variable = new Color(255,255,255);
    private static Color links = new Color(57,173,181);
    private static Color functions = new Color(97,130,184);
    private static Color keywords = new Color(124,77,255);
    private static Color tags = new Color(229,57,53);
    private static Color strings = new Color(145,184,89);
    private static Color operators = new Color(57,173,181);
    private static Color attributes = new Color(246,164,52);
    private static Color numbers = new Color(247,109,71);
    private static Color parameters = new Color(247,109,71);

    public static Theme getTheme() {
        return new Theme(background, foreground, text, selectionBackground, selectionForeground, buttons, secondBackground, disabled, contrast, active, border, highlight, tree, notifications, accent, excludedFiles, comments, variable, links, functions, keywords, tags, strings, operators, attributes, numbers, parameters);
    }
}
