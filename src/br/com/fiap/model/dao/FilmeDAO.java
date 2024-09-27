package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeDAO {
    Connection con;

    public FilmeDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Filme filme){
        String sql = "insert into ddd_filme(TITULO, GENERO, PRODUTURA) values(?, ?, ?)";
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getGenero());
            ps.setString(3, filme.getProdutora());
            if (ps.executeUpdate() > 0){
                return "Inserido com sucesso!";
            }
            return "Falha: não inserido!";
        }catch (SQLException e){
            return "Erro de sql!\n" + e.getMessage();
        }
    }
    public String alterar(Filme filme){
        String sql = "update ddd_filme set TITULO = ?, GENERO = ?, PRODUTURA = ? where codigo = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setInt(4, filme.getCodigo());
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getGenero());
            ps.setString(3, filme.getProdutora());
            if (ps.executeUpdate() > 0){
                return "Atualizado com sucesso!";
            }
            return "Falha: não atualizado com sucesso!";
        }catch (SQLException e){
            return "Erro de sql!\n" + e.getMessage();
        }

    }
    public String excluir(Filme filme){
        String sql = "delete ddd_filme where codigo = ?";
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setInt(1, filme.getCodigo());
            if(ps.executeUpdate() > 0){
                return "Deletado com sucesso!";
            }
            return "Não deletado! O filme existe mesmo?";
        } catch (SQLException e){
            return "Erro de sql!\n" + e.getMessage();
        }
    }
    public ArrayList<Filme> listarTodos(){
        String sql = "select * from ddd_filme";
        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        try(PreparedStatement ps = getCon().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if(rs != null){
                while (rs.next()){
                    Filme filme = new Filme();
                    filme.setCodigo(rs.getInt(1));
                    filme.setTitulo(rs.getString(2));
                    filme.setGenero(rs.getString(3));
                    filme.setProdutora(rs.getString(4));
                    listaDeFilmes.add(filme);
                }
                return listaDeFilmes;
            }
            System.out.println("Sem filmes para listar!");
            return null;
        }catch (SQLException e){
            System.out.println("Erro de sql!\n" + e.getMessage());
            return null;
        }
    }
}
