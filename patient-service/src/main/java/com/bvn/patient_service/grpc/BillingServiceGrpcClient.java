package com.bvn.patient_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    // request obj for grpc - will wait for response to come from billing service before continuing
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    // localhost:9001/BillingService/CreatePatientAccount -- local
    // aws.grpc:1231/BillingService/CreatePatientAccount -- when deployed to aws
    public BillingServiceGrpcClient(
            @Value("${billing.service.address.localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort
    ) {
        log.info("Connecting to Billing Service GRPC service at {}:{}", serverAddress, serverPort);
        // usePainText - disabled encryption for local development and testing
        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(
            String patientId,
            String name,
            String email
    ) {
        BillingRequest request = BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setEmail(email)
                .setName(name)
                .build();

        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Received Response from billing service via GRPC : {}", response);
        return response;
    }
}
