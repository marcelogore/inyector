package ar.com.marcelogore.test.inyector;


import java.lang.reflect.Field;

public class Inyector<T> {

    private Class<T> claseInyectada = null;
    
    private T instanciaInyectada = null;
    
    public Inyector<T> inyectar(Object inyectable) {

        Class<?> claseActual = claseInyectada;
        Field campoInyectado = null;
        
        do {
            
            Field[] camposInyectables = claseActual.getDeclaredFields();
            campoInyectado = inyectarCampo(camposInyectables, inyectable);
            claseActual = claseActual.getSuperclass();
            
        } while (campoInyectado == null && claseActual.getSuperclass() != null);
        
        if (campoInyectado == null) {
            
            throw InyectorException.lanzar("No encuentro atributos de este tipo para inyectar");
        }

        return this;
    }

    private Field inyectarCampo(Field[] camposDeLaClase, Object valorInyectable) {
        
        Field campoInyectado = null;
        
        boolean inyectado = false;

        if (valorInyectable != null) {
            
            for (Field campoInyectable : camposDeLaClase) {
                
                if (campoInyectable.getType().isInstance(valorInyectable)) {
                    
                    if (!inyectado) {
                        
                        campoInyectable.setAccessible(true);
                        
                        try {
                            
                            campoInyectable.set(instanciaInyectada, valorInyectable);
                            inyectado = true;
                            campoInyectado = campoInyectable;
                            
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            
                            throw InyectorException.lanzar("No pude modificar un atributo", e);
                        }
                    } else {
                        
                        throw InyectorException.lanzar("Existen dos campos del mismo tipo y no sé dónde inyectar");
                    }
                }
            }
        } else {
            
            throw InyectorException.lanzar("No puedo inyectar un atributo nulo");
        }

        return campoInyectado;
    }
    
    public static <T> Inyector<T> instanciar(Class<T> claseInyectada) {

        Inyector<T> probador = new Inyector<>();
        probador.claseInyectada = claseInyectada;
        
        try {
            
            probador.instanciaInyectada = claseInyectada.newInstance();
        
        } catch (IllegalAccessException | InstantiationException e) {
            
            throw InyectorException.lanzar("No pude instanciar la clase de prueba", e);
        }
        
        return probador;
    }

    public T build() {

        return instanciaInyectada;
    }
}
