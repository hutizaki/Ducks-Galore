package com.hutizaki.ducksgalore.util;

/**
 * Helper methods for color-related functionality
 */
public class ColorHelper {
    
    /**
     * Create a packed RGB color from individual components
     */
    public static int getPackedRGB(int red, int green, int blue) {
        return (red << 16) | (green << 8) | blue;
    }
    
    /**
     * Create a packed RGBA color from individual components
     */
    public static int getPackedRGBA(int red, int green, int blue, int alpha) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
    
    /**
     * Extract the red component from a packed RGB(A) color
     */
    public static int getRed(int packedColor) {
        return (packedColor >> 16) & 0xFF;
    }
    
    /**
     * Extract the green component from a packed RGB(A) color
     */
    public static int getGreen(int packedColor) {
        return (packedColor >> 8) & 0xFF;
    }
    
    /**
     * Extract the blue component from a packed RGB(A) color
     */
    public static int getBlue(int packedColor) {
        return packedColor & 0xFF;
    }
    
    /**
     * Extract the alpha component from a packed RGBA color
     */
    public static int getAlpha(int packedColor) {
        return (packedColor >> 24) & 0xFF;
    }
    
    /**
     * Interpolate between two colors
     */
    public static int interpolateColors(int color1, int color2, float factor) {
        int r1 = getRed(color1);
        int g1 = getGreen(color1);
        int b1 = getBlue(color1);
        int a1 = getAlpha(color1);
        
        int r2 = getRed(color2);
        int g2 = getGreen(color2);
        int b2 = getBlue(color2);
        int a2 = getAlpha(color2);
        
        int r = (int) (r1 + factor * (r2 - r1));
        int g = (int) (g1 + factor * (g2 - g1));
        int b = (int) (b1 + factor * (b2 - b1));
        int a = (int) (a1 + factor * (a2 - a1));
        
        return getPackedRGBA(r, g, b, a);
    }
} 