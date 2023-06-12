// Software created by Jack Meng (AKA exoad). Licensed by the included "LICENSE" file. If this file is not found, the project is fully copyrighted.

package com.jackmeng.ansicolors;

/**
 * Represents colors to use for internal ANSI coloring.
 *
 * Private because the programmer should not use this class directly and should
 * mostly rely on pre-existing methods in jm_Ansi
 *
 * @author Jack Meng
 */
class use_Help
{

    static int clamp(int min, int max, int v)
    {
        return v < min ? min : Math.min(v, max);
    }



}