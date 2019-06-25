package com.wg.demo.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujuan
 * From: https://github.com/twitter/snowflake
 * An object that generates IDs.
 * This is broken into a separate class in case
 * we ever want to support multiple worker threads
 * per process
 */
public class IdWorker {
     
    protected static final Logger LOG = LoggerFactory.getLogger(IdWorker.class);
     
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
 
    private long twepoch = 1483200000000L;
 
    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    private long sequenceBits = 12L;
 
    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
 
    private long lastTimestamp = -1L;
 
    /**
     * 
     * @param workerId 机器ID
     * @param datacenterId 标识位
     */
    public IdWorker(long workerId, long datacenterId) {
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        LOG.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
    }
 
    public synchronized long nextId() {
        long timestamp = timeGen();

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
//        int hashCodeV = UUID.randomUUID().toString().hashCode();
//        if(hashCodeV < 0) {//有可能是负数
//            hashCodeV = - hashCodeV;
//        }
//        String nextId = workerId + String.format("%017d", hashCodeV);
//        return Long.valueOf(nextId);
        long nextId = ((timestamp - twepoch) << 22) | (datacenterId << 17) | (workerId << 12) | sequence;
        String nextIdTtr = String.valueOf(nextId);
        if (nextIdTtr.length() > 18) {
            nextId = Long.valueOf(nextIdTtr.substring(nextIdTtr.length() - 18, nextIdTtr.length()));
        }
        return nextId;
    }
 
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
 
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}