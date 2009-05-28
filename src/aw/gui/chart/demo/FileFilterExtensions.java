/*
 * 
 *  FileFilterExtensions.java, a FileFilter implementation that 
 *  filters files by their extension.
 *  Copyright (C) Achim Westermann, created on 01.07.2004, 12:18:29  
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *  
 *  If you modify or optimize the code in a useful way please let me know.
 *  Achim.Westermann@gmx.de
 *	
 */
package aw.gui.chart.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * <p>
 * Configureable implementation of {@link javax.swing.filechooser.FileFilter}
 * that filters files by their extension (e.g.: ".txt").
 * </p>
 * <p>
 * The extension Strings are provided to the constructor (no configuration of
 * initialized instance provided yet) and have to be the sole extension without
 * the dot.
 * </p>
 * <p>
 * This class (all all {@link javax.swing.filechooser.FileFilter}is most often
 * used to configure {@link javax.swing.JFileChooser}dialogs. Therefore it
 * accepts all directories to allow browsing.
 * </p>
 * <h3>Example usage:</h3>
 * <p>
 * 
 * <pre>
 * 
 *  ...
 *  JFileChooser fileChooser = new JFileChooser();
 *  FileFilter soundFileFilter = new FileFilterExtensions(new String[]{&quot;wav&quot;,&quot;mp3&quot;});
 *  fileChooser.setFileFilter(soundFileFilter); 
 *  ...
 *  
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:Achim.Westermann@gmx.de>Achim Westermann </a>
 */
public final class FileFilterExtensions extends FileFilter implements INameFilter {
  private String[] extensions;

  private boolean isWindows = false;

  /**
   * 
   * @param extensionsWithoutDot
   *          A String[] containing extension strings without the dot like:
   *          <nobr><code>new String[]{"bat","txt","dict"}</code> </nobr>.
   */
  public FileFilterExtensions(String[] extensionsWithoutDot) throws IllegalArgumentException {
    this.verify(extensionsWithoutDot);
    this.extensions = extensionsWithoutDot;
    this.isWindows = this.isWindows();
  }

  /**
   * @param extensions
   *          The array with the Strings of extensions.
   * @throws IllegalArgumentException
   *           If a String of the array is null or contains a dot ('.').
   */
  private void verify(String[] extensions) throws IllegalArgumentException {
    String current;
    StringBuffer msg = new StringBuffer();
    for (int i = extensions.length - 1; i >= 0; i--) {
      current = extensions[i];
      if (current == null) {
        msg.append("Extension at index " + i + " is null!\n");
      }
      else if (current.indexOf('.') != -1) {
        msg.append("Extension \"" + current + "\" contains a dot!\n");
      }
    }
    if (msg.length() > 0) {
      throw new IllegalArgumentException(msg.toString());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.io.FileFilter#accept(java.io.File)
   */
  public boolean accept(File pathname) {
    if (pathname.isDirectory()) {
      return true;
    }
    return acceptNoDirectory(pathname.getAbsolutePath());
  }

  /*
   * (non-Javadoc)
   * 
   * @see jbuzzer.util.INameFilter#accept(java.lang.String)
   */
  public boolean accept(String urlstring) {
    if (isDirectory(urlstring)) {
      return true;
    }
    return acceptNoDirectory(urlstring);
  }

  private boolean acceptNoDirectory(String noDirFileNoURL) {
    boolean ret = false;
    // search for extension without dot.
    StringTokenizer tokenizer = new StringTokenizer(noDirFileNoURL, ".");
    String extension = "no.txt"; // a dot, because verify will not allow these
                                  // tokens: won't accept, if no extension in
                                  // pathname.
    while (tokenizer.hasMoreElements()) {
      extension = tokenizer.nextToken();
    }
    for (int i = this.extensions.length - 1; i >= 0; i--) {
      if (this.extensions[i].equals(extension)) {
        ret = true;
        break;
      }
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.filechooser.FileFilter#getDescription()
   */
  public String getDescription() {
    StringBuffer ret = new StringBuffer();
    int len = this.extensions.length;
    for (int i = 0; i < len; i++) {
      ret.append("*.").append(this.extensions[i]);
      if (i < (len - 1)) {
        ret.append(",");
      }
    }
    return ret.toString();
  }

  private boolean isDirectory(String urlstring) {
    boolean ret = false;
    boolean isURL = false;
    try {
      // try, if URL (expensive):
      URL test = new URL(urlstring);
      isURL = true;
    } catch (MalformedURLException e) {
      // nop.
    }

    int lastDot = urlstring.lastIndexOf('.');
    int lastSeparator;
    // Could be minimized but is more talking this way.
    if (isURL) {
      lastSeparator = urlstring.lastIndexOf('/');
    }
    else {
      if (this.isWindows) {
        lastSeparator = urlstring.lastIndexOf('\\');
      }
      else {
        lastSeparator = urlstring.lastIndexOf('/');
      }
    }

    if (lastSeparator == -1) {
      if (lastDot == -1) {
        // top host without a path.
        ret = true;
      }
      else {
        ret = false;
      }
    }
    else {
      if (lastDot == -1) {
        ret = true;
      }
      else if (lastDot > (lastSeparator + 1)) {
        ret = false;
      }
      else {
        ret = true;
      }
    }
    return ret;
  }

  /**
   * Needed for {@link #isDirectory(String)}: We cannot use
   * {@link System#getProperty(java.lang.String)}to determine file separators
   * in applet context. That would possibly throw an SecurityAccessException.
   * 
   * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann </a>
   */
  private boolean isWindows() {
    boolean ret = false;
    File[] roots = FileSystemView.getFileSystemView().getRoots();
    for (int i = 0; i < roots.length; i++) {
      if (roots[i].getAbsolutePath().indexOf(':') != -1) {
        ret = true;
        break;
      }
    }
    return ret;
  }
}