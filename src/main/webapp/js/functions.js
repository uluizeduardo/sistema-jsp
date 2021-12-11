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

// Função Delete com ajax
function deleteComAjax(){
	
	if(confirm('Deseja realmente excluir os dados?')){
		var urlAction = document.getElementById('formUser').action;
		var idUser = document.getElementById('id').value;
		
		$.ajax({
			method: "get",
			url: urlAction,
			data: "id=" + idUser + '&acao=deletarajax',
			success: function(response){
				
				limparForm();
				document.getElementById('msg').textContent = response;
			}
		}).fail(function(xhr, status, errorThrown){
			alert('Erro ao deletar usuário por id:' + xhr.responseText);
		});
	}
}