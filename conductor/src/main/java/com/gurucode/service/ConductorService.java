package com.gurucode.service;

import com.codeguru.services.grpc.transaction.model.ConductorProto;
import com.codeguru.services.grpc.transaction.model.ConductorServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ConductorService extends ConductorServiceGrpc.ConductorServiceImplBase {


    @Override
    public void processRequest(ConductorProto.TransactionEvent request, StreamObserver<ConductorProto.TransactionEvents> responseObserver) {
        ConductorProto.TransactionEvent event = ConductorProto.TransactionEvent.newBuilder().setName("TestResponse "+request.getName()).build();
        ConductorProto.TransactionEvents events = ConductorProto.TransactionEvents.newBuilder().addEvents(event).build();
        responseObserver.onNext(events);
        responseObserver.onCompleted();
    }
}
