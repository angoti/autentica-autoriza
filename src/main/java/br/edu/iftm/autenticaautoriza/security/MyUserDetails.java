package br.edu.iftm.autenticaautoriza.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.iftm.autenticaautoriza.model.Papel;
import br.edu.iftm.autenticaautoriza.model.Usuario;
import br.edu.iftm.autenticaautoriza.repository.UsuarioRepository;

public class MyUserDetails implements UserDetails {

    private Usuario usuario;

    @Autowired
    UsuarioRepository usuarioRepository;

    public MyUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Papel> papeis = usuarioRepository.buscaPapeisDoUsuario(usuario.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Papel papel : papeis) {
            authorities.add(new SimpleGrantedAuthority(papel.getNome()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
