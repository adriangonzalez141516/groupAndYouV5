

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.AbstractButton;
import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Pedro Cardoso Rdz
 * @version 0.23
 */
public class JOvalBtn extends JButton{
    
    /**
     * size behavior, if true ignores xDiameter and yDiameter and uses width and heigth for size or from layout behavior
     *   if false will force the dimension to xDiameter and yDiameter overriden layout behavior
     */
    private boolean autoSize;
    /**
     * diameter on x axis or heigth
     */
    private int xDiameter;
    /**
     * diameter on y axis or width
     */
    private int yDiameter;
    /**
     * Color to paint the border
     */
    private Color borderColor;
    /**
     * Color to paint the border when JOvalbutton has the focus
     */
    private Color borderFocusColor;
    private int wUsed;
    private int hUsed;
    private int xIni;
    private int yIni;
    private static final String uiClassID = "ButtonUI";
    private java.awt.Shape shape;  

    public JOvalBtn(){
        autoSize=true;
        jobInitialize();
    }
    
    /**
     * @param xDiameter diameter on x axis or heigth
     * @param yDiameter diameter on y axis or width
     */
    public JOvalBtn(int xDiameter, int yDiameter){
        this.xDiameter=xDiameter;
        this.yDiameter=yDiameter;
        autoSize=false;
        setPreferredSize(new Dimension(xDiameter,yDiameter));
        setMaximumSize(new Dimension(xDiameter,yDiameter));
        setMinimumSize(new Dimension(xDiameter,yDiameter));
        jobInitialize();
    }
    
    /** Sets preferred diameter measures
     * @param xDiameter diameter on x axis or heigth
     * @param yDiameter diameter on y axis or width
     */
    public void setPreferredDiameter(int xDiameter, int yDiameter){
        this.xDiameter=xDiameter;
        this.yDiameter=yDiameter;
        autoSize=false; 
        setPreferredSize(new Dimension(xDiameter,yDiameter));
        setMaximumSize(new Dimension(xDiameter,yDiameter));
        setMinimumSize(new Dimension(xDiameter,yDiameter)); 
        repaint();
    }

    /**
     * 
     * @param xDiameter diameter on x axis or heigth
     * @param yDiameter diameter on y axis or width
     * @param borderColor Color to paint the border
     * @param borderFocusColor Color to paint the border when JOvalbutton has the focus
     */
    public JOvalBtn(int xDiameter, int yDiameter, Color borderColor, Color borderFocusColor) {
        this.xDiameter = xDiameter;
        this.yDiameter = yDiameter;
        this.borderColor = borderColor;
        this.borderFocusColor = borderFocusColor;
    }

    /**
     * 
     * @param borderColor Color to paint the border
     * @param borderFocusColor Color to paint the border when JOvalbutton has the focus
     */
    public JOvalBtn(Color borderColor, Color borderFocusColor) {
        this.borderColor = borderColor;
        this.borderFocusColor = borderFocusColor;
    }

    public boolean isAutoSize() {
        return autoSize;
    }

    public void setAutoSize(boolean autoSize) {
        this.autoSize = autoSize;
    }

    public int getXDiameter() {
        return xDiameter;
    }

    public void setXDiameter(int xDiameter) {
        this.xDiameter = xDiameter;
    }

    public int getYDiameter() {
        return yDiameter;
    }

    public void setYDiameter(int yDiameter) {
        this.yDiameter = yDiameter;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBorderFocusColor() {
        return borderFocusColor;
    }

    public void setBorderFocusColor(Color borderFocusColor) {
        this.borderFocusColor = borderFocusColor;
    }
    
    private void jobInitialize(){
        borderColor=new Color(225,230,255);
        borderFocusColor=new Color(115,190,245);
        this.setModel(new DefaultButtonModel());
        this.init(null, null);
        this.setFocusable(true);
        this.setOpaque(autoSize);
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) { swapBorderColors(); }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) { swapBorderColors(); }
        });
        this.addMouseListener(new MouseAdapter(){
            @Override public void mouseEntered(MouseEvent e) { grabFocus(); }
        });
    }
        
    private void swapBorderColors(){
        Color tmp=borderColor; borderColor=borderFocusColor; borderFocusColor=tmp; paintBorder(getGraphics());
    }
              
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        LayoutManager lmg;
        Container cont=this.getParent();
        boolean chBounds=true;
        if(autoSize){
            wUsed=getWidth(); hUsed=getHeight(); xIni=0; yIni=0;
        }else{
            wUsed=xDiameter; hUsed=yDiameter; xIni=0; yIni=0;
            lmg=this.getParent().getLayout(); 
            if(lmg instanceof GridBagLayout || lmg instanceof FlowLayout){ chBounds=false; }
            if(chBounds && wUsed>this.getWidth()){ wUsed=this.getWidth(); }
            if(chBounds && hUsed>this.getHeight()){ hUsed=this.getHeight(); }
            if(chBounds && wUsed<this.getWidth()){ xIni=((this.getWidth()-wUsed)/2); }
            if(chBounds && hUsed<this.getHeight()){ yIni=((this.getHeight()-hUsed)/2); }
        }
        setPreferredSize(new Dimension(wUsed,hUsed));
        setMaximumSize(new Dimension(wUsed,hUsed));
        setMinimumSize(new Dimension(wUsed,hUsed));
        Paint oldPaint = g2.getPaint();
        Color cntbgc=cont.getBackground();
        Color bcgtrans=new Color(255,255,255,0);
        g2.setColor( (cntbgc instanceof ColorUIResource?bcgtrans:cntbgc) );
        g2.fillRect(0,0,getWidth(), getHeight()); 
        g2.setClip(new Ellipse2D.Float(xIni,yIni,wUsed,hUsed));
        g2.setPaint(oldPaint);
        super.paintComponent(g2); 
    }    
    
    @Override
    protected void paintBorder(Graphics g){
        if(g==null){ return; }
    	g.setColor(borderColor);
    	g.drawOval(xIni,yIni,wUsed-1,hUsed-1);
        g.drawOval(xIni+1,yIni+1,wUsed-2,hUsed-2);
        g.drawOval(xIni+1,yIni+1,wUsed-3,hUsed-3);
    }
        
    @Override
    public void updateUI(){ setUI((ButtonUI)UIManager.getUI(this)); }
    
    @Override
    public String getUIClassID() { return uiClassID; }
    
    @Override
    public boolean contains(int x, int y) { 
      if(shape==null || !shape.getBounds().equals(getBounds())){
         shape=new Ellipse2D.Float(xIni,yIni,wUsed,hUsed);
      }
      return shape.contains(x, y);
    }
}