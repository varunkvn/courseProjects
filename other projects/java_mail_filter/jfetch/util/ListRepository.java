package jfetch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

import org.apache.log.Logger;
import org.apache.log.LogKit;
import org.apache.avalon.Initializable;
import org.apache.avalon.configuration.Configurable;
import org.apache.avalon.configuration.Configuration;
import org.apache.avalon.configuration.ConfigurationException;

/**
 * Serializable List of lightweight objects.
 *
 * 
 */
public class ListRepository implements Configurable, Initializable {
    private List list;
    private int limit = -1;
    private String repository;
    private String name;
    private Logger logger;

    public ListRepository() {
        logger = LogKit.getLoggerFor("liststore");
    }

    /**
     * set name, limit, repository
     * &lt; storage name=&quot; uidlfile &quot; limit= &quot; 8192 
     * &quot; destination= &quot; ../spool &quot;  /&gt;
     *
     * @param conf a <code>Configuration</code> value
     * @exception ConfigurationException if an error occurs
     */
    public void configure(Configuration conf) throws ConfigurationException {
        name = conf.getAttribute("name");
        limit = conf.getAttributeAsInt("limit", -1);
        repository = conf.getAttribute("destination");
    }

    /**
     * Describe <code>init</code> method here.
     *
     */
    public void init() {
        list = new ArrayList();
        load();
        logger.info("Storage " + name + " init done, " + list.size() + " objects loaded.");
    }

    /**
     * Describe <code>add</code> method here.
     *
     * @param obj an <code>Object</code> value
     */
    public void add(Object obj) {
        if(!list.contains(obj)) {
            list.add(obj);
            save();
        }
    }

    /**
     * Describe <code>contains</code> method here.
     *
     * @param obj an <code>Object</code> value
     * @return a <code>boolean</code> value
     */
    public boolean contains(Object obj) {
        return list.contains(obj);
    }

    /**
     * Describe <code>remove</code> method here.
     *
     * @param obj an <code>Object</code> value
     * @return a <code>boolean</code> value
     */
    public boolean remove(Object obj) {
        boolean result = list.remove(obj);
        if(result)
            save();
        return result;
    }

    /**
     * do we really need this???
     *
     * @return an <code>Iterator</code> value
     */
    public Iterator list() {
        // This is a PITA - can't let a modifiable list out...
        return Collections.unmodifiableList(list).iterator();
    }

    /**
     * Load the list from the repository
     *
     */
    private void load() {
        BufferedInputStream fin = null;
        try {
            fin = new BufferedInputStream(new FileInputStream( repository ));
            ObjectInputStream oin = new ObjectInputStream( fin );
            list = (ArrayList) oin.readObject();
            logger.debug("Read from store " + name + " successful");
        } catch(Exception ex) {
            logger.error("Error while loading list",  ex);
        } finally {
            try {
                if(fin != null)
                    fin.close();
            } catch(IOException ignore) {} 
        }
    }

    /**
     * Save the list to the repository
     *
     */
    private void save() {
        BufferedOutputStream fout = null;
        // dont save more than needed
        checkLimit();
        try {
            File file = new File( repository );
            file.getParentFile().mkdirs();
            //            file.mkdirs();
            fout = new BufferedOutputStream( new FileOutputStream( file ) );
            ObjectOutputStream oout = new ObjectOutputStream( fout );
            oout.writeObject( list );
        } catch(Exception ex) {
            logger.error("Error while saving list",  ex);
        } finally {
            try {
                if(fout != null)
                    fout.close();
            } catch(IOException ignore) {} 
        }
        
    }

    /**
     * Check if we are reaching the limit specified by the user
     *
     */
    private final void checkLimit() {
		for( int size = list.size()-1; size > limit; size-- )
			list.remove( size );
    }
}
