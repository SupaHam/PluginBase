package com.dumptruckman.minecraft.pluginbase.minecraft.location;

import org.jetbrains.annotations.NotNull;

public interface BlockCoordinates {

    /**
     * Gets the name of the world in which these coordinates are located.
     *
     * @return The name of the world in which these coordinates are located.
     */
    @NotNull
    String getWorld();

    /**
     * Gets the block x coordinate represented by this location.
     *
     * @return The block x coordinate represented by this location.
     */
    int getBlockX();

    /**
     * Gets the block y coordinate represented by this location.
     *
     * @return The block y coordinate represented by this location.
     */
    int getBlockY();

    /**
     * Gets the block z coordinate represented by this location.
     *
     * @return The block z coordinate represented by this location.
     */
    int getBlockZ();

    void add(final int x, final int y, final int z);

    void subtract(final int x, final int y, final int z);
}
