/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package com.dumptruckman.minecraft.pluginbase.config;

import com.google.common.io.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

class EncodedYamlConfiguration extends CommentedYamlConfiguration {

    private final Charset charset;

    EncodedYamlConfiguration(final Charset charset, final boolean doComments) throws UnsupportedEncodingException {
        super(doComments);
        this.charset = charset;
    }

    EncodedYamlConfiguration(final String charset, final boolean doComments) throws UnsupportedEncodingException, IllegalCharsetNameException {
        this(Charset.forName(charset), doComments);
    }

    @Override
    public void save(final File file) throws IOException {
        Files.createParentDirs(file);

        final String data = saveToString();

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
            writer.write(data);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
