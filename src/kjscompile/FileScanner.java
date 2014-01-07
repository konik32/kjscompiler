/*
 * kjscompiler
 * Copyright (C) 2014  Oleksandr Knyga, oleksandrknyga@gmail.com
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

package kjscompile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author agnynk
 */
public class FileScanner {
    private static List<String> result;
    
    public static List<String> run(String rootPath, String search) {
        result = new ArrayList<String>();
        
        String ptext = "^" +
                (search.replace (".", "\\.")
                        .replace("*", ".*")
                ) +
                "$";
        
        File[] domains = new File(rootPath).listFiles();
        
        for (File domain : domains) {
            
            if (domain.isDirectory()) {
                File[] files = new File(domain.getAbsolutePath()).listFiles();

                for (File file : files) {
                    ProcessFile(file, ptext);
                }
            } else {
                ProcessFile(domain, ptext);
            }
        }
        
        return result;
    }
    
    private static void ProcessFile(File file, String ptext) {
        String absoultePath = file.getAbsolutePath();
        if (file.isFile() &&
                Pattern.matches(ptext, absoultePath)
            ) {
            result.add(absoultePath);
        }
    }
    
    
    
}
