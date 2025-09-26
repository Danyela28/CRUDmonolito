
package com.TGarciaProgramacionNCapas25.Proyect.Service;

import com.TGarciaProgramacionNCapas25.Proyect.DAO.IUsuarioRepository;
import com.TGarciaProgramacionNCapas25.Proyect.JPA.Usuario;
import com.TGarciaProgramacionNCapas25.Proyect.ML.Result;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    private final IUsuarioRepository iUsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UsuarioService(IUsuarioRepository iUsuarioRepository, PasswordEncoder passwordEncoder){
        this.iUsuarioRepository = iUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Result Add(Usuario usuario){
        
        Result result = new Result();
        
        try{
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            
//            iUsuarioRepository.saveAll(usuario);
            
        }catch(Exception ex){
            result.correct=false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex=ex;
        }
        return result;
    }
    
    
    
}
