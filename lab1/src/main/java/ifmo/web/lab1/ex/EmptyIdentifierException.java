package ifmo.web.lab1.ex;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "ifmo.web.lab1.ex.EmptyIdentifierExceptionBean")
public class EmptyIdentifierException extends Exception {
    private final EmptyIdentifierExceptionBean fault;

    public EmptyIdentifierException(String message, EmptyIdentifierExceptionBean fault) {
        super(message);
        this.fault = fault;
    }

    public EmptyIdentifierException(String message, EmptyIdentifierExceptionBean fault, Throwable cause){
        super(message, cause);
        this.fault = fault;
    }

    public EmptyIdentifierExceptionBean getFaultInfo() {
        return fault;
    }
}