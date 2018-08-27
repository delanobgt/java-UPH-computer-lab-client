/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrutalForce {

    public static void stop() {

    }

    public static void start() {

    }

    public static void killRunningApps() {
        String[] allowedImageNames = {
            "MySQLWorkbench.exe",
            "taskhostw.exe",
            "fbguard.exe",
            "Memory Compression",
            "igfxTray.exe",
            "AdminService.exe",
            "MSASCuiL.exe",
            "WmiPrvSE.exe",
            "AvastSvc.exe",
            "nvvsvc.exe",
            "SearchFilterHost.exe",
            "RuntimeBroker.exe",
            "PresentationFontCache.exe",
            "sihost.exe",
            "GFNEXSrv.exe",
            "CxAudMsg64.exe",
            "fontdrvhost.exe",
            "AvastBrowserCrashHandler64.exe",
            "svchost.exe",
            "RemindersServer.exe",
            "dasHost.exe",
            "dwm.exe",
            "igfxHK.exe",
            "DMedia.exe",
            "lsass.exe",
            "SearchProtocolHost.exe",
            "AsLdrSrv.exe",
            "CAudioFilterAgent64.exe",
            "mysqld.exe",
            "igfxEM.exe",
            "SearchUI.exe",
            "GoogleUpdate.exe",
            "esif_uf.exe",
            "SppExtComObj.Exe",
            "SkypeHost.exe",
            "SASrv.exe",
            "QMEmulatorService.exe",
            "smartscreen.exe",
            "vmnat.exe",
            "ConnectifyService.exe",
            "AvastUI.exe",
            "gxxsvc.exe",
            "dllhost.exe",
            "aips.exe",
            "smss.exe",
            "Microsoft.Photos.exe",
            "WMIC.exe",
            "HControl.exe",
            "esif_assist_64.exe",
            "escsvc64.exe",
            "unsecapp.exe",
            "netbeans64.exe",
            "Update.exe",
            "TeamViewer_Service.exe",
            "SgrmBroker.exe",
            "SettingSyncHost.exe",
            "E_YATII2E.EXE",
            "WUDFHost.exe",
            "Calculator.exe",
            "ApplicationFrameHost.exe",
            "EpicGamesLauncher.exe",
            "sppsvc.exe",
            "eaglesvclic.exe",
            "ConnectifyNetServices.exe",
            "spoolsv.exe",
            "csrss.exe",
            "winlogon.exe",
            "explorer.exe",
            "nvtray.exe",
            "ctfmon.exe",
            "fbserver.exe",
            "vmware-usbarbitrator64.exe",
            "atom.exe",
            "SystemSettings.exe",
            "wininit.exe",
            "KMS-R@1n.exe",
            "armsvc.exe",
            "ATKOSD2.exe",
            "vmware-authd.exe",
            "ShellExperienceHost.exe",
            "MsMpEng.exe",
            "AvastBrowserCrashHandler.exe",
            "IDMan.exe",
            "NisSrv.exe",
            "nvxdsync.exe",
            "vmnetdhcp.exe",
            "SearchIndexer.exe",
            "SecurityHealthService.exe",
            "igfxCUIService.exe",
            "services.exe",
            "MySQLNotifier.exe",
            "audiodg.exe",
            "Video.UI.exe",
            "UnrealCEFSubProcess.exe",
            "conhost.exe",
            "NvBackend.exe",
            "jusched.exe",
            "java.exe",
            "javaw.exe",
            "javac.exe"
        };
        Set<String> allowedSet = new HashSet<>(Arrays.asList(allowedImageNames));

//        String[] tokens = allowedImageNames[0].split("[\\r\\n]+");
//        Set<String> set = new HashSet<>();
//        for (String token : tokens) {
//            set.add(token.trim());
//        }
//        for (String item : set) {
//            System.out.println("\""+item+"\",");
//        }
//        System.exit(0);
        List<String> commandList = new ArrayList<>();
        commandList.add("taskkill");
        commandList.add("/F");
        for (String imageName : allowedImageNames) {
            commandList.add("/FI");
            commandList.add(String.format("\"IMAGENAME ne %s\"", imageName));
        }

        ProcessBuilder pb = new ProcessBuilder(commandList);
        try {
            Process p = pb.start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private static Set<String> getAllProcessesSet() {
        Set<String> allSet = new HashSet<>();
        try {
            ProcessBuilder pb = new ProcessBuilder("wmic", "process","get", "caption");
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                allSet.add(line.trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(BrutalForce.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allSet;
    }
}
