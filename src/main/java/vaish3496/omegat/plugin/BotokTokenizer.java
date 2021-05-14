/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
               2010 Volker Berlin
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package vaish3496.omegat.plugin;

/**
 *
 * @author vaish3496
 */
import java.awt.Window;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


import org.omegat.core.Core;
import org.omegat.core.machinetranslators.BaseTranslate;
import org.omegat.util.Language;


public class BotokTokenizer extends BaseTranslate{

    /**
     * Plugin loader.
     */
    public static void loadPlugins() {
        Core.registerMachineTranslationClass(BotokTokenizer.class);
    }

    /**
     * Plugin unloader.
     */
    public static void unloadPlugins() {
    }

    @Override
    protected String getPreferenceName() {
        return "botok_tokenizer";
    }

    @Override
    protected String translate(Language lng, Language lng1, String string) throws Exception {
        String test;
        test = getTokenized(string);
        return test;
    }

    @Override
    public String getName() {
        return "Botok Tokenizer";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public void showConfigurationUI(Window parent) {
        // do nothing
    }
    
    
    /**
     * Get the tokenized String of the text using Botok NLP.
     */
    public String getTokenized(String text) {
        String output = "";
        try {
            String ss;
            String projectRoot = Core.getProject().getProjectProperties().getProjectRoot();
            File pythonFile = new File(projectRoot+"/pythonScripts/temp.py"+" "+text);
            String absolute_path = pythonFile.getAbsolutePath();
            Process p = Runtime.getRuntime().exec("python "+absolute_path);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            byte[] bytes = new byte[0];
            boolean sizeFound = false;
            int counter = 0;
            while ((ss = input.readLine()) != null) {
                if (!sizeFound) {
                    bytes = new byte[Integer.parseInt(ss)];
                    sizeFound = true;
                    continue;
                }
                bytes[counter] = (byte) Integer.parseInt(ss);
                counter++;   
            }

            output = new String(bytes,"UTF-8");
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        
        return output;
    }

}
