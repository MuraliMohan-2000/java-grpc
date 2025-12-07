package greeting.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.proto.primes.*;

import java.util.Random;

public class PrimesClient {

    private static void primesReq(ManagedChannel channel) {
        System.out.println("Entering primesReq method");
        int requestedPrime = new Random().nextInt(Integer.MAX_VALUE);
        primesGrpc.primesBlockingStub stub = primesGrpc.newBlockingStub(channel);
        primesRequest request = primesRequest.newBuilder().setNum1(requestedPrime).build();
        System.out.println("Requested Prime Decomposition number : " + requestedPrime);
        stub.getPrimes(request).forEachRemaining(response -> {
            System.out.println(response.getPrimes());
        });
    }

    static void main(String[] args) {
        if (args.length ==0){
            System.out.println("Need on argument to work");
        }
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        switch(args[0]){
            case "primes" : primesReq(channel); break;
            default:
                System.out.println("Invalid Argument :" + args[0]);
                break;
        }
        System.out.println("Shutting Down");
        channel.shutdown();
    }
}
