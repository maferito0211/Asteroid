package asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;


public class FrmJuego extends javax.swing.JFrame implements KeyListener {

    int X1;
    int X2=X1;
    int llamas;
    int signo=1;
    int sentidogiro=1;
    int aceleracion=(int)(Math.random()*4+1);
    int colision,tiempoexplosion,aleato,aleatorio,shot,numShot,inicio,punto;
    int puntoexplosionX=100;
    int puntoexplosionY=100;
    double distancia;
    double distanciaShot;
    Objeto nave = new Objeto(400,300,0,0,0);
    Objeto asteroidito = new Objeto(Math.random()*800, Math.random()*600, 0, 0, 0);
    Objeto disparo[] = new Objeto[11];
    
    public FrmJuego() {
        initComponents();
        addKeyListener(this);
        for(int i=0;i<11;i++)
        {
            disparo[i]=new Objeto(nave.getDrawLocationX()+40,nave.getDrawLocationY()+40,0,0,0);
        }
    }
    
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        llamas=0;
    }
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode()==39)
        {
            nave.setAngulo(nave.getAngulo()+10);
        }
        if (e.getKeyCode()==37)
        {
            nave.setAngulo(nave.getAngulo()-10);
        }
        if (e.getKeyCode()==38)
        {
            nave.setAccX(Math.cos(Math.toRadians(nave.getAngulo()%360))*10);
            nave.setAccY(Math.sin(Math.toRadians(nave.getAngulo()%360))*10);
            llamas=1;
        }
        if(nave.getDrawLocationX()>=815 )
        {
            nave.setDrawLocationX(-10);
            nave.setDrawLocationY(nave.getDrawLocationY());
            nave.setAccX(3);
        }
        else if (nave.getDrawLocationX()<=-15)
        {
            nave.setDrawLocationX(805);
            nave.setDrawLocationY(nave.getDrawLocationY());
            nave.setAccX(3);
        }
        if(nave.getDrawLocationY()>=610 )
        {
            nave.setDrawLocationY(-10);
            nave.setDrawLocationX(nave.getDrawLocationX());
            nave.setAccY(3);
        }
        else if (nave.getDrawLocationY()<=-15)
        {
            nave.setDrawLocationY(605);
            nave.setDrawLocationX(nave.getDrawLocationX());
            nave.setAccY(3);
        }
        if(e.getKeyCode() == 32)
        {
            shot=1;
            numShot++;
            if(numShot>=10 && inicio<10)
                numShot=10;
            if(numShot==10 && inicio==10)
                numShot=0;
            disparo[numShot-1].setAngulo(nave.getAngulo());
        }
    }
    
    Timer temporizador = new Timer (100, new ActionListener ()
    {
        public void actionPerformed(ActionEvent e){
            X1+=3;
            if (X1>=0)
                X2=X1;
            if (X1+5>=780)
                X1=0;
            
            distancia=Math.sqrt(Math.pow(nave.getDrawLocationX()-asteroidito.getDrawLocationX(),2)+Math.pow(nave.getDrawLocationY()-asteroidito.getDrawLocationY(),2));
            for(int i=0;i<10;i++)
            {
                distanciaShot=Math.sqrt(Math.pow(disparo[i].getDrawLocationX()-asteroidito.getDrawLocationX(),2)+Math.pow(disparo[i].getDrawLocationY()-asteroidito.getDrawLocationY(),2));
                if (distanciaShot<=80)
                {
                    punto=1;
                }
            }
            if (distancia<=90)
            {
                colision=1;
                tiempoexplosion+=10;
            }
            else if(punto==1)
            {
                colision=1;
                tiempoexplosion+=10;
            }
            
            for(int i=numShot;i<10;i++)
            {
                disparo[i].setDrawLocationX(nave.getDrawLocationX()+40);
                disparo[i].setDrawLocationY(nave.getDrawLocationY()+40);
            }
            if(shot==1)
            {
                for(int i=inicio;i<numShot;i++)
                {
                    disparo[i].setAccX(Math.cos(Math.toRadians(disparo[i].getAngulo()%360))*20);
                    disparo[i].setAccY(Math.sin(Math.toRadians(disparo[i].getAngulo()%360))*20);
                    disparo[i].setDrawLocationX(disparo[i].getDrawLocationX()+(int)disparo[i].getAccX());
                    disparo[i].setDrawLocationY(disparo[i].getDrawLocationY()+(int)disparo[i].getAccY());
                }
            }
            for(int i=0;i<inicio;i++)
            {
                disparo[i].setDrawLocationX(nave.getDrawLocationX()+40);
                disparo[i].setDrawLocationY(nave.getDrawLocationY()+40);
            }
            repaint();
        }
    });
    
    public void paint(Graphics g)
    {
        BufferedImage imgNebula=null;
        BufferedImage imgDebris=null;
        BufferedImage imgAsteroid=null;
        BufferedImage imgShip=null;
        BufferedImage imgFireShip=null;
        BufferedImage imgShot=null;
        BufferedImage imgExplosion=null;
        BufferedImage imgBlast=null;
        
        try 
        {
            imgNebula = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\nebula_blue.png"));
            imgDebris = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\debris2_blue.png"));
            imgShip = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\nave1.png"));
            imgFireShip = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\nave2.png"));
            imgAsteroid = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\asteroid_blue.png"));
            imgExplosion = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\explosion.hasgraphics.png"));
            imgShot= ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\shot2.png"));
            imgBlast = ImageIO.read(new File("c:\\Users\\hp\\Documents\\SEGUNDO\\Informática II\\Asteroids imágenes\\shot1.png"));
        }
        //codigo que se desea ejecutar (con errores ono)
        catch (IOException ex) {
            Logger.getLogger(FrmJuego.class.getName()).log(Level.SEVERE, null, ex);
        }//aqui entra si hay un error, ex es donde se guarda elerror
        
        
        
        //super.paint(g);
        g.drawImage(imgNebula, 0, 0,800,600,this);//sin llama
        g.drawImage(imgDebris, (X2)-800, 0,800,600,this);//con llama
        g.drawImage(imgDebris, X1, 0,800,600,this);//con llama
        nave.setDrawLocationX(nave.getDrawLocationX()+nave.getAccX());
        nave.setDrawLocationY(nave.getDrawLocationY()+nave.getAccY());
        asteroidito.setDrawLocationX(asteroidito.getDrawLocationX()+(signo*aceleracion));
        asteroidito.setDrawLocationY(asteroidito.getDrawLocationY()+(signo*aceleracion));
        for(int i=0;i<10;i++)
        {
            g.drawImage(imgShot,(int)disparo[i].getDrawLocationX(),(int)disparo[i].getDrawLocationY(),8,8,this);
        }
        
        
        
        
         // Rotation information
        if (llamas==0)
        {
        double rotationRequired = Math.toRadians(nave.getAngulo());
        double locationX = imgShip.getWidth() / 2;
        double locationY = imgShip.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        Graphics2D g2=(Graphics2D) g;
        g2.drawImage(op.filter(imgShip, null), (int)nave.getDrawLocationX(), (int)nave.getDrawLocationY(), null);
        }
        else
        {
        double rotationRequired = Math.toRadians(nave.getAngulo());
        double locationX = imgFireShip.getWidth() / 2;
        double locationY = imgFireShip.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        Graphics2D g2=(Graphics2D) g;
        g2.drawImage(op.filter(imgFireShip, null), (int)nave.getDrawLocationX(), (int)nave.getDrawLocationY(), null);
        }
        
        
        //linea del asteroide
        double rotationRequired = Math.toRadians(asteroidito.getAngulo());
        double locationX = imgAsteroid.getWidth() / 2;
        double locationY = imgAsteroid.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        Graphics2D g2=(Graphics2D) g;
       
        
        if(colision==0)
        {
            asteroidito.setAngulo(asteroidito.getAngulo()+(sentidogiro*4));
            asteroidito.setAccX(Math.cos(Math.toRadians(asteroidito.getAngulo()%360))*5.99);
            asteroidito.setAccY(Math.sin(Math.toRadians(asteroidito.getAngulo()%360))*5.99);
            puntoexplosionX=100;
            puntoexplosionY=100;
            g2.drawImage(op.filter(imgAsteroid, null), (int)asteroidito.getDrawLocationX(), (int)asteroidito.getDrawLocationY(), null);
            if((int)asteroidito.getDrawLocationX()>=800 || (int)asteroidito.getDrawLocationY()>=600 || (int)asteroidito.getDrawLocationX()<-70 || (int)asteroidito.getDrawLocationY()<-70)
            {
                aleato=(int)(Math.random()*2);
                aleatorio=(int)(Math.random()*2);
                asteroidito.setDrawLocationX(Math.random()*800);
                asteroidito.setDrawLocationY(Math.random()*600);
                aceleracion=(int)(Math.random()*4+1);
                if(aleato==1)
                    signo=1;
                else
                    signo=-1;
                if(aleatorio==1)
                    sentidogiro=1;
                else
                    sentidogiro=-1;
                asteroidito.setAngulo(asteroidito.getAngulo()+(sentidogiro*4));
                g2.drawImage(op.filter(imgAsteroid, null), (int)asteroidito.getDrawLocationX(), (int)asteroidito.getDrawLocationY(), null);     
            }
        }
        else
        {
            g.drawImage(imgExplosion, (int)asteroidito.getDrawLocationX(), (int)asteroidito.getDrawLocationY(), (int)asteroidito.getDrawLocationX()+90, (int)asteroidito.getDrawLocationY()+90, puntoexplosionX, puntoexplosionY, puntoexplosionX+100, puntoexplosionY+100, this);
            puntoexplosionX+=100;
            puntoexplosionY+=100;
            if(tiempoexplosion==100)
            {
                colision=0;
                aleato=(int)(Math.random()*2);
                aleatorio=(int)(Math.random()*2);
                asteroidito.setDrawLocationX(Math.random()*800);
                asteroidito.setDrawLocationY(Math.random()*600);
                aceleracion=(int)(Math.random()*4+1);
                if(aleato==1)
                    signo=1;
                else
                    signo=-1;
                if(aleatorio==1)
                    sentidogiro=1;
                else
                    sentidogiro=-1;
                tiempoexplosion=0;
                punto=0;
            }
        }
        
        if(shot==1)
        {
            for(int i=0;i<10;i++)
            {
                if(disparo[i].getDrawLocationX()<-5 || disparo[i].getDrawLocationX()>=820 || disparo[i].getDrawLocationY()>=620 || disparo[i].getDrawLocationY()<-5)
                {
                    disparo[i].setDrawLocationX(nave.getDrawLocationX()+40);
                    disparo[i].setDrawLocationY(nave.getDrawLocationY()+40);
                    inicio++;
                    if(inicio==10)
                    {
                        inicio=0;
                        numShot=0;
                    }
                }
            }   
        }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        temporizador.start();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
