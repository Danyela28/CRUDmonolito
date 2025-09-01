
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.ML.Result;

public interface IUsuarioJPADAO {
    
    Result GetAll();
    Result Add(com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuario);
    Result Delete(int IdUsuario);
    Result UpDate(com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuarioML);
    Result GetById(int IdUsuario);
}
