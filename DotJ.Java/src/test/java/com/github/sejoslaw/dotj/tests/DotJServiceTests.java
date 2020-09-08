package com.github.sejoslaw.dotj.tests;

import com.github.sejoslaw.dotj.DotJService;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class DotJServiceTests {
    @Test
    public void shouldConnectToService() throws IOException {
        String hostname = "localhost";
        int port = 8765;

        // Create service

        DotJService service = new DotJService(port);
        service.listen();

        // Call client

        try (Socket socket = new Socket(hostname, port)) {

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);

            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            System.out.println(data);
        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
