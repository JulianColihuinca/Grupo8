package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Ollers
 * Clase PersistenciaXML para persistir el sistema con su lista de abonados
 */
public class PersistenciaXML 
{
    private XMLEncoder xmlEncoder;
    private XMLDecoder xmlDecoder;
    private FileOutputStream fileoutput;
    private FileInputStream fileinput;


    public void abrirInput(String nombre) throws IOException
    {
        fileinput = new FileInputStream(nombre);
        xmlDecoder = new XMLDecoder(fileinput);
    }

    public void abrirOutput(String nombre) throws IOException
    {
        fileoutput = new FileOutputStream(nombre);
        xmlEncoder = new XMLEncoder(fileoutput);
    }

    public void cerrarOutput() throws IOException
    {
        this.xmlEncoder.close();
    }

    public void cerrarInput() throws IOException
    {
        this.xmlDecoder.close();
    }

    public void escribir(Object objeto) throws IOException
    {
        xmlEncoder.writeObject(objeto);

    }

    public Object leer() throws IOException, ClassNotFoundException
    {
        Object objecto = null;
        if (xmlDecoder != null)
            objecto =  xmlDecoder.readObject();

        return objecto;
    }
}
