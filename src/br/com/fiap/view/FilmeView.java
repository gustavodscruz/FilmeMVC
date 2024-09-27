package br.com.fiap.view;

import br.com.fiap.controller.FilmeController;

import javax.swing.*;

public class FilmeView {
    public static void main(String[] args) {
        do{
            int id;
            String titulo, genero, produtora;
            int opcao;
            String [] escolha = {"Inserir","Alterar", "Excluir", "Listar"};
            FilmeController filmeController = new FilmeController();
            try{
                opcao = JOptionPane.showOptionDialog(null, "Escolha uma das opções abaixo sobre filmes",
                        "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[0]);
                switch (opcao){
                    case 0:
                        titulo = JOptionPane.showInputDialog("Qual o nome do filme?");
                        genero = JOptionPane.showInputDialog("Qual o gênero do filme?");
                        produtora = JOptionPane.showInputDialog("Qual a produtora do filme?");
                        JOptionPane.showMessageDialog(null, filmeController.inserirFilme(titulo, genero, produtora));
                        break;
                    case 1:
                        id = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do filme!"));
                        titulo = JOptionPane.showInputDialog("Nome do filme novo: ");
                        genero = JOptionPane.showInputDialog("Novo genero do filme: ");
                        produtora = JOptionPane.showInputDialog("Nova produtora do filme: ");
                        JOptionPane.showMessageDialog(null, filmeController.alterarFilme(id, titulo, genero, produtora));
                        break;
                    case 2:
                        id = Integer.parseInt(JOptionPane.showInputDialog("Insira o id do filme!"));
                        JOptionPane.showMessageDialog(null, filmeController.excluirFilme(id));
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, filmeController.listarTodos());
                        break;

                }

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro : " + e.getMessage());
            }
        }while (JOptionPane.showConfirmDialog(null, "Deseja continuar? ", "Atenção", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == 0);
    }
}
