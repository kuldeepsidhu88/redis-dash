package com.dash.model;


public class Info {
    private String version;
    private String role;
    private long maxMemory;
    private String maxMemoryHuman;
    private long usedMemory;
    private String usedMemoryHuman;
    private long totalKeys;
    private int connectedClients;
    private int connectionsReceived;
    private int connectionsRejected;
    private String hitRatio;
    private float uptime;
    private String uptimeDays;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(long usedMemory) {
        this.usedMemory = usedMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public String getMaxMemoryHuman() {
        return maxMemoryHuman;
    }

    public void setMaxMemoryHuman(String maxMemoryHuman) {
        this.maxMemoryHuman = maxMemoryHuman;
    }

    public String getUsedMemoryHuman() {
        return usedMemoryHuman;
    }

    public void setUsedMemoryHuman(String usedMemoryHuman) {
        this.usedMemoryHuman = usedMemoryHuman;
    }

    public long getTotalKeys() {
        return totalKeys;
    }

    public void setTotalKeys(long totalKeys) {
        this.totalKeys = totalKeys;
    }

    public int getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(int connectedClients) {
        this.connectedClients = connectedClients;
    }

    public int getConnectionsReceived() {
        return connectionsReceived;
    }

    public void setConnectionsReceived(int connectionsReceived) {
        this.connectionsReceived = connectionsReceived;
    }

    public int getConnectionsRejected() {
        return connectionsRejected;
    }

    public void setConnectionsRejected(int connectionsRejected) {
        this.connectionsRejected = connectionsRejected;
    }

    public String getHitRatio() {
        return hitRatio;
    }

    public void setHitRatio(String hitRatio) {
        this.hitRatio = hitRatio;
    }

    public float getUptime() {
        return uptime;
    }

    public void setUptime(float uptime) {
        this.uptime = uptime;
    }

    public String getUptimeDays() {
        return uptimeDays;
    }

    public void setUptimeDays(String uptimeDays) {
        this.uptimeDays = uptimeDays;
    }
}
