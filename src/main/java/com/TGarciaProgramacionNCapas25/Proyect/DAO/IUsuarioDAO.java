
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.ML.Result;
import com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario;




public interface IUsuarioDAO {
    
    Result UsuarioDireccionGetAll(Usuario usuario);
    Result DireccionGetByIdUsuario(int idUsuario);
    Result UsuarioDireccionAdd(Usuario usuario);
    Result UsuarioUpDate(Usuario usuario);
    Result UsuarioAddDireccion(Usuario usuario);
    Result UsuarioGetById(int idUsuario);
}
