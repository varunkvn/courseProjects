
package jfetch.log;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log.output.FileOutputLogTarget;

/**
 * The <code>FileAppender</code> class extends a FileLogTarget to
 * append to a file and not overwrite it everytime.
 *
 * 
 */
public final class FileAppender extends FileOutputLogTarget {
    
    /**
     * Creates a new <code>FileAppender</code> instance.
     *
     */
    public FileAppender() {
    }
    
    /**
     * Creates a new <code>FileAppender</code> instance.
     *
     * @param fileName a <code>String</code> The file to append to
     * @exception IOException if an error occurs
     */
    public FileAppender(String fileName) throws IOException {
        setFileName(fileName);
    }
    
    /**
     * The <code>setFileName</code> opens the file and sets the m_output 
     * member.
     *
     * @param fileName a <code>String</code> Name of the file to log to
     * @exception IOException if an error occurs
     */
    public void setFileName(String fileName) throws IOException {
        final File file = new File(fileName);
        final File parent = file.getAbsoluteFile().getParentFile();
        if(!parent.exists()) parent.mkdirs();
        m_output =
                new OutputStreamWriter(new FileOutputStream(fileName, true));
        
    }
}
