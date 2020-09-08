package com.github.sejoslaw.dotj;

import java.io.IOException;

/**
 * Entry point for Java side of the library.
 *
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        DotJService service = new DotJService(port);
        service.listen();
    }
}
