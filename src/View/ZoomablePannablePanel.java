package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import static java.lang.Math.min;

/**
 * Class that displays and image that can be zoomed into and panned with a mouse.
 *
 * This class is inspired and build upon a class located at:
 * https://github.com/Thanasis1101/Zoomable-Java-Panel/blob/master/src/zoomable/panel/MainPanel.java
 *
 * @author Thanasis1101
 * @version 1.0
 */
public class ZoomablePannablePanel extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {

    private final Image image;

    private double zoomFactor;
    private double prevZoomFactor;
    private boolean isZooming;
    private boolean isDragging;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;
    private boolean firstPaint;
    private final double minZoomFactor;
    private final double maxZoomFactor;

    public ZoomablePannablePanel(Image image, double size, double minZoom, double maxZoom) {

        firstPaint = true;

        double height = image.getHeight(this);
        double width = image.getWidth(this);

        double minimum = min(height, width);

        prevZoomFactor = size / minimum;
        zoomFactor = prevZoomFactor;

        minZoomFactor = minZoom;
        maxZoomFactor = maxZoom;

        this.image = image;
        this.setBackground(Color.white);
        initComponent();
    }

    private void initComponent() {
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (firstPaint) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);
            firstPaint = false;
        }

        if (isZooming) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            isZooming = false;
        }

        if (isDragging) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                isDragging = false;
            }

        }

        // All drawings go here
        g2.drawImage(image, 0, 0, this);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        isZooming = true;

        //Zoom in
        if (e.getWheelRotation() < 0 && zoomFactor < maxZoomFactor) {
            zoomFactor *= 1.1;
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0 && zoomFactor > minZoomFactor) {
            zoomFactor /= 1.1;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getLocationOnScreen();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;

        isDragging = true;
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        released = false;
        startPoint = MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        released = true;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}