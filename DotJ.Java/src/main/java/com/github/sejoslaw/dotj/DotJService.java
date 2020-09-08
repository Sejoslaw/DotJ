package com.github.sejoslaw.dotj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Service which is responsible for listening for messages from the .NET side.
 *
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class DotJService extends Thread {
    private final ServerSocket serverSocket;

    private boolean isRunning;

    public DotJService(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    /**
     * Starts listening.
     */
    public void listen() {
        this.start();
    }

    /**
     * Keeps Server listening for the Messages.
     */
    public void run() {
        this.isRunning = true;

        while (this.isRunning) {
            try {
                Socket socket = this.serverSocket.accept();
                DotJWorker worker = new DotJWorker(this, socket);
                worker.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the service.
     */
    public void stopService() {
        this.isRunning = false;
        this.interrupt();
    }
}
