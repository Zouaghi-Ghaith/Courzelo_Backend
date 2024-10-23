package com.example.corzello.Service;

import java.io.*;

public class PythonScriptRunner {

    public boolean runPythonScript(String variable) {
        try {
            String scriptPath = "C:\\Users\\Zaibi\\Desktop\\PIBACK\\Integration final\\Courzelo\\src\\main\\java\\com\\example\\corzello\\flask_integration\\recruitement_similarity.py";
            // Build the command to run the Python script with the variable as argument
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath, variable);

            // Start the process
            Process process = pb.start();

            // Read output from Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Read error output from Python script
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.out.println("Error: " + line);
            }

            // Wait for the process to finish
            process.waitFor();

            // Check the exit value of the process
            int exitValue = process.exitValue();
            if (exitValue == 0) {
                System.out.println("Python script executed successfully.");
                return true; // Return true for success
            } else {
                System.out.println("Python script failed with exit code: " + exitValue);
                return false; // Return false for error
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // Return false for error
        }
    }
    public boolean runPythonScript2(String variable) {
        try {
            String scriptPath = "C:\\Users\\Zaibi\\Desktop\\PIBACK\\Integration final\\Courzelo\\src\\main\\java\\com\\example\\corzello\\flask_integration\\recruitement_similarity.py";
            // Build the command to run the Python script with the variable as argument
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath, variable);

            // Start the process
            Process process = pb.start();

            // Read output from Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Read error output from Python script
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.out.println("Error: " + line);
            }

            // Wait for the process to finish
            process.waitFor();

            // Check the exit value of the process
            int exitValue = process.exitValue();
            if (exitValue == 0) {
                System.out.println("Python script executed successfully.");
                return true; // Return true for success
            } else {
                System.out.println("Python script failed with exit code: " + exitValue);
                return false; // Return false for error
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // Return false for error
        }
    }
}
