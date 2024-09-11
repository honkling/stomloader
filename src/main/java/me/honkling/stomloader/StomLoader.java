package me.honkling.stomloader;

import net.fabricmc.api.DedicatedServerModInitializer;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class StomLoader implements DedicatedServerModInitializer {
    private static File SERVER = new File("airbrush.jar");

    @Override public void onInitializeServer() {}
    public static void main() {
        if (!SERVER.exists()) {
            System.err.println("The Airbrush JAR file could not be found. Please upload it at `airbrush.jar`.");
            return;
        }

        System.out.println("Found the Airbrush JAR file. Running now.");
        Process process = null;

        try {
            var memory = System.getenv("SERVER_RAM");
            var processBuilder = new ProcessBuilder(getExecutablePath(), "-jar", "-Xms" + memory, "-Xmx" + memory, SERVER.getAbsolutePath())
                    .inheritIO();
            process = processBuilder.start();
            Runtime.getRuntime().addShutdownHook(new Thread(process::destroy));
            process.waitFor();
        } catch (IOException exception) {
            System.err.println("An error occurred when running Airbrush.");
            exception.printStackTrace();
        } catch (InterruptedException exception) {
            System.out.println("Shutting down...");
            process.destroy();
        }
    }

    private static String getExecutablePath() {
        var javaHome = System.getProperty("java.home");
        var file = new File(javaHome, "bin");
        return new File(file, "java").getAbsolutePath();
    }
}
