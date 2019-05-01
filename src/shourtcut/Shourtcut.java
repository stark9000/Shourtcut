/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shourtcut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author bitxnor
 */
public class Shourtcut {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "shourtcut.jar";
        path = '"' + path + '"';
        try {
            File file = File.createTempFile("shortcut_geni", ".vbs");
            file.deleteOnExit();
            try (FileWriter fw = new java.io.FileWriter(file)) {
                String vbs = "Set oWS = WScript.CreateObject(\"WScript.Shell\")  \n"
                        + "sLinkFile = oWS.ExpandEnvironmentStrings(\"%HOMEDRIVE%%HOMEPATH%\\Desktop\\jar_file_name.lnk\")\n"
                        + "Set oLink = oWS.CreateShortcut(sLinkFile)\n "
                        + "oLink.TargetPath = oWS.ExpandEnvironmentStrings(" + path + ")\n"
                        + "oLink.Save \n";
                fw.write(vbs);
            }
            Process p = Runtime.getRuntime().exec("wscript " + file.getPath());
            p.waitFor();

        } catch (IOException | InterruptedException e) {
            System.out.println("" + e);
        }
    }

}
