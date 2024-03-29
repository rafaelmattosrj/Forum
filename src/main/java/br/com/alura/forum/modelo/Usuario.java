package br.com.alura.forum.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Usuario ->TER @Entity. GERAR GETS E SETS, GERAR CONSTRUTOR DEFAULT. PODE CRIAR OUTROS CONSTRUTORES. 
//Long ID, String NOME, String EMAIL, String SENHA.

@Entity
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

//@Entity
//ENTIDADE TEM Q TER A ANOTAÇÃO.
//public class Usuario implements UserDetails{
//UserDetails - classe q tem detalhes do usuario - segurança
//adicionar inimplemented methods pelo Usuario - segurança
//@Id @GeneratedValue
//(strategy = GenerationType.IDENTITY)
//BANCO DE DADOS VAI GERAR.	
//@ManyToMany
//USUIARIO PODE TER VARIOS PERFIS, E VARIOS PERFIS PODEM TA ATRELADO A UM USUARIO.
//(fetch = FetchType.EAGER)
//CARREGA O USUARIO, CARREGAR OS PERFIS DELE.
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.email;
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
