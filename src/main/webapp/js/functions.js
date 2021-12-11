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