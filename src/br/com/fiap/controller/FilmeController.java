package br.com.fiap.controller;

import br.com.fiap.model.dao.FilmeDAO;
import br.com.fiap.model.dto.Filme;
import br.com.fiap.model.factory.ConnectionFactory;

import java.sql.Connection;
import java.util.ArrayList;

public class FilmeController {
    public String inserirFilme(String titulo, String genero, String produtora){
        Filme filme = new Filme();
        filme.setTitulo(titulo);
        filme.setGenero(genero);
        filme.setProdutora(produtora);
        Connection con = ConnectionFactory.abrirConexao();
        System.out.println(con);
        FilmeDAO filmeDAO = new FilmeDAO(con);
        String resultado = filmeDAO.inserir(filme);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
    public String alterarFilme(int codigo, String titulo, String genero, String produtora){
        Filme filme = new Filme();
        filme.setCodigo(codigo);
        filme.setTitulo(titulo);
        filme.setGenero(genero);
        filme.setProdutora(produtora);
        Connection con = ConnectionFactory.abrirConexao();
        FilmeDAO filmeDAO = new FilmeDAO(con);
        String resultado = filmeDAO.alterar(filme);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
    public String excluirFilme(int codigo){
        Filme filme = new Filme();
        filme.setCodigo(codigo);
        Connection con = ConnectionFactory.abrirConexao();
        FilmeDAO filmeDAO = new FilmeDAO(con);
        String resultado = filmeDAO.excluir(filme);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }

    public String listarTodos(){
        Connection con = ConnectionFactory.abrirConexao();
        FilmeDAO filmeDAO = new FilmeDAO(con);
        ArrayList<Filme> filmes = filmeDAO.listarTodos();
        String resultado = "";

        if(filmes != null){
            for (Filme filme : filmes){
                resultado += "Código: " + filme.getCodigo() +  "\nNome do filme: " + filme.getTitulo() + "\nGênero: " + filme.getGenero() + "\nProdutora" +
                        ":" +
                        " " + filme.getProdutora() + "\n\n";
            }
            return resultado;
        }
        return "Sem filmes para listar!";
    }
}
