/*
 * UnitGiga.java
 *
 * Created on 3. September 2001, 18:40
 */

package aw.util.units;


/**
 *  @see aw.util.units.Unit
 *  @see aw.util.units.UnitFactory
 *  @see aw.util.units.UnitSystem
 *  @see aw.util.units.UnitSystemSI
 *
 * @author  <a href='mailto:Achim.Westermann@gmx.de'>Achim Westermann</a>
 * @version 1.0
 */
public class UnitGiga extends Unit{
    /** Creates new Unit */
    public UnitGiga() {
        this.factor = 1000000000;
        this.prefix = "G";
    }
}
