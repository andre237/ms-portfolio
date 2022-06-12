package com.andre.training.core.shared;

public abstract class UseCase<I extends UseCase.Input, O extends UseCase.Output> {

    public abstract O execute(I input) throws Exception;

    public interface Input {}
    public interface Output {}

}
