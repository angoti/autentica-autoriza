package br.edu.iftm.autenticaautoriza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.iftm.autenticaautoriza.model.Usuario;
import br.edu.iftm.autenticaautoriza.repository.UsuarioRepository;


 
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UsuarioRepository usuarioRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("----------------------> entrou em loadUserByUsername: "+username);
        Usuario usuario = usuarioRepository.buscaPorEmail(username);
        if (usuario == null) {
            System.out.println("----------------------> ERRO");
            //throw new UsernameNotFoundException("User not found");
        }
        System.out.println("---------------------->"+usuario.getEmail());
        return new MyUserDetails(usuario);
    }
 
}

