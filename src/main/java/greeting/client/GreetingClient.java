package greeting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    private static void doGreet(ManagedChannel channel){
        System.out.println("Entering doGreet");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingResponse response = stub.greet(GreetingRequest.newBuilder().setFirstName("Murali").build());
        System.out.println("Greeting: " + response.getResult());
    }

    private static void doGreetManyTimes(ManagedChannel channel){
        System.out.println("Entering do greet many times");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingRequest request = GreetingRequest.newBuilder().setFirstName("Murali").build();
        stub.greetManyTimes(request).forEachRemaining(response -> {
            System.out.println(response.getResult());
        });
    }


    static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Need one argument to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();

        switch(args[0]){
            case "greet": doGreet(channel); break;
            case "greet_many_times": doGreetManyTimes(channel); break;
            default:
                System.out.println("Invalid argument: " + args[0]);
                break;
        }


        System.out.println("Shutting Down");
        channel.shutdown();

    }

}
