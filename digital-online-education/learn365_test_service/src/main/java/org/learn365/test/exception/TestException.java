package org.learn365.test.exception;

public class TestException extends Exception {

    private static final long serialVersionUID = 1L;

    public TestException() {
        super();
    }

    public TestException(String invalid_test_id) {
        super(invalid_test_id);
    }
}
