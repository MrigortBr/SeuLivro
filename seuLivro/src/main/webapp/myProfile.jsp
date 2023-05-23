<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="object.Person"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <link rel="stylesheet" href="styles/profileEditor.css">
</head>
<body>

    <%@ include file="partials/header.jsp" %>

	<%
		Person person = (Person) request.getAttribute("person");
		byte[] imagemBytes = person.getImage();
		String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);	
		
	%>

    
    <div class="background-profile">
    	<%if (imagemBase64.equals("")){ %>
         <img src="images/profile.svg" alt="" class="profile-picture">
 
    	<%}else{ %>
        <img src="data:image/png;base64,<%= imagemBase64 %>" alt="" class="profile-picture">
        <%}; %>
        <div class="name-span">
            <input type="text" class="profile-name" placeholder="Nome" value=<%=person.getName() %>>
            <input type="text" class="profile-surname" placeholder="Sobrenome" value=<%=person.getSurname() %>>
        </div>

        <input type="text" class="profile-tag" placeholder="@" value=@<%=person.getTag() %>>
        <input type="date" class="profile-age" value= <%= person.getBirth()%>>
        <input type="text" class="profile-email" placeholder="Email" value=<%=person.getEmail() %>> 
        <div class="password-span">
            <input type="password" class="profile-newpassword" placeholder="Nova Senha">
            <input type="password" class="profile-newpasswordConfirm" placeholder="Confirme a Nova Senha">
        </div>



        <textarea name="" id="" cols="30" rows="10" class="profile-bio" placeholder="Bio"><%=person.getBio() %></textarea>
        <input type="password" class="profile-password" placeholder="Senha para confirmar alterações">

        <button class="button-change-info" onclick="updateProfileinfo()">Atualizar Perfil</button>
        <form action="" style="visibility: hidden;" enctype="multipart/form-data">
            <input type="file" class="profile-image">
        </form>

        <hr>
        <div class="books">
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/profileCreator.js"></script>
</body>
</html>