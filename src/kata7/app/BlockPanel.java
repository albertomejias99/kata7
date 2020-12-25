package kata7.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata7.view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay{
    private final int max;
    private int x;
    private int y;
    private Moved moved;

    public BlockPanel(int max) {
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);  
        this.max = max;
    }
    
    
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        int c= max*SIZE;
        g.setColor(Color.BLACK);
        for (int i = 0; i <= max; i++){
            int d = i*SIZE;
            g.drawLine(d,0,d,c);
            g.drawLine(0,d,c,d);
        }
        
        g.setColor(Color.red);
        g.fillRect(x, y, SIZE, SIZE);
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }
    
   private class MouseHandler implements MouseListener, MouseMotionListener{
        private boolean pressed = false;
        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if((event.getX() < x) || (event.getY() < y) || (event.getX() > x+SIZE) || (event.getY() > y+SIZE)) return;
            pressed=true;
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            pressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            moved.to(event.getX(), event.getY());
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
       
   }

}
