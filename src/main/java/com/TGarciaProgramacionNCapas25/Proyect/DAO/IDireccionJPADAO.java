
package com.TGarciaProgramacionNCapas25.Proyect.DAO;

import com.TGarciaProgramacionNCapas25.Proyect.ML.Result;



public interface IDireccionJPADAO {
    
    Result UpDate(com.TGarciaProgramacionNCapas25.Proyect.ML.Usuario usuarioML);
    Result Delete(int IdDireccion);
    Result GetById(int IdDireccion);
}
