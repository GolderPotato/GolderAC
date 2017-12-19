package fr.golderpotato.ac.utils;

import org.bukkit.Location;

/**
 * Created by Eliaz on 25/02/2017.
 */
public class PositionUtils {


    public static double getDirection(final Location from, final Location to)
    {
        if ((from == null) || (to == null)) {
            return 0.0D;
        }
        final double difX = to.getX() - from.getX();
        final double difZ = to.getZ() - from.getZ();

        return wrapAngleTo180_float((float)(Math.atan2(difZ, difX) * 180.0D / 3.141592653589793D) - 90.0F);
    }

    public static float wrapAngleTo180_float(float value)
    {
        value %= 360.0F;
        if (value >= 180.0F) {
            value -= 360.0F;
        }
        if (value < -180.0F) {
            value += 360.0F;
        }
        return value;
    }


    public static double getDistance(final Location victime, final Location damager){
        double toReturn = 0.0D;
        double xSqr = (damager.getX() - victime.getX()) * (damager.getX() - victime.getX());
        double ySqr = (damager.getY() - victime.getY()) * (damager.getY() - victime.getY());
        double zSqr = (damager.getZ() - victime.getZ()) * (damager.getZ() - victime.getZ());
        double sqrt = Math.sqrt(xSqr + ySqr + zSqr);
        toReturn = Math.abs(sqrt);
        return toReturn;
    }
}
