import java.awt.*;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.util.Date;
import java.util.Vector;
import java.io.Serializable;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * A bean that displays a digital clock, the day of the week, day of the
 * month, and month. Implements Serializable for automatic saving and
 * restoring of bean state.
 */
public class Clock extends Canvas implements Serializable, Runnable {

    /* properties */
    private Color   timeColor;
    private Color   dateColor;
    private int     alarmInterval; /* in minutes */
    private boolean alarm;

    /* transient data members */
    private transient Thread clockThread;
    private transient int last_min = -1;
    private transient Vector actionListeners = new Vector();

    private transient Font dateFont, dayFont, timeFont, monthFont;
    private transient Image baseImg;
    private transient int size = 5;
    private transient int blink = 0;
    private transient Date now;

    private transient String weekday[] =
        { "SUN", "MON","TUE","WED","THU","FRI","SAT" };

    private transient String month[] =
        { "JAN","FEB","MAR","APR","MAY","JUN","JUL", "AUG","SEP",
          "OCT","NOV","DEC" };

    /* null argument constructor */
    public Clock() {
        alarm = true;
        alarmInterval = 1;  /* every minute */
        baseImg = loadImage("Clock.gif");
        timeFont = new Font("TimesRoman",Font.BOLD,16);
        dayFont = new Font("Helvetica",Font.PLAIN,10);
        dateFont = new Font("TimesRoman",Font.BOLD,18);
        monthFont = new Font("Helvetica",Font.ITALIC,8);
	timeColor = Color.yellow;
        dateColor = new Color(0,200,0); // dark green

        clockThread = new Thread(this, "Clock");
        clockThread.start();
        setSize(64, 64);
    }

    /* Get the time color property */
    public Color getTimeColor() {
        return timeColor;
    }

    /* Set the time color property */
    public void setTimeColor(Color timeColor) {
        this.timeColor = timeColor;
        repaint();
    }

    /* Get the date color property */
    public Color getDateColor() {
        return dateColor;
    }

    /* Set the date color property */
    public void setDateColor(Color dateColor) {
        this.dateColor = dateColor;
        repaint();
    }

    /* Get the alarm property */
    public boolean isAlarm() {
        return alarm;
    }

    /* Set the alarm property */
    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    /* Get the alarm interval property, in minutes */
    public int getAlarmInterval() {
        return alarmInterval;
    }

    /* Set the alarm interval property, in minutes */
    public void setAlarmInterval(int interval) {
        alarmInterval = interval;
    }

    /*
     * Implement minimum size method.
     */
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /*
     * Implement preferred size method.
     */
    public Dimension getPreferredSize() {
	/* replace this code with source code specific to your bean */
        return new Dimension(64, 64);
    }

    public void update(Graphics g) {
        paint(g);
    }

    /* Display the clock image, time, and date */
    public void paint(Graphics g) {
        if (g != null) {
            if (baseImg != null) {
                g.drawImage(baseImg, 0, 0, this);
	    }
            drawDate(g);
            drawTime(g);
	}
    }

    /* Start the clock */
    public void run() {
        while (clockThread != null) {
            Graphics g = this.getGraphics();
            if (g != null) {
                now = new Date();
                if (last_min != now.getMinutes()) {
                    last_min = now.getMinutes();
                    if (baseImg != null) {
                        g.drawImage(baseImg, 0, 0, this);
		    }
                    drawDate(g);
                    drawTime(g);
                    if (isAlarm() && doAlarm(last_min)) {
                        fireAction();
		    }
                }
                drawBlink(g);
            }
            this.getToolkit().sync();
            pause(1000);
        }
    }

    /* Determine whether to fire the alarm */
    private boolean doAlarm(int min) {
        boolean result = false;
        if (min == 0) {
            switch (alarmInterval) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 10:
                case 12:
                case 15:
                case 20:
                case 30:
                case 60:
                    result = true;
                    break;
                default:
                    break;
            }
        } else if (min % alarmInterval == 0) {
            result = true;
        }
        return result;
    }

    /* Pause the timer thread */
    private void pause(int time) {
        try {
            clockThread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    /* Stop the clock */
    public void stop() {
        clockThread.stop();
        clockThread = null;
    }

    /**
     * Draw one segment (0-6)
     */
    private void drawSegment(Graphics g, int x, int y, int seg) {
        if (g == null)
            return;
        int coord[] = {
            0,0,1,0,   // 0
            1,0,1,1,   // 1
            1,1,1,2,   // 2
            1,2,0,2,   // 3
            0,2,0,1,   // 4
            0,1,0,0,   // 5
            0,1,1,1 }; // 6
        g.drawLine(x + coord[seg * 4 + 0] * size, y + coord[seg * 4 + 1] * size,
            x + coord[seg * 4 + 2] * size, y + coord[seg * 4 + 3] * size);
    }

    /**
     * Draw one digit
     */
    private void drawDigit(Graphics g, int digit, int x, int y) {
        if (g == null)
            return;
        int digits[] = {
            1 + 2 + 4 + 8 + 16 + 32 + 00,  // 0
            0 + 2 + 4 + 0 + 00 + 00 + 00,  // 1
            1 + 2 + 0 + 8 + 16 + 00 + 64,  // 2
            1 + 2 + 4 + 8 + 00 + 00 + 64,  // 3
            0 + 2 + 4 + 0 + 00 + 32 + 64,  // 4
            1 + 0 + 4 + 8 + 00 + 32 + 64,  // 5
            1 + 0 + 4 + 8 + 16 + 32 + 64,  // 6
            1 + 2 + 4 + 0 + 00 + 00 + 00,  // 7
            1 + 2 + 4 + 8 + 16 + 32 + 64,  // 8
            1 + 2 + 4 + 8 + 00 + 32 + 64,  // 9
        };
        for(int i = 0; i < 7; i++) {
            if ((digits[digit] & (1 << i)) != 0)
                drawSegment(g, x, y, i);
        }
    }

    /* Display a numeric string */
    private void drawDigitString(Graphics g, String s, int x, int y) {
        if (g == null)
            return;
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i);
            if (digit >= '0' && digit <= '9')
                drawDigit(g, digit - '0', x += (int)(size * 1.8), y);
            else
                x += (int)(size * .8);
        }
    }

    /* Display blinking colon in time */
    private void drawBlink(Graphics g) {
        if (g == null)
            return;
        if (blink == 0) {
            g.setColor(timeColor);
            blink = 1;
        } else {
            g.setColor(Color.black);
            blink = 0;
        }
        g.drawLine(30, 9,  30, 10);
        g.drawLine(30, 12, 30, 13);
    }

    /* Displays the time */
    private void drawTime(Graphics g) {
        if (g == null || now == null)
            return;
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(timeFont);
        String s =
            "" + (int)(now.getHours() / 10) + (now.getHours() % 10) + ":" +
            "" + (int)(now.getMinutes() / 10) + (now.getMinutes() % 10);
        g.setFont(timeFont);
        g.setColor(timeColor);
        drawDigitString(g, s, 4, 6);
    }

    /*
     * Displays the date in the format:
     *        day of week
     *        day of month
     *           month
     */
    private void drawDate(Graphics g) {
        if (now == null || g == null) {
            return;
	}
        g.setColor(dateColor);
	/* day of week */
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(dayFont);
        g.setFont(dayFont);
        String str = weekday[now.getDay()];
        g.drawString(str, (65 - fm.stringWidth(str)) / 2 - 2, 31);

	/* day of month */
        fm = Toolkit.getDefaultToolkit().getFontMetrics(dateFont);
        g.setFont(dateFont);
        str = "" + now.getDate();
        g.drawString(str, (65 - fm.stringWidth(str)) / 2 - 2, 46);

	/* month */
        fm = Toolkit.getDefaultToolkit().getFontMetrics(monthFont);
        g.setFont(monthFont);
        str = month[now.getMonth()];
        g.drawString(str, (65 - fm.stringWidth(str)) / 2 - 2, 54);
    }

    /* Load the image file via the class loader */
    private Image loadImage(String imageName) {
        Image image = null;
        try {
	    URL url = getClass().getResource(imageName);
            image = createImage((ImageProducer)url.getContent());
        } catch (Exception e) {
            System.out.println("Exception loading image " + imageName + ": " +
                e.getMessage());
        }
        return image;
    }

    /*
     * Add an action listener to this bean.
     */
    public synchronized void addActionListener(ActionListener l) {
        actionListeners.addElement(l);
    }

    /*
     * Remove an action listener from this bean.
     */
    public synchronized void removeActionListener(ActionListener l) {
        actionListeners.removeElement(l);
    }

    /*
     * Create an ActionEvent and notify the action listeners of the event
     */
    public void fireAction() {
        Vector targets;
        synchronized (this) {
            targets = (Vector) actionListeners.clone();
	}
        ActionEvent actionEvt = new ActionEvent(this, 0, null);
        for (int i = 0; i < targets.size(); i++) {
            ActionListener target = (ActionListener)targets.elementAt(i);
	    target.actionPerformed(actionEvt);
	}
    }
}

