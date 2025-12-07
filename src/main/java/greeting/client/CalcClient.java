package greeting.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.proto.calc.*;

public class CalcClient {

    public static void doSum(ManagedChannel channel){
        System.out.println("Entering doSum method");
        calcServiceGrpc.calcServiceBlockingStub stub = calcServiceGrpc.newBlockingStub(channel);
        sumResponse sumResponse = stub.sum(sumRequest.newBuilder().setNumOne(5).setNumTwo(5).build());
        System.out.println("Sum : " + sumResponse.getSum());

    }

    static void main(String[] args) {

        if (args.length ==0){
            System.out.println("Need one argument to work");
            return;
        }
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext().build();

        switch(args[0]){
            case "sum": doSum(channel); break;
            default:
                System.out.println("Invalid argument : " + args[0]);
                break;
        }
        System.out.println("Shutting Down");
        channel.shutdown();
    }
}
