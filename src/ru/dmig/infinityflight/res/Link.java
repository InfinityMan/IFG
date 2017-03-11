/*
 * Copyright (C) 2016 Dmitry Tsvetkovsky
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.dmig.infinityflight.res;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Dmitry Tsvetkovsky
 */
public final class Link {
    
    /**
     * Get the array of lines in [file_name]
     * @param file_name Name of file
     * @return Array of strokes
     * @throws IOException 
     * @throws FileNotFoundException 
     */

    public String[] readResArray(String file_name) throws IOException, FileNotFoundException {
        return readRes(file_name).split("\n");
    }
    
    /**
     * Get all text in file with '\n'
     * @param file_name Name of file
     * @return Text
     * @throws IOException 
     * @throws FileNotFoundException 
     */

    public String readRes(String file_name) throws IOException, FileNotFoundException {
        InputStream is = this.getClass().getResourceAsStream(file_name);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bf = new BufferedReader(isr);

        String txtOfFile = "";

        while (bf.ready()) {
            txtOfFile += bf.readLine() + "\n";
        }

        return txtOfFile;
    }
}
