//package org.fos.mobile.config;
//
//public class EmulatorManager {
//    public void startEmulator(String avdName) {
//        try {
//            // Command to start the AVD
//            String command = "/Users/admin/Library/Android/sdk/emulator/emulator -avd " + avdName;
//
//            // Execute the command
//            Process process = Runtime.getRuntime().exec(command);
//
////            // Optional: Read the output of the command
////            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////            String line;
////            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
////            }
//
//            System.out.println("Android Virtual Device " + avdName + " started successfully.");
//
//            // Wait for the emulator to be ready
//            Thread.sleep(10000); // Wait for 30 seconds (adjust time as necessary)
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Failed to start Android Virtual Device: " + avdName);
//        }
//    }
//
//    public void startEmulator(String avdName) {
//        try {
//            // Command to start the AVD
//            String command = "/Users/admin/Library/Android/sdk/emulator/emulator -avd " + avdName;
//
//            // Execute the command
//            Process process = Runtime.getRuntime().exec(command);
//
////            // Optional: Read the output of the command
////            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////            String line;
////            while ((line = reader.readLine()) != null) {
////                System.out.println(line);
////            }
//
//            System.out.println("Android Virtual Device " + avdName + " started successfully.");
//
//            // Wait for the emulator to be ready
//            Thread.sleep(10000); // Wait for 30 seconds (adjust time as necessary)
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Failed to start Android Virtual Device: " + avdName);
//        }
//    }
//        public void stopEmulator(String emulatorName) {
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder(
//                    "adb", "-s", emulatorName, "emu", "kill");
//            Process process = processBuilder.start();
//            process.waitFor();
//            System.out.println("Emulator " + emulatorName + " stopped.");
//        } catch (Exception e) {
//            System.err.println("Failed to stop the emulator: " + emulatorName);
//            e.printStackTrace();
//        }
//    }
//}
