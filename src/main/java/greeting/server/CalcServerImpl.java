package greeting.server;

import com.proto.calc.calcServiceGrpc;
import com.proto.calc.*;
import io.grpc.stub.StreamObserver;

public class CalcServerImpl extends calcServiceGrpc.calcServiceImplBase{

    @Override
    public void sum(sumRequest request, StreamObserver<sumResponse> responseStreamObserver){
        responseStreamObserver.onNext(sumResponse.newBuilder().setSum(request.getNumOne() + request.getNumTwo()).build());
        responseStreamObserver.onCompleted();
    }


}
