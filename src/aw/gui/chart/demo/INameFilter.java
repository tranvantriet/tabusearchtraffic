/*
 * 
 *  INameFilter.java  jbuzzer
 *  Copyright (C) Achim Westermann, created on 03.07.2004, 22:18:53  
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


/**
 * <p>
 * A story on the side: 
 * There are:
 * <ul>
 * 	<li>
 *  {@link java.io.FileFilter}
 *  <li>
 *   {@link java.io.FileNameFilter}
 *  <li>
 *   {@link javax.swing.filechooser.FileFilter}
 * </ul>
 * </p>
 * <p>
 * Dumb all over hein. Well here's another one. 
 * Because we cannot stick to File instances when 
 * working with URL's .
 * </p>
 * @author <a href="mailto:Achim.Westermann@gmx.de">Achim Westermann</a>
 *
 */
public interface INameFilter 
{
	public boolean accept(String urlstring);

}
