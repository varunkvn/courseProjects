package jfetch.log;

import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.log.LogKit;
import org.apache.log.Logger;
import org.apache.log.LogTarget;
import org.apache.log.Category;
import org.apache.log.Priority;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.Configuration;

/**
 * The LogManager configures and initializes the Logging sub-system.
 *
 * TODO
 * Get priorities et al. in place.
 *
 * 
 */
public class LogManager implements Configurable, Initializable {
    String fileName;
    Priority.Enum priority;
    Configuration conf;
    private HashMap priorityHash;
	private boolean enabled;

    public LogManager() {
        priorityHash = new HashMap();
        priorityHash.put("DEBUG", Priority.DEBUG);
        priorityHash.put("INFO", Priority.INFO);
        priorityHash.put("WARN", Priority.WARN);
        priorityHash.put("ERROR", Priority.ERROR);
        priorityHash.put("FATAL_ERROR", Priority.FATAL_ERROR);
    }

    /**
     * Reads up the configuration values from the configuration.
     *
     * @param conf a <code>Configuration</code> value
     */
    public void configure(Configuration conf) {
        this.conf = conf;
        fileName = conf.getAttribute( "target", "logs/jfetch.log" );
        String priorityStr = conf.getAttribute( "priority", "DEBUG" );
		enabled = conf.getAttributeAsBoolean( "enabled", true );

        // get the priority of the LogKit to be set
        priority = (Priority.Enum) priorityHash.get( priorityStr );
    }

    /**
     * Initializes the logging sub-system. 
     *
     * @exception Exception if an error occurs
     */
    public void init() throws Exception {
        LogKit.setGlobalPriority( priority );
		if( enabled ) {
	        LogTarget logTarget = new FileAppender( fileName );
    	    LogKit.setDefaultLogTarget(logTarget);
		}
    }
}

