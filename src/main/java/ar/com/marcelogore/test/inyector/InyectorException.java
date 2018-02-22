package ar.com.marcelogore.test.inyector;



public class InyectorException extends RuntimeException {

    private static final long serialVersionUID = 8632385960882849787L;

    private InyectorException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
    public static InyectorException lanzar(String mensaje, Throwable causa) {
        return new InyectorException(mensaje, causa);
    }

    public static InyectorException lanzar(String mensaje) {
        return new InyectorException(mensaje, null);
    }
}
