<?php
require_once("banco.php");

// Define uma classe chamada Cadastro que herda a funcionalidade da classe Banco
class Cadastro extends Banco {

    // Declara variáveis privadas para armazenar os dados do cadastro
    private $nome;
    private $telefone;
    private $origem;
    private $data_contato;
    private $observacao;

    // Métodos SET: são funções que definem (guardam) os valores nas variáveis privadas
    public function setNome($string){
        $this->nome = $string;
    }
    public function setTelefone($string){
        $this->telefone = $string;
    }
    public function setOrigem($string){
        $this->origem = $string;
    }
    public function setData_contato($string){
        $this->data_contato = $string;
    }
    public function setObservacao($string){
        $this->observacao = $string;
    }

    // Métodos GET: são funções que retornam (mostram) os valores armazenados nas variáveis privadas
    public function getNome(){
        return $this->nome;
    }
    public function getTelefone(){
        return $this->telefone;
    }
    public function getOrigem(){
        return $this->origem;
    }
    public function getData_contato(){
        return $this->data_contato;
    }
    public function getObservacao(){
        return $this->observacao;
    }

    // Método que chama um método da classe pai para salvar os dados no banco de dados
    public function incluir(){
        // Passa os dados armazenados para o método setAgendamentos da classe Banco 
        return $this->setAgendamentos($this->getNome(), $this->getTelefone(), $this->getOrigem(), $this->getData_contato(), $this->getObservacao());
    }
}
?>
