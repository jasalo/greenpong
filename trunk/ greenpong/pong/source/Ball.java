package source;
import java.awt.*;
import java.awt.event.*;

/**
 * Ball object.
 *
 * @author Don Yang
 * @version 1.01 (9/28/01)
 *
 * @see Pong
 * @see Paddle
 */
public class Ball extends Thread
{
   //
   // Data
   //

   /** Ball size */
   public static int Radius = 8;

   /** Ball initial velocity */
   public static int MinVelocity = 5;

   /** Ball maximum velocity */
   public static int MaxVelocity = 20;

   /** Ball coordinate */
   public int x, y;

   /** Ball velocity */
   public int vx, vy;

   /** Ball color */
   public Color color;

   /** Ball status */
   public int held = 0;

   /** Thread status */
   public boolean runthread;


   //
   // Code
   //

   /** Ball constructor */
   public Ball(int x0, int y0, int vx0, int vy0)
   {
      x = x0;
      y = y0;
      vx = vx0;
      vy = vy0;
      color = new Color(Math.abs(Pong.rand.nextInt()) % 256,
                        Math.abs(Pong.rand.nextInt()) % 256,
                        Math.abs(Pong.rand.nextInt()) % 256);
      held = 0;
      runthread = true;

   } // Ball()

   /** Execute */
   public void run()
   {
      Thread me = Thread.currentThread();

      while( runthread && me == Thread.currentThread() )
      {
         // Set global data
         Radius = Pong.Height / 30;
         MinVelocity = Radius / 2 + 1;
         MaxVelocity = Radius * 2 + Paddle.Width;

         try {sleep(Pong.REFRESH_RATE);}
         catch (InterruptedException e) {}

         // Animate if applet is active
         if( Pong.active )
         {
            // Move
            if( vx == 0 )
               vx++;
            if( held > 1 )
            {
               x = Pong.MouseX;
               y = Pong.MouseY;

               // Clamp
               if( x < Radius )
                  x = Radius;
               if( x > Pong.Width - Radius - 1 )
                  x = Pong.Width - Radius - 1;
               if( y < Radius )
                  y = Radius;
               if( y > Pong.Height - Radius - 1 )
                  y = Pong.Height - Radius - 1;
            }
            else
            {
               x += vx;
               y += vy;

               // Bounce
               if( y < Radius )
               {
                  y = 2 * Radius - y;
                  vy = -vy;
               }
               if( y > Pong.Height - Radius )
               {
                  y = 2 * (Pong.Height - Radius) - y;
                  vy = -vy;
               }
               if( Math.abs(x - Pong.left.x) < Ball.Radius + Paddle.Width &&
                   vx < 0 )
               {
                  if( Math.abs(Pong.left.y - y) < Paddle.Height / 2 )
                  {
                     vy = Pong.rand.nextInt() % MinVelocity + 2;
                     if( (Pong.rand.nextInt() & 1) == 0 )
                        vy = -vy;
                     vx = -vx;
                     if( vx < MaxVelocity )
                        vx++;
                  }
               }
               if( Math.abs(x - Pong.right.x) < Ball.Radius + Paddle.Width &&
                   vx > 0 )
               {
                  if( Math.abs(Pong.right.y - y) < Paddle.Height / 2 )
                  {
                     vy = Pong.rand.nextInt() % MinVelocity + 2;
                     if( (Pong.rand.nextInt() & 1) == 0 )
                        vy = -vy;
                     vx = -vx;
                     if( vx > -MaxVelocity )
                        vx--;
                  }
               }
               if( x < 0 || x >= Pong.Width )
                  break;
            }
         }
      }
      if( vx < 0 )
         Pong.right.score++;
      else
         Pong.left.score++;

   } // run()
}
