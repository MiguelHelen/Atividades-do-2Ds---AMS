<?php
// Importa o arquivo que contém a classe Cadastro, que cuida de salvar os dados no banco
require_once("../model/cadastro.php");

// Esta é a classe que controla o processo de cadastro
class cadastroController{

    // Cria uma variável para guardar a classe de cadastro
    private $cadastro;

    // Quando a classe é criada, já inicia o processo de cadastro
    public function __construct(){
        // Cria um novo objeto da classe Cadastro
        $this->cadastro = new Cadastro();

        // Chama a função que vai cadastrar os dados
        $this->incluir();
    }

    // Função que pega os dados do formulário e envia para o banco
    private function incluir(){
        // Pega os dados do formulário (enviados por POST) e coloca no objeto
        $this->cadastro->setNome($_POST['txtNome']);
        $this->cadastro->setTelefone($_POST['txtTelefone']);
        $this->cadastro->setOrigem($_POST['txtOrigem']);

        // Converte a data do formato brasileiro para o formato do banco (ano-mês-dia)
        $this->cadastro->setData_contato(date('Y-m-d', strtotime($_POST['txtDataContato'])));

        // Salva a observação no objeto
        $this->cadastro->setObservacao($_POST['txtObservacao']);

        // Tenta salvar os dados no banco
        $result = $this->cadastro->incluir();

        // Se deu certo, mostra uma mensagem de sucesso e volta para a página inicial
        if($result >= 1){
            echo "<script>alert('Registro incluído com sucesso!');document.location='../index.php'</script>";
        } else {
            // Se deu erro, mostra uma mensagem de erro
            echo "<script>alert('Erro ao gravar registro!');</script>";
        }
    }
}

// Cria o objeto controlador, que já faz tudo automaticamente
new cadastroController();
