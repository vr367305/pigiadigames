/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import gioco.Game;
import java.awt.Font;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author Marco
 */
public class Window {

    public static boolean debug;
    public static Settings.DebugColor debugColor = Settings.DebugColor.Normal;
    public static int w, h;
    public static Tester game;
    public static Game game2;
    public static Vec2 mSpace;
    public static float conv, invconv;
    public static Float[] bounds = new Float[4];
    public static Float[] unfixedBounds = new Float[4];

    public static boolean initialise(int width, int heigth) {
        try {
            Display.setDisplayMode(new DisplayMode(width, heigth));
            w = width;
            h = heigth;


            return true;
        } catch (LWJGLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static World getWorld() {
        return Window.game2.world;
    }

    public static void debugDrawLine(float x, float y, float fx, float fy) {
        if (debug) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 0, 0);
            Color.yellow.bind();
            GL11.glBegin(GL11.GL_LINES);
            {
                GL11.glVertex2d(x, y);
                GL11.glVertex2d(fx, fy);
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        }
    }

    public static void debugDrawStaticString(float x, float y, String text, int font) {
        if (debug) {
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(x, y, 0);
                GL11.glScalef(0.03f, -0.03f, 0);
                GL11.glEnable(GL11.GL_BLEND);
                TextureImpl.bindNone();
                FontHandler.getFont(font).drawString(0, 0, text, Color.white);
                GL11.glDisable(GL11.GL_BLEND);
            }
            GL11.glPopMatrix();
        }
    }

    public static void debugDrawHudString(float x, float y, String text, int font) {

        GL11.glPushMatrix();
        {
            GL11.glTranslatef(unfixedBounds[0]+x, unfixedBounds[3]-y, 0);
            GL11.glScalef(0.03f, -0.03f, 0);
            GL11.glEnable(GL11.GL_BLEND);
            TextureImpl.bindNone();
            FontHandler.getFont(font).drawString(0, 0, text, Color.white);
            GL11.glDisable(GL11.GL_BLEND);
        }
        GL11.glPopMatrix();

    }

    public static void debugDrawLine(float x, float y, float fx, float fy, Color color) {
        if (debug) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 0, 0);
            color.bind();
            GL11.glBegin(GL11.GL_LINES);
            {
                GL11.glVertex2d(x, y);
                GL11.glVertex2d(fx, fy);
            }
            GL11.glEnd();
            GL11.glPopMatrix();
        }
    }

    public static void setMeterSpace(Vec2 value) {
        Window.mSpace = value;
        bounds[0] = -Window.mSpace.x / 2.0f;
        bounds[1] = Window.mSpace.x / 2.0f;
        bounds[2] = -Window.mSpace.y / 2.0f;
        bounds[3] = Window.mSpace.y / 2.0f;
        conv = w / mSpace.x;
        invconv = mSpace.x / w;
    }

    public static void setMeterSpace(float x, float y) {
        Window.mSpace = new Vec2(x, y);
        bounds[0] = -Window.mSpace.x / 2.0f;
        bounds[1] = Window.mSpace.x / 2.0f;
        bounds[2] = -Window.mSpace.y / 2.0f;
        bounds[3] = Window.mSpace.y / 2.0f;
        conv = w / mSpace.x;
        invconv = mSpace.x / w;
    }

    public static Float[] getBoundaries() {
        bounds[0] = -Window.mSpace.x / 2.0f;
        bounds[1] = Window.mSpace.x / 2.0f;
        bounds[2] = -Window.mSpace.y / 2.0f;
        bounds[3] = Window.mSpace.y / 2.0f;
        return bounds;
    }

    public static void setUnfixedBoundaries(float x, float fx, float y, float fy) {
        unfixedBounds[0] = x;
        unfixedBounds[1] = y;
        unfixedBounds[2] = fx;
        unfixedBounds[3] = fy;
    }
}
