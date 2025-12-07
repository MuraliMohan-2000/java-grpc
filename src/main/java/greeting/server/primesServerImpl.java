package greeting.server;

import com.proto.primes.*;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class primesServerImpl extends  primesGrpc.primesImplBase{

    @Override
    public void getPrimes(primesRequest request, StreamObserver<primesResponse> responseStreamObserver){
        List<Integer> resultList = primeDecomposition(request.getNum1());
        for (int i : resultList){
            responseStreamObserver.onNext(primesResponse.newBuilder().setPrimes(i).build());
        }
        responseStreamObserver.onCompleted();
    }

    private List<Integer> primeDecomposition(int num1) {
        List<Integer> res = new ArrayList<>();
        int n = num1;

        for (int k = 2; k * k <= n; k++) {
            while (n % k == 0) {
                res.add(k);
                n /= k;
            }
        }

        // If anything > 1 is left, it's a prime
        if (n > 1) {
            res.add(n);
        }
        return res;
    }

}
