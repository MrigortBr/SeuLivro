document.getElementsByClassName("background-login-button")[0].onclick = function (){
	const email = document.getElementById("login-email").value
    const password = document.getElementById("login-password").value
    doLogin(email, password);
}


function doLogin(email, password){
    if (email.trim() != "" && password.trim() != ""){
        $.ajax({
            url: 'http://localhost:8080/seuLivro/Login',
            method: "POST",
            data: {email: email, password: password},
        success: function (data){
           	if ((JSON.parse(data)).result == "L210"){
				   window.location.href = "/seuLivro/Index"
			  }else{
				  alert("Conta invalida")
			  }
        },
        error: function (error){
            console.log(error);
        }
        })
    }
};