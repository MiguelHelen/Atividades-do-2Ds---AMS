<?php

date_default_timezone_set('America/Sao_Paulo');

// Define constantes para os dados da conexão com o banco de dados (servidor, usuário, senha e nome do banco)
define('BD_SERVIDOR','localhost');
define('BD_USUARIO','root');
define('BD_SENHA','');
define('BD_BANCO','projetoweb');
    
// Cria a classe Banco, responsável pela conexão e operações no banco de dados
class Banco{

    // Variável protegida para guardar o objeto de conexão com o banco (mysqli)
    protected $mysqli;

    // Método construtor que é chamado automaticamente quando a classe é usada
    public function __construct(){
        // Chama o método para fazer a conexão com o banco
        $this->conexao();
    }

    // Método privado que cria a conexão com o banco usando as constantes definidas antes
    private function conexao(){
        $this->mysqli = new mysqli(BD_SERVIDOR, BD_USUARIO , BD_SENHA, BD_BANCO);
    }

    // Método para inserir um novo registro na tabela 'agendamentos' no banco de dados
    public function setAgendamentos($nome, $telefone, $origem, $data_contato, $observacao){
        // Prepara uma consulta SQL para inserir os dados de forma segura
        $stmt = $this->mysqli->prepare("INSERT INTO agendamentos (`nome`, `telefone`, `origem`, `data_contato`, `observacao`) VALUES (?,?,?,?,?)");
        
        // Liga os valores passados para os "?" da consulta, todos como strings (sssss)
        $stmt->bind_param("sssss", $nome, $telefone, $origem, $data_contato, $observacao);
        
        // Executa a consulta e retorna true se deu certo, ou false se falhou
        if($stmt->execute() == TRUE){
            return true;
        }else{
            return false;
        }
    }
}    
?>
