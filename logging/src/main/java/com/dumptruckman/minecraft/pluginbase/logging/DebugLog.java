/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.dumptruckman.minecraft.pluginbase.logging;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * The Multiverse debug-logger.
 */
class DebugLog {

    static final int ORIGINAL_DEBUG_LEVEL = 0;

    volatile int debugLevel = ORIGINAL_DEBUG_LEVEL;

    /**
     * Initializes the {@link com.dumptruckman.minecraft.pluginbase.logging.DebugLog} the first time this is called with the information passed in.  The DebugLog must be
     * initializes before use.
     *
     * @param logger The logger this debug logger should be linked to.
     * @param fileName The file name where a file copy of the log will be placed.
     */
    static DebugLog getDebugLog(final Logger logger, final String fileName) {
        return new DebugLog(logger, fileName);
    }

    Logger getLogger() {
        return this.log;
    }

    /**
     * Returns the file name set for this {@link com.dumptruckman.minecraft.pluginbase.logging.DebugLog}.
     *
     * @return the file name set for this {@link com.dumptruckman.minecraft.pluginbase.logging.DebugLog}.
     */
    public synchronized String getFileName() {
        return fileName;
    }

    public void setDebugLevel(final int debugLevel) {
        this.debugLevel = debugLevel;
    }

    public int getDebugLevel() {
        return debugLevel;
    }

    /**
     * The FileHandler for file logging purposes.
     */
    protected FileHandler fileHandler = null;
    /**
     * The Logger associated with this DebugLog.
     */
    protected final Logger log;

    private final String fileName;

    /**
     * Creates a new debug logger.
     *
     * @param logger The name of the logger.
     * @param file   The file to log to.
     */
    protected DebugLog(final Logger logger, final String file) {
        this.log = logger;
        this.fileName = file;
    }

    public final synchronized void open() {
        try {
            fileHandler = new FileHandler(fileName, true);
            Set<Handler> toRemove = new HashSet<Handler>(log.getHandlers().length);
            for (Handler handler : log.getHandlers()) {
                toRemove.add(handler);
            }
            for (Handler handler : toRemove) {
                log.removeHandler(handler);
            }
            log.addHandler(fileHandler);
            log.setLevel(Level.ALL);
            fileHandler.setFormatter(new LogFormatter());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(final LogRecord record) {
        log.log(record);
    }

    public boolean isClosed() {
        return fileHandler == null;
    }

    /**
     * Log a message at a certain level.
     *
     * @param level The log-{@link java.util.logging.Level}.
     * @param msg the message.
     */
    public void log(final Level level, final String msg) {
        log(new LogRecord(level, msg));
    }

    /**
     * Our log-{@link java.util.logging.Formatter}.
     */
    private static class LogFormatter extends Formatter {
        private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public String format(final LogRecord record) {
            final StringBuilder builder = new StringBuilder();
            final Throwable ex = record.getThrown();

            builder.append(this.date.format(record.getMillis()));
            builder.append(" [");
            builder.append(record.getLevel().getLocalizedName().toUpperCase());
            builder.append("] ");
            builder.append(record.getMessage());
            builder.append('\n');

            if (ex != null) {
                final StringWriter writer = new StringWriter();
                ex.printStackTrace(new PrintWriter(writer));
                builder.append(writer);
            }

            return builder.toString();
        }
    }

    /**
     * Closes this {@link com.dumptruckman.minecraft.pluginbase.logging.DebugLog}.
     */
    public synchronized void close() {
        if (fileHandler != null) {
            log.removeHandler(fileHandler);
            fileHandler.close();
            fileHandler = null;
        }
    }
}
