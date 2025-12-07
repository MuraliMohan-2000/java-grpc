package greeting.server;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;

public class GreetingServerImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseStreamObserver){
        responseStreamObserver.onNext(GreetingResponse.newBuilder().setResult("Hello " + request.getFirstName()).build());
        responseStreamObserver.onCompleted();
    }

    @Override
    public void greetManyTimes(GreetingRequest request, StreamObserver<GreetingResponse> responseStreamObserver){
        GreetingResponse response = GreetingResponse.newBuilder().setResult("Hello : " + request.getFirstName()).build();
        for(int i=0; i<10; i++){
            responseStreamObserver.onNext(response);
        }
        responseStreamObserver.onCompleted();
    }
}
