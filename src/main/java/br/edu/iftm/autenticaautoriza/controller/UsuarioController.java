package br.edu.iftm.autenticaautoriza.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.autenticaautoriza.model.Usuario;
import br.edu.iftm.autenticaautoriza.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "usuarios")
    public String mostraTodosUsuarios(Model model) {
        model.addAttribute("lista", usuarioRepository.buscaTodosUsuarios());
        return "usuarios.html";
    }

    // método responsável por exibir formulário com objeto do tipo Usuario vazio
    @GetMapping(value = "novo-usuario")
    public String exibeFormNovoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "form-usuario";
    }

    // método responsável por receber um objeto do tipo Usuario preenchido pelo
    // usuário em um form html
    @PostMapping(value = "novo-usuario")
    public String cadastraNovoUsuario(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "form-usuario";
        }
        if (usuario.getId() == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(encodedPassword);
            usuarioRepository.gravaUsuario(usuario);
        } else
            usuarioRepository.atualizaUsuario(usuario);
        return "redirect:/usuarios";
    }

    // recebe id de um usuario e exibe em um form html
    @GetMapping(value = "editar-usuario")
    public String exibeFormUsuarioExistente(@RequestParam(value = "id", required = true) Integer id_usuario,
            Model model) {
        model.addAttribute("usuario", usuarioRepository.buscaPorId(id_usuario));
        return "form-usuario";
    }

    @GetMapping(value = "excluir-usuario")
    public String excluiUsuario(@RequestParam(value = "id", required = true) Integer id_usuario) {
        usuarioRepository.excluirUsuario(id_usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
