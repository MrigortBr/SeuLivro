document.getElementById("picture").onclick = function (){
    document.getElementsByClassName("uploadImage")[0].click()
};

document.getElementsByClassName("register")[0].onclick = function(){
    document.getElementsByClassName("submitFile")[0].click()
}

document.getElementsByClassName("uploadImage")[0].addEventListener('change', function(event) {
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function(event) {
      var image = document.getElementById("picture")
      image.src = event.target.result;
    };
    reader.readAsDataURL(file);
 });



$(document).ready(function() {
  $('#uploadForm').submit(function(event) {
    event.preventDefault();
    const name = document.getElementById("name").value
	const surname = document.getElementById("surname").value
	const email = document.getElementById("email").value
	const tag = document.getElementById("tag").value
	const password = document.getElementById("password").value
	const confirm = document.getElementById("confirm").value
	const birth = document.getElementById("birth").value
    if (name.trim() != "" && surname.trim() != "" && email.trim() != "" && tag.trim() != "" && password.trim() != "" && birth.trim() != "" ){
        if (password == confirm){
		    	var formData = new FormData($(this)[0]);
		        formData.append("name", name);
		        formData.append("surname", surname);
		        formData.append("email", email);		            
		        formData.append("tag", tag);
		        formData.append("password", password);
		        formData.append("birth", birth);
		            
		        $.ajax({
		            url: 'http://localhost:8080/seuLivro/Register',
		            type: 'PUT',
		            data: formData,
		            cache: false,
		            contentType: false,
		            processData: false,
		        success: function(data) {
		            doLogin(email, password);
		            },
		        error: function() {
		            alert('Erro ao enviar arquivo! Tente editar no perfil');
		            }
		            });	
			}
		}else{
			alert("Preencha todos os campos")
		}
	}
)});


