package modelo;

import java.io.Serializable;
import java.util.Date;

public class BookItem implements Serializable {
    Integer Identificador;
    String Título;
    String Autor;
    Date Fecha;
    String Descripcion;
    String portadaURL;

    public BookItem (Integer Identificador,
                     String Título,
                     String Autor,
                     Date Fecha,
                     String Descripcion,
                     String portadaURL)
    {
        this.Identificador = Identificador;
        this.Título = Título;
        this.Autor = Autor;
        this.Fecha = Fecha;
        this.Descripcion = Descripcion;
        this.portadaURL = portadaURL;
    }


    public Integer getIdentificador(){ return this.Identificador;}
    public String getTítulo(){ return this.Título;}
    public String getAutor(){ return this.Autor;}
    public Date getFecha(){ return this.Fecha;}
    public String getDescripcion(){ return this.Descripcion;}
    public String getportadaURL(){ return this.portadaURL;}



}
