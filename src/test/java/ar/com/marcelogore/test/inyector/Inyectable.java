package ar.com.marcelogore.test.inyector;

public class Inyectable extends InyectablePadre {

    private String atributo;
    private String atributoDeTipoDuplicado;
    private Integer atributoDeTipoUnico;
    private Boolean atributoDeOtroTipoUnico;
    private Exception atributoDeTercerTipoUnico;

    public String getAtributo() {

        return atributo;
    }

    public String getAtributoDeTipoDuplicado() {

        return atributoDeTipoDuplicado;
    }

    public Integer getAtributoDeTipoUnico() {

        return atributoDeTipoUnico;
    }

    public Boolean getAtributoDeOtroTipoUnico() {

        return atributoDeOtroTipoUnico;
    }

    public Exception getAtributoDeTercerTipoUnico() {

        return atributoDeTercerTipoUnico;
    }

}
