package com.dumptruckman.minecraft.pluginbase.minecraft.location;

class DefaultCoordinates implements Coordinates, FacingCoordinates {

    private double x, y, z;
    private float pitch, yaw;

    DefaultCoordinates(final double x, final double y, final double z, final float pitch, final float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public float getYaw() {
        return this.yaw;
    }

    @Override
    public void add(final double x, final double y, final double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    @Override
    public void subtract(final double x, final double y, final double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }
}
