function limparForm(){
	var elementos = document.getElementById("formUser").elements;
	for(p = 0; p < elementos.length; p++){
		elementos[p].value = '';
	}
}