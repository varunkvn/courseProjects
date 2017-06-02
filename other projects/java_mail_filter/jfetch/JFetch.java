
package jfetch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.xml.sax.SAXException;

import org.apache.avalon.Component;
import org.apache.avalon.Composer;
import org.apache.avalon.Initializable;
import org.apache.avalon.ComponentManager;
import org.apache.avalon.DefaultComponentManager;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.ConfigurationException;
import org.apache.avalon.configuration.ConfigurationBuilder;
import org.apache.avalon.configuration.DefaultConfigurationBuilder;
import org.apache.log.LogKit;
import org.apache.log.Logger;

import jfetch.log.LogManager;
import jfetch.filters.FilterProcessor;
import jfetch.core.Maildrop;
import jfetch.delivery.DeliveryManager;
import jfetch.delivery.DeliveryAgent;

/**
 * Entry class of the JFetch Application.
 * Responsible for opening up the configuration and building it.
 * Overall processing happens here.
 *
 * 
 */
public class JFetch {
    private final String JFETCHCONF = "conf/jfetch.xml";
    private final String JFETCHVERSION = "1.1a";
    private ArrayList maildrops;
    private Logger logger;
    private Configuration conf;
    private int pollTime;
    
    private JFetch() {
        maildrops = new ArrayList();
    }
    
    /**
     * Entry point into the system here.
     *
     * @param args a <code>String[]</code> value
     */
    public static void main(String[] args) throws Exception {
        JFetch jfetch = new JFetch();

        try {
            jfetch.init();
        } catch (Exception e) {
            System.out.println("CRITICAL ERROR! " + e.getMessage());
            e.printStackTrace();
            System.exit( 1 );
        }
        jfetch.start();
    }

    /**
     * Initialise the system.
     * Configure the following sub-systems
     * logging, global filters, and maildrops.
     *
     * Each of these sub-systems may configure further systems.
     * Of course, we are not bothered here ;)
     *
     * @exception Exception if an error occurs
     */
    private void init() throws Exception {
        ConfigurationBuilder configBuilder = new DefaultConfigurationBuilder();
        try {
            conf = configBuilder.build( JFETCHCONF );
        } catch ( ConfigurationException ce ) {
            System.out.println("Error in configuration file: " + JFETCHCONF);
            System.out.println(ce.getMessage());
			throw ce;
        }

        Configuration logConf = conf.getChild( "log" );

        // initialize the logging sub system.
        LogManager logManager = new LogManager();
        logManager.configure( logConf );
        logManager.init();

        pollTime = conf.getChild("poll").getValueAsInt(-1);
        if(pollTime <= 0)
            pollTime = -1;

        logger = LogKit.getLoggerFor( "JFetch" );
        logger.info("Starting up JFETCH version " + JFETCHVERSION);

        // setup the Component Manager
        DefaultComponentManager compManager = new DefaultComponentManager();

        // set up the DeliveryManager
        DeliveryManager deliveryManager =  DeliveryManager.getInstance();
        Configuration deliveryAgents[] = conf.getChildren("mda");

        for(int i=0; i<deliveryAgents.length; i++) {
            // get the DeliveryAgent
            DeliveryAgent mda = DeliveryAgent.setupAgent( deliveryAgents[i] );
            // install it in the Manager for the filters to have access to it
            deliveryManager.addAgent( mda );
        }

        // Initialize the global filterprocessor; this is optional
        FilterProcessor filterProc = new FilterProcessor();
        Configuration filterConf = conf.getChild( "filters" );
        filterProc.configure( filterConf );
        filterProc.init();
        compManager.put("jfetch.filters.GlobalFilterProcessor", filterProc);

        // initialize the mail drops and let them configure themselves
        Configuration configurations[] = conf.getChildren( "maildrop" );
        for( int i=0; i < configurations.length; i++ ) {
            Maildrop md =  Maildrop.getMaildrop( configurations[i] );

            // configure...
            if( md instanceof Configurable )
                md.configure( configurations[i] );

            // compose...
            if( md instanceof Composer )
                md.compose( compManager );

            // initialize
            if( md instanceof Initializable )
                md.init();

            // add to list...
            maildrops.add( md );
        }

        logger.info( "JFetch init done." );
    }

    /**
     * At this stage the system is fully configured and initialised.
     * Start the processing of the maildrops.
     *
     * @exception Exception if an error occurs
     */
    private void start()  {
        while(true) {
            logger.info("Starting processing of maildrops ...");
            for(Iterator i=maildrops.iterator(); i.hasNext(); ) {
	            ((Maildrop)i.next()).process();
            }
            logger.info("Done, processed " + maildrops.size() + " maildrops.");
            if(pollTime != -1) {
                logger.info("Sleeping for " + pollTime + " seconds...");
                try {
                    Thread.sleep(pollTime * 1000);
                } catch(InterruptedException ignore) {
                }
            } else {
                break;
            }
        }
    }       
} // JFetch
