function updateProfileinfo() {
    var profilePicture = ""
    try {
        profilePicture = document.querySelector('.profile-image').files[0];
    } catch (error) {
        
    }
    var profileName = document.querySelector('.profile-name').value;
    var profileSurname = document.querySelector('.profile-surname').value;
    var profileTag = document.querySelector('.profile-tag').value.replace("@", "");
    var profileAge = document.querySelector('.profile-age').value;
    var profileEmail = document.querySelector('.profile-email').value;
    var profileNewPassword = document.querySelector('.profile-newpassword').value;
    var profileNewPasswordConfirm = document.querySelector('.profile-newpasswordConfirm').value;
    var profileBio = document.querySelector('.profile-bio').value;
    var profilePassword = document.querySelector('.profile-password').value;
    
    // Aqui você pode realizar a validação e as ações desejadas com os valores obtidos
    
    // Exemplo de validação: verificar se o nome está vazio
    if (profileName.trim() === '') {
        alert('Por favor, preencha o campo Nome.');
        return;
      }
    
      if (profileSurname.trim() === '') {
        alert('Por favor, preencha o campo Sobrenome.');
        return;
      }
    
      if (profileTag.trim() === '') {
        alert('Por favor, preencha o campo Tag.');
        return;
      }
    
      if (profileAge.trim() === '') {
        alert('Por favor, selecione uma data de nascimento.');
        return;
      }
    
      if (profileEmail.trim() === '') {
        alert('Por favor, preencha o campo Email.');
        return;
      }
    
      if (profileBio.trim() === '') {
        alert('Por favor, preencha o campo Bio.');
        return;
      }
    
      if (profilePassword.trim() === '') {
        alert('Por favor, preencha o campo Senha para confirmar alterações.');
        return;
      }

    var formData = new FormData();


  if (profileNewPassword.trim() !== '') {
    if (profileNewPassword === profileNewPasswordConfirm){
        formData.append('newPassword', newPassword);
    }else{
        alert("A nova senha deve ser igual a senha de confirmação")
        return;
    }
  }

	console.log(profilePicture != undefined)
  if (profilePicture != undefined){
    formData.append("picture", profilePicture)
  }

  // Criar um objeto FormData
  formData.append('name', profileName);
  formData.append('surname', profileSurname);
  formData.append('tag', profileTag);
  formData.append('age', profileAge);
  formData.append('email', profileEmail);
  formData.append('bio', profileBio);
  formData.append('password', profilePassword);

  $.ajax({
    url: 'http://localhost:8080/seuLivro/profile',
    method: 'POST',
    data: formData,
    processData: false,
    contentType: false,
    success: function(response) {
      // Executar ações em caso de sucesso
      console.log('Requisição bem-sucedida:', response);
        // Zerar o valor dos campos de senha
        $('.profile-password').val('');
        $('.profile-newpassword').val('');
        $('.profile-newpasswordConfirm').val('');
    },
    error: function(xhr, status, error) {
      // Executar ações em caso de erro
      console.log('Erro na requisição:', error);
    }
  });
}

document.getElementsByClassName("profile-picture")[0].onclick = function(){
    const input = document.getElementsByClassName("profile-image")[0]


    input.onchange = function (event){
        var file = event.target.files[0];
        var reader = new FileReader();
        reader.onload = function(event) {
          document.getElementsByClassName("profile-picture")[0].src = event.target.result;
        };
        reader.readAsDataURL(file);
    }

    input.click()


  

}