/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectonewbanco;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jtcas
 */
public class ConexionDB 
{
    private ObjectContainer db = null;
    
    public void abrirConexion()
    {
        db = Db4oEmbedded.openFile("RegistrarUsuairo");
    }
    
    public void cerrarConexion()
    {
        db.close();
    }
    
    public void insertarRegistro(Usuario u)
    {
        abrirConexion();
        db.store(u);
        cerrarConexion();
    }
    public List<Usuario> mostrarRegistros()
    {
        abrirConexion();
        ObjectSet listaRegistro = db.queryByExample(Usuario.class);
        List<Usuario> lista = new ArrayList<>();
        for(Object listaRegistros:listaRegistro)
        {
            lista.add((Usuario)listaRegistros);
        }
        cerrarConexion();
        return lista;
    }
    public Usuario selecionarRegistro(Usuario user)
    {
        abrirConexion();
        ObjectSet result = db.queryByExample(user);
        Usuario User = (Usuario) result.next();
        cerrarConexion();
        return User;
    }
    public void actualizarFondo(int id, int cantidad)
    {
        abrirConexion();
        Usuario user = new Usuario();
        user.setId(id);
        ObjectSet result = db.queryByExample(user);
        Usuario preResult = (Usuario) result.next();
        preResult.setFondos(preResult.getFondos()+cantidad);
        db.store(preResult);
        cerrarConexion();
    }
}
