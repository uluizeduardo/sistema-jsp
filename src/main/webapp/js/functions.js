// Função para pesquisar o Cep através do webservice viacep
function pesquisaCep() {
	var cep = $("#cep").val();

	$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

		if (!("erro" in dados)) {
			//Atualiza os campos com os valores da consulta.
			$("#cep").val(dados.cep);
			$("#logradouro").val(dados.logradouro);
			$("#bairro").val(dados.bairro);
			$("#localidade").val(dados.localidade);
			$("#uf").val(dados.uf);
		} 
	});
}

// Function to clear screen data
function limparForm() {
	var elementos = document.getElementById("formUser").elements;
	for (p = 0; p < elementos.length; p++) {
		elementos[p].value = '';
	}
}

// Function to delete data in the database
function deletar() {

	if (confirm('Deseja realmente excluir os dados?')) {
		document.getElementById("formUser").method = 'get';
		document.getElementById("acao").value = 'deletar';
		document.getElementById("formUser").submit();
	}

}

// Delete function with ajax
function deleteComAjax() {

	if (confirm('Deseja realmente excluir os dados?')) {
		var urlAction = document.getElementById('formUser').action;
		var idUser = document.getElementById('id').value;

		$.ajax({
			method: "get",
			url: urlAction,
			data: "id=" + idUser + '&acao=deletarajax',
			success: function(response) {

				limparForm();
				document.getElementById('msg').textContent = response;
			}
		}).fail(function(xhr, status, errorThrown) {
			alert('Erro ao deletar usuário por id:' + xhr.responseText);
		});
	}
}

// Function to search for user in the bank
function buscarUsuario(){

	var nomeBusca = document.getElementById('nomeBusca').value;

	if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') { // Validating that it must have value to search the bank

		var urlAction = document.getElementById('formUser').action;

		$.ajax({
			method: "get",
			url: urlAction,
			data: "nomeBusca=" + nomeBusca + '&acao=buscarUserAjax',
			success: function(response, textStatus, xhr) {

				var json = JSON.parse(response);

				$('#tabelaResultados > tbody > tr').remove();
				$("#ulPaginacaoUserAjax > li").remove();
				
				for (var p = 0; p < json.length; p++) {
					$('#tabelaResultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+ '</td><td><button onclick="verEditar('+json[p].id+');" type="button" class="btn btn-info">Ver</button></td></tr>')
				}

				document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
				
				var totalPagina = xhr.getResponseHeader("totalPagina");
				
				 for (var p = 0; p < totalPagina; p++){
			      
			      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
			      $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>');
			  }

			}
		}).fail(function(xhr, status, errorThrown) {
			alert('Erro ao buscar usuário por nome:' + xhr.responseText);
		});

	}
}


function buscaUserPagAjax(url){
	
    var urlAction = document.getElementById('formUser').action;
    var nomeBusca = document.getElementById('nomeBusca').value;
    
	 $.ajax({	     
	     method: "get",
	     url : urlAction,
	     data : url,
	     success: function (response, textStatus, xhr) {
		 
		 var json = JSON.parse(response);
		 
		 $('#tabelaResultados > tbody > tr').remove();
		 $("#ulPaginacaoUserAjax > li").remove();
		 
		 for (var p = 0; p < json.length; p++) {
				$('#tabelaResultados > tbody').append('<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+ '</td><td><button onclick="verEditar('+json[p].id+');" type="button" class="btn btn-info">Ver</button></td></tr>')
			}
		  
		  document.getElementById('totalResultados').textContent = 'Resultados: ' + json.length;
		  
		  var totalPagina = xhr.getResponseHeader("totalPagina");
		  for (var p = 0; p < totalPagina; p++){
		      var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina='+ (p * 5);
			  $("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+url+'\')">'+ (p + 1) +'</a></li>'); 
		  }
	     }
	     
	 }).fail(function(xhr, status, errorThrown){
	    alert('Erro ao buscar usuário por nome: ' + xhr.responseText);
	 });
}


function verEditar(id) {
	var urlAction = document.getElementById('formUser').action;

	window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
}

function visualizarImg(fotoembase64, fileFoto) {

		var preview = document.getElementById(fotoembase64);
		var fileUser = document.getElementById(fileFoto).files[0];
		var reader = new FileReader();
	
		reader.onloadend = function() {
			preview.src = reader.result;
		}
	
		if (fileUser) {
		reader.readAsDataURL(fileUser); // Preview da imagem
		} else {
			preview.src = '';
		}
	}
	
// Função aônima para Validar campos númericos
(function(){
	
	$('#numero').keypress(function (event){
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
	$('#cep').keypress(function (event){
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
})()

// MaskMoney jquery
 $(document).ready(function() {
    $('#rendaMensal').maskMoney({prefix:'R$ ', thousands:'.', decimal:','});
    
    //Formatando os valores
    const formatter = new Intl.NumberFormat('pt-BR', {
		currency : 'BRL',
		minimumFractionDigits : 2
		})
	
	//Passa para a tela o valor formatado
	$("#rendaMensal").val(formatter.format($("#rendaMensal").val()));
	
	//Manda o foco para o campo rendaMensal
	$("#rendaMensal").focus();

  })
  
