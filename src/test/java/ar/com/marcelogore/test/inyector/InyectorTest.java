package ar.com.marcelogore.test.inyector;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InyectorTest {

    @Rule public ExpectedException excepcionInyectableNulo = ExpectedException.none();
    
    @Test
    public void deberiaInyectarUnAtributoInyectableSiHayUnicamenteUnAtributoDeEseTipo() throws Exception {
        
        Inyector<Inyectable> inyector = Inyector.instanciar(Inyectable.class);
        
        Inyectable inyectado = inyector.inyectar(123456).build();
        
        assertThat(inyectado.getAtributoDeTipoUnico(), is(123456));
    }

    @Test
    public void deberiaInyectarVariosAtributosInyectableSiHayUnicamenteUnAtributoDeCadaTipo() throws Exception {
        
        Inyector<Inyectable> inyector = Inyector.instanciar(Inyectable.class);
        
        Inyectable inyectado = inyector.inyectar(123456).inyectar(true).inyectar(new Exception()).build();
        
        assertThat(inyectado.getAtributoDeTipoUnico(), is(123456));
    }

    @Test
    public void deberiaInyectarAtributoInyectableEnSuperclaseSiHayUnicamenteUnAtributoEseTipo() throws Exception {
        
        Inyector<Inyectable> inyector = Inyector.instanciar(Inyectable.class);
        
        Inyectable inyectado = inyector.inyectar(123456L).inyectar(true).inyectar(new Exception()).build();
        
        assertThat(inyectado.getAtributoDeTipoUnicoEnSuperclase(), is(123456L));
    }

    @Test(expected = InyectorException.class)
    public void deberiaDarErrorAlInyectarUnAtributoSiHayMasDeUnCampoDelMismoTipo() throws Exception {
        
        Inyector<Inyectable> inyector = Inyector.instanciar(Inyectable.class);
        
        inyector.inyectar("Un string").build();
    }

    @Test(expected = InyectorException.class)
    public void deberiaDarErrorAlInyectarUnAtributoSiNoHayCampoDelTipo() throws Exception {
        
        Inyector<Inyectable> inyector = Inyector.instanciar(Inyectable.class);
        
        inyector.inyectar(123456.0).build();
    }
    
    @Test
    public void deberiaDarErrorClaroAlInyectarUnAtributoNulo() {

        excepcionInyectableNulo.expectMessage(containsString("atributo nulo"));
        
        Inyector<Inyectable> inyector = Inyector.instanciar(Inyectable.class);
        inyector.inyectar(null).build();
    }
}
