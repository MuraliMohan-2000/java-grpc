package greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    static void main() throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port)
                .addService(new GreetingServerImpl())
                .addService(new CalcServerImpl()).build();
        server.start();
        System.out.println("Server Started");
        System.out.println("Listening on port: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Received Shoutdown message");
            server.shutdown();
            System.out.println("Server Stopped");
        }));

        server.awaitTermination();
    }
}