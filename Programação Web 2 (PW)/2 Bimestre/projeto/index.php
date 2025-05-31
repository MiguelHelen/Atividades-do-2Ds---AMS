<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<meta name="format-detection" content="telephone=no">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="viewport" content="initial-scale=1, width=device-width, viewport-fit=cover">
	<meta name="color-scheme" content="light dark"> 
	<link rel="stylesheet" href="bootstrap/css/bootstrap.css"> 
	<link rel="stylesheet" href="css/estilo.css">
	<script src="js/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
	<script src="js/funcoes.js"></script>
	<title>SISTEMA DE AGENDAMENTO - CLIENTES</title>
</head> 
<body> 


	<div class="container">
		<div class="row">
			<nav class="navbar navbar-expand-lg navbar-dark bg-primary col-12">
				<a class="navbar-brand" href="#">SISTEMA WEB</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav">
						<li class="nav-item active">
							<a class="nav-link" href="index.php">Cadastrar<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="consultarClientes.php">Consultar</a>
						</li>
					</ul>
				</div>
			</nav>
		</div>
		<div class="row">
			<div class="card mb-3 col-12">
				<div class="card-body">
					<h5 class="card-title">Cadastrar - Agendamento de Potenciais Clientes</h5>
					<p class="card-text">Sistema utilizado para agendamento de serviços.</p>
					<p>
						<form method="post" action="controller/ControllerCadastro.php" id="form" name="form">
							<div class="form-group">
								<label for="exampleFormControlInput1">nome:</label>
								<input type="text" class="form-control" name="txtNome" required id="txtNome">
							</div>
							<div class="form-group">
								<label for="exampleFormControlInput1">telefone:</label>
								<input type="tel" class="form-control" required name="txtTelefone" id="txtTelefone" placeholder="(xx)xxxxx-xxxx">
							</div>
							<div class="form-group">
								<label for="exampleFormControlSelect1">origem:</label>
								<select class="form-control" required name="txtOrigem" id="txtOrigem">
									<option>Celular</option>
									<option>Fixo</option>
									<option>Whatsapp</option>
									<option>Facebook</option>
									<option>Instagram</option>
									<option>Google Meu Negocio</option>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleFormControlInput1">data do Contato:</label>
								<input type="date" class="form-control" required name="txtDataContato" id="txtDataContato">
							</div>
							<div class="form-group">
								<label for="exampleFormControlTextarea1">observação</label>
								<textarea class="form-control" name="txtObservacao" id="txtObservacao" rows="3"></textarea>
							</div>
							<button type="submit" id="btnInserir" class="btn btn-primary">Cadastrar</button>
						</form>
					</p>
				</div>
			</div>
		</div>
	</div>


</body>
</html>