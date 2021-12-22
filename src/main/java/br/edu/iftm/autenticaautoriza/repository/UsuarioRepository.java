package br.edu.iftm.autenticaautoriza.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.edu.iftm.autenticaautoriza.model.Papel;
import br.edu.iftm.autenticaautoriza.model.Usuario;

@Repository
public class UsuarioRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<Usuario> buscaTodosUsuarios() {
        String consulta = "SELECT * FROM usuarios;";
        return jdbc.query(consulta,
                (res, linha) -> new Usuario(
                        res.getInt("usuario_id"),
                        res.getString("email"),
                        res.getString("senha")));
    }

    public int gravaUsuario(Usuario usuario) {
        String consulta = "insert into usuarios(email,senha) values(?,?)";
        return jdbc.update(consulta, usuario.getEmail(), usuario.getSenha());
    }

    public int excluirUsuario(Integer id) {
        System.out.println(" -------------> id = " + id);
        String consulta = "delete from usuarios where usuario_id = ?";
        return jdbc.update(consulta, id);
    }

    public Usuario buscaPorId(Integer id) {
        return jdbc.queryForObject(
                "select * from usuarios where usuario_id = ?",
                (res, linha) -> new Usuario(
                        res.getInt("usuario_id"),
                        res.getString("email"),
                        res.getString("senha")),
                id);
    }

    public int atualizaUsuario(Usuario usuario) {
        String consulta = "update usuarios set email = ?, senha = ? where usuario_id = ?";
        return jdbc.update(consulta, usuario.getEmail(), usuario.getSenha(), usuario.getId());
    }

    public Usuario buscaPorEmail(String username) {
        return jdbc.queryForObject(
                "select * from usuarios where email = ?",
                (resultados, linha) -> {
                    return new Usuario(
                            resultados.getInt("usuario_id"),
                            resultados.getString("email"),
                            resultados.getString("senha"));
                },
                username);
    }

    public List<Papel> buscaPapeisDoUsuario(Integer id) {
        String consulta = "select papeis.nome from papeis, usuarios, usuarios_papeis where usuarios_papeis.papel_id = papeis.papel_id and usuarios_papeis.usuario_id = usuarios.usuario_id and usuarios.usuario_id = 2;";
        return jdbc.query(consulta, (res, linha) -> new Papel(res.getString("nome")));
    }
}
