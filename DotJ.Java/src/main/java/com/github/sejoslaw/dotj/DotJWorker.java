package com.github.sejoslaw.dotj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Worker which is responsible for handling the data from socket.
 *
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class DotJWorker extends Thread {
    private final DotJService service;
    private final Socket socket;

    public DotJWorker(DotJService service, Socket socket) {
        this.service = service;
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.socket.getOutputStream());

            this.handle(reader, writer);

            reader.close();
            writer.close();
            this.socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handle(BufferedReader reader, PrintWriter writer) {
        List<String> inputData = reader.lines().collect(Collectors.toList());
        String outputData = this.handle(inputData);
        writer.write(outputData);
        writer.flush();
    }

    private String handle(List<String> inputData) {
        return "TEST";
    }
}

/**
 * https://www.infoworld.com/article/2853780/socket-programming-for-scalable-systems.html
 * https://docs.microsoft.com/pl-pl/dotnet/api/system.net.sockets.socket?view=netcore-3.1
 * https://www.codejava.net/java-se/networking/java-socket-client-examples-tcp-ip
 *
 *
 * https://i0.wp.com/nextlevel-entrepreneur.com/wp-content/uploads/2018/01/candlestick-en.png?w=1800&ssl=1
 * https://bpcdn.co/images/2011/03/order-types.png
 *
 *
 * https://www.jobbank.gc.ca/jobsearch/jobsearch?searchstring=software+engineer&action=s2
 * https://www.numbeo.com/cost-of-living/in/Vancouver
 */
