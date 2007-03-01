package source;
/**
 * Paddle object.
 *
 * @author Don Yang
 * @version 1.01 (9/28/01)
 *
 * @see Pong
 * @see Ball
 */
public class Paddle extends Thread
{
   //
   // Data
   //

   /** Paddle width (thickness) */
   public static int Width = 4;

   /** Paddle height */
   public static int Height = 45;

   /** Paddle speed */
   private static int MinVelocity = 1;

   /** Paddle coordinate */
   public int x, y;

   /** Paddle destination */
   public int tx, ty;

   /** Paddle score */
   public int score;

   /** Paddle status */
   public int held = 0, held2 = 0;;

   /** Thread status */
   public boolean runthread;


   //
   // Code
   //

   /** Paddle constructor */
   public Paddle(int x0, int y0)
   {
      x = x0;
      y = y0;
      score = 0;
      held = 0;
      held2 = 0;
      runthread = true;

   } // Paddle()

   /** Execute */
   public void run()
   {
      Thread me = Thread.currentThread();
      double MinTime, time;
      int i;

      while( runthread && me == Thread.currentThread() )
      {
         // Set global data
         Width = Ball.Radius / 2;
         Height = Pong.Height / 5;
         MinVelocity = Pong.Height / 100;

         try {sleep(Pong.REFRESH_RATE);}
         catch (InterruptedException e) {}

         // Animate if applet active
         if( Pong.active )
         {
            // Move paddle
            if( held > 1 )
            {
               x = Pong.MouseX;
               y = Pong.MouseY;
            }
            else
            {
               // Find closest ball
               MinTime = Double.POSITIVE_INFINITY;
               ty = Pong.Height / 2;
               for(i = 0; i < Pong.MAX_BALLS; i++)
               {
                  if( Pong.ball[i] != null )
                  {
                     // Ignore balls not coming in this direction
                     if( (Pong.ball[i].vx < 0 && Pong.ball[i].x < x) ||
                         (Pong.ball[i].vx > 0 && Pong.ball[i].x > x) )
                        continue;

                     // Calculate arrival time
                     time = ((double)(x - Pong.ball[i].x)) / Pong.ball[i].vx;
                     if( time > MinTime )
                        continue;
                     MinTime = time;

                     // Predict ball position
                     ty = (int)(Pong.ball[i].y + time * Pong.ball[i].vy);
                     while( ty < Ball.Radius ||
                            ty > (Pong.Height - Ball.Radius) )
                     {
                        if( ty < Ball.Radius )
                           ty = 2 * Ball.Radius - ty;
                        else
                           ty = 2 * (Pong.Height - Ball.Radius) - ty;
                     }
                  }
               }

               // Shift paddle to receive ball
               if( Math.abs(y - ty) > Height )
               {
                  if( ty > y && y < Pong.Height - (Height / 2) )
                     y += MinVelocity * 4;
                  else if( ty < y && y > (Height / 2) )
                     y -= MinVelocity * 4;
               }
               else if( Math.abs(y - ty) > (Height / 3) )
               {
                  if( ty > y && y < Pong.Height - (Height / 2) )
                     y += MinVelocity * 2;
                  else if( ty < y && y > (Height / 2) )
                     y -= MinVelocity * 2;
               }
               else
               {
                  if( ty > y && y < Pong.Height - (Height / 2) )
                     y++;
                  else if( ty < y && y > (Height / 2) )
                     y--;
               }

               // Shift horizontally
               if( Math.abs(x - tx) > Height )
               {
                  if( tx > x )         x += MinVelocity * 4;
                  else if( tx < x )    x -= MinVelocity * 4;
               }
               else if( Math.abs(x - tx) > (Height / 2) )
               {
                  if( tx > x )         x += MinVelocity * 2;
                  else if( tx < x )    x -= MinVelocity * 2;
               }
               else
               {
                  if( tx > x )         x++;
                  else if( tx < x )    x--;
               }
            }

            // Clamp
            if( x < Width )
               x = Width;
            if( x > Pong.Width - Width - 1 )
               x = Pong.Width - Width - 1;
            if( y < Height / 2 )
               y = Height / 2;
            if( y > Pong.Height - Height / 2 - 1 )
               y = Pong.Height - Height / 2 - 1;

            // Update score
            if( held2 > 1 )
            {
               if( Pong.MouseX - Pong.MouseX0 > 1 )   score++;
               if( Pong.MouseX0 - Pong.MouseX > 1 )   score--;
            }
         }
      }

   } // run()
}
