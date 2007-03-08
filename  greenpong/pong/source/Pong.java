package source;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Pong!
 *
 * @author Don Yang
 * @version 1.01 (9/28/01)
 *
 * @see Ball
 * @see Paddle
 */
public class Pong extends Applet
                  implements Runnable, MouseListener, MouseMotionListener
{
   //
   // Constants
   //

   /** Texts */
   private static final String INTRO_TEXT1 = "Pong 1.0 (12/25/99)";
   private static final String INTRO_TEXT2 = "omoikane";
   private static final String TITLE_TEXT = INTRO_TEXT1 + " - " + INTRO_TEXT2;

   /** Refresh time (ms) */
   public static final int REFRESH_RATE = 40;

   /** Ball count */
   public static final int MAX_BALLS = 16;


   //
   // Data
   //

   /** Game field width */
   public static int Width = 320;

   /** Game field height */
   public static int Height = 240;

   /** Mouse coordinates */
   public static int MouseX = 0, MouseY = 0;
   public static int MouseX0 = 0, MouseY0 = 0;


   /** Program status */
   public static boolean active;
   private static int IntroT = 2000 / REFRESH_RATE;
   private static int IntroR, IntroG, IntroB;
   private static int serial = 1;

   /** Program thread */
   private Thread PongThread;
   private static boolean runthread = true;


   /** Balls */
   public static Ball ball[] = null;
   private static int BallCount, BallIndex, BallSerial = 1;
   private static int BallRefreshTime;


   /** Paddle position */
   public static int Margin = 16;

   /** Paddle */
   public static Paddle left = null;

   /** Paddle */
   public static Paddle right = null;


   /** Random number generator */
   public static Random rand;

   /** Back buffer */
   private Image BackBuffer;
   private Graphics g;


   //
   // Code
   //

   /** Initialize applet (called on startup) */
   public void init()
   {
      int i;

      // Initialize random numbers
      rand = new Random();

      // Initialize balls
      BallCount = BallRefreshTime = BallIndex = 0;
      ball = new Ball[MAX_BALLS];
      for(i = 0; i < MAX_BALLS; ball[i++] = null);

      // Initialize paddles
      left = new Paddle(Margin, Height / 2);
      right = new Paddle(Width - Margin - 1, Height / 2);
      left.start();
      left.setName("Pong #" + serial + " - left paddle");
      right.start();
      right.setName("Pong #" + serial + " - right paddle");

      // Initialize back buffer
      BackBuffer = createImage(Width, Height);
      g = BackBuffer.getGraphics();

   } // init()

   /** Kill applet (called on cleanup) */
   public void destroy()
   {
      int i;

      if( left != null )   left.runthread = false;
      if( right != null )  right.runthread = false;
      for(i = 0; i < MAX_BALLS; i++)
      {
         if( ball[i] != null )
            ball[i].runthread = false;
      }

      g.dispose();

   } // destroy()

   /** Run applet */
   public void start()
   {
      // Initialize mouse
      addMouseListener(this);
      addMouseMotionListener(this);

      // Initialize applet
      active = false;
      PongThread = new Thread(this, "Pong #" + (serial++));
      PongThread.start();

   } // start()

   /** Stop applet */
   public void stop()
   {
      // Stop thread
      if( PongThread != null )
         Pong.runthread = false;

      // Free mouse
      removeMouseListener(this);
      removeMouseMotionListener(this);

   } // stop()

   /** Return info */
   public String getAppletInfo()
   {
      return TITLE_TEXT;

   } // getAppletInfo()

   /** Start thread */
   public void run()
   {
      // Animate
      while( Pong.runthread && PongThread == Thread.currentThread() )
      {
         // Set global data
         Width = getSize().width;
         Height = getSize().height;
         Margin = Width / 20;
         left.tx = Margin;
         right.tx = Width - Margin - 1;
         if( BackBuffer.getWidth(this) != Width ||
             BackBuffer.getHeight(this) != Height )
         {
            BackBuffer = createImage(Width, Height);
            g = BackBuffer.getGraphics();
         }

         // Animate
         try {PongThread.sleep(REFRESH_RATE);}
         catch (InterruptedException e) {}

         RefreshBalls();
         CatchBalls();

         if( (active = isActive()) == true )
            repaint();
      }

   } // run()

   /** Redraw */
   public void update(Graphics g0)
   {
      if( PongThread != null )
         paint(g0);

   } // update()

   /** Redraw */
   public void paint(Graphics g0)
   {
      Font f = new Font("Courier", Font.BOLD, Height / 12);
      FontMetrics fm = g0.getFontMetrics(f);
      int i;

      if( g == null )
         return;

      // Clear background
      g.setPaintMode();
      g0.setPaintMode();
      g.setColor(Color.white);
      g.fillRect(0, 0, Width, Height);

      // Draw text
      g.setFont(f);
      g.setColor(left.held2 > 1 ? Color.red
                                : (left.held2 > 0 ? Color.green
                                                  : Color.black));
      g.drawString(Integer.toString(left.score),
                   Width / 4 - fm.stringWidth(Integer.toString(left.score)),
                   Height / 12 + 10);
      g.setColor(right.held2 > 1 ? Color.red
                                 : (right.held2 > 0 ? Color.green
                                                    : Color.black));
      g.drawString(Integer.toString(right.score),
                   3 * Width / 4,
                   Height / 12 + 10);

      // Draw intro text
      if( IntroT > 0 )
      {
         IntroT--;
         g.setFont(f = new Font("Times New Roman", Font.PLAIN, Height / 10));
         fm = g.getFontMetrics(f);
         g.setColor(new Color(IntroR = (rand.nextInt() % 32 + 32),
                              IntroG = 64,
                              IntroB = (rand.nextInt() % 32 + 32)));
         g.drawString(INTRO_TEXT1, (Width - fm.stringWidth(INTRO_TEXT1)) / 2,
                                   Height / 3);
         g.drawString(INTRO_TEXT2, (Width - fm.stringWidth(INTRO_TEXT2)) / 2,
                                   2 * Height / 3);
      }
      else if( IntroR < 255 || IntroG < 255 || IntroB < 255 )
      {
         g.setFont(f = new Font("Times New Roman", Font.PLAIN, Height / 10));
         fm = g.getFontMetrics(f);
         g.setColor(new Color(IntroR, IntroG, IntroB));
         g.drawString(INTRO_TEXT1, (Width - fm.stringWidth(INTRO_TEXT1)) / 2,
                                   Height / 3);
         g.drawString(INTRO_TEXT2, (Width - fm.stringWidth(INTRO_TEXT2)) / 2,
                                   2 * Height / 3);
         if( IntroR < 255 )      IntroR++;
         if( IntroG < 255 )      IntroG++;
         if( IntroB < 255 )      IntroB++;
      }

      // Draw balls
      if( ball == null )
         return;
      for(i = 0; i < MAX_BALLS; i++)
      {
         if( ball[i] != null )
         {
            g.setColor(ball[i].color);
            g.fillRoundRect(ball[i].x - Ball.Radius,
                            ball[i].y - Ball.Radius,
                            Ball.Radius * 2, Ball.Radius * 2,
                            Ball.Radius * 2, Ball.Radius * 2);
            g.setColor(ball[i].held > 1 ? Color.red
                                        : (ball[i].held > 0 ? Color.green
                                                            : Color.black));
            g.drawRoundRect(ball[i].x - Ball.Radius,
                            ball[i].y - Ball.Radius,
                            Ball.Radius * 2,
                            Ball.Radius * 2,
                            Ball.Radius * 2, Ball.Radius * 2);
            g.drawRoundRect(ball[i].x - Ball.Radius + 1,
                            ball[i].y - Ball.Radius + 1,
                            (Ball.Radius - 1) * 2,
                            (Ball.Radius - 1) * 2,
                            (Ball.Radius - 1) * 2, (Ball.Radius - 1) * 2);
         }
      }

      // Draw paddles
      g.setColor(Color.white);
      g.fillRect(left.x - Paddle.Width, left.y - Paddle.Height / 2,
                 Paddle.Width, Paddle.Height);
      g.fillRect(right.x, right.y - Paddle.Height / 2,
                 Paddle.Width, Paddle.Height);
      g.setColor(left.held > 1 ? Color.red
                               : (left.held > 0 ? Color.green
                                                : Color.black));
      g.drawRect(left.x - Paddle.Width, left.y - Paddle.Height / 2,
                 Paddle.Width, Paddle.Height);
      g.drawRect(left.x - Paddle.Width - 1, left.y - Paddle.Height / 2 - 1,
                 Paddle.Width + 2, Paddle.Height + 2);
      g.setColor(right.held > 1 ? Color.red
                                : (right.held > 0 ? Color.green
                                                  : Color.black));
      g.drawRect(right.x, right.y - Paddle.Height / 2,
                 Paddle.Width, Paddle.Height);
      g.drawRect(right.x - 1, right.y - Paddle.Height / 2 - 1,
                 Paddle.Width + 2, Paddle.Height + 2);

      // Blit
      g0.drawImage(BackBuffer, 0, 0, this);

   } // paint()


   /** Regenerate balls */
   private void RefreshBalls()
   {
      int i, py, vy;

      // Kill inactive ball threads
      if( ball == null )
         return;
      for(i = 0; i < MAX_BALLS; i++)
      {
         if( ball[i] != null )
         {
            if( !ball[i].isAlive() )
            {
               ball[i] = null;
               BallCount--;
            }
         }
      }

      // Regenerate balls
      if( (--BallRefreshTime) > 0 )
         return;
      BallRefreshTime = 3000 / REFRESH_RATE;
      if( BallCount >= MAX_BALLS )
         return;

      py = Math.abs(rand.nextInt()) % Height;
      vy = Math.abs(rand.nextInt()) % 4 + 2;
      if( BallCount <= 0 )
      {
         // No balls in field, generate one from scratch
         if( (rand.nextInt() & 1) == 0 )
         {
            // Next ball appears from left
            ball[0] = new Ball(0, py, Ball.MinVelocity, vy);
         }
         else
         {
            // Next ball appears from right
            ball[0] = new Ball(Width - 1, py, -Ball.MinVelocity, vy);
         }
         BallCount = 1;
         ball[0].start();
         ball[0].setName("Ball #" + (BallSerial++));
      }
      else
      {
         // Clone ball from existing ones
         if( BallCount < MAX_BALLS )
         {
            for(i = Math.abs(rand.nextInt()) % MAX_BALLS;
                ball[i] == null; i = Math.abs(rand.nextInt()) % MAX_BALLS);
            while( ball[BallIndex] != null )
               BallIndex = (BallIndex + 1) % MAX_BALLS;
            ball[BallIndex] = new Ball(ball[i].x, ball[i].y,
                                       ball[i].vx, ball[i].vy);
            ball[BallIndex].vx = (ball[i].vx < 0) ? -Ball.MinVelocity
                                                  :  Ball.MinVelocity;
            ball[BallIndex].start();
            ball[BallIndex].setName("Ball #" + (BallSerial++));
            BallCount++;
         }
      }

   } // RefreshBalls()

   /** Catch balls */
   private void CatchBalls()
   {
      int i;

      // Catch balls
      for(i = 0; i < MAX_BALLS; i++)
      {
         if( ball[i] != null )
         {
            if( Math.abs(MouseX - ball[i].x) < Ball.Radius &&
                Math.abs(MouseY - ball[i].y) < Ball.Radius )
               ball[i].held |= 1;
            else
               ball[i].held &= 2;
         }
      }

      // Catch paddles
      if( MouseX <= left.x &&
          MouseX >= left.x - Paddle.Width &&
          Math.abs(MouseY - left.y) <= Paddle.Height / 2 )
         left.held |= 1;
      else
         left.held &= 2;
      if( MouseX >= right.x &&
          MouseX <= right.x + Paddle.Width &&
          Math.abs(MouseY - right.y) <= Paddle.Height / 2 )
         right.held |= 1;
      else
         right.held &= 2;

      // Catch scores
      if( MouseY <= Height / 12 + 10 &&
          MouseY >= Height / 12 + 10 - 2 * Ball.Radius )
      {
         if( MouseX < Width / 4 &&
             MouseX > Width / 4 - Height / 12 )
            left.held2 |= 1;
         else
            left.held2 &= 2;
         if( MouseX > 3 * Width / 4 &&
             MouseX < 3 * Width / 4 + Height / 12 )
            right.held2 |= 1;
         else
            right.held2 &= 2;
      }
      else
      {
         left.held2 &= 2;
         right.held2 &= 2;
      }

   } // CatchBalls()


   /** Track button click (double click) */
   public void mouseClicked(MouseEvent m)
   {
      if( m.getClickCount() > 1 )
         IntroT = 1000 / REFRESH_RATE;

   } // mouseClicked()

   /** Track button press */
   public void mousePressed(MouseEvent m)
   {
      int i;

      // Lock balls
      for(i = 0; i < MAX_BALLS; i++)
      {
         if( ball[i] != null )
         {
            if( ball[i].held > 0 )
               ball[i].held |= 2;
         }
      }

      // Lock paddles
      if( left.held > 0 )     left.held |= 2;
      if( right.held > 0 )    right.held |= 2;

      // Lock scores
      if( left.held2 > 0 )    left.held2 |= 2;
      if( right.held2 > 0 )   right.held2 |= 2;

   } // mousePressed()

   /** Track button release */
   public void mouseReleased(MouseEvent m)
   {
      int i;

      // Release balls
      for(i = 0; i < MAX_BALLS; i++)
      {
         if( ball[i] != null )
            ball[i].held &= 1;
      }

      // Release paddles
      left.held &= 1;
      right.held &= 1;

      // Release scores
      left.held2 &= 1;
      right.held2 &= 1;

   } // mouseReleased()

   /** Track mouse motion (pressed) */
   public void mouseDragged(MouseEvent m)
   {
      // Update mouse coordinates
      MouseX0 = MouseX;
      MouseY0 = MouseY;
      MouseX = m.getX();
      MouseY = m.getY();

   } // mouseDragged()

   /** Track mouse motion (released) */
   public void mouseMoved(MouseEvent m)
   {
      // Update mouse coordinate
      MouseX0 = MouseX;
      MouseY0 = MouseY;
      MouseX = m.getX();
      MouseY = m.getY();

   } // mouseMoved()

   /** Set cursor (called on mouse entrance) */
   public void mouseEntered(MouseEvent m)
   {
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

   } // mouseEntered()

   /** Restore cursor (called on mouse exit) */
   public void mouseExited(MouseEvent m)
   {
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      MouseX = MouseX0 = MouseY = MouseY0 = -1;
      mouseReleased(null);

   } // mouseExited()
}
