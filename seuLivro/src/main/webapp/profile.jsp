<%@page import="service.Essentials"%>
<%@page import="object.Book"%>
<%@page import="java.util.ArrayList"%>
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
    <link rel="stylesheet" href="styles/profile.css">
</head>
<body>
    
	<%@ include file="partials/header.jsp" %>
	<%
		Essentials essentials = new Essentials();
		Person person = (Person) request.getAttribute("person");
		ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
		byte[] imagemBytes = person.getImage();
		String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);	
	%>
    <div class="background-profile">
    	<%if (imagemBase64.equals("")){ %>
    		<svg class="profile-picture" width="70" height="70" fill="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
	            <path d="M24 11.988C24 5.37 18.624 0 12 0S0 5.37 0 11.988c0 3.645 1.656 6.93 4.248 9.135.024.024.048.024.048.048.216.168.432.336.672.504.12.072.216.166.336.262A11.97 11.97 0 0 0 12.024 24a11.97 11.97 0 0 0 6.72-2.063c.12-.072.216-.166.336-.24.216-.166.456-.334.672-.502.024-.024.048-.024.048-.048 2.544-2.23 4.2-5.514 4.2-9.159ZM12 22.49c-2.256 0-4.32-.72-6.024-1.919.024-.192.072-.383.12-.575.143-.52.353-1.02.624-1.486.264-.456.576-.864.96-1.224a6.74 6.74 0 0 1 1.224-.959 6.057 6.057 0 0 1 1.464-.6A6.224 6.224 0 0 1 12 15.513a6.278 6.278 0 0 1 4.392 1.749 6.01 6.01 0 0 1 1.296 1.942c.168.433.288.889.36 1.367A10.545 10.545 0 0 1 12 22.489Zm-3.672-11.1a3.735 3.735 0 0 1-.312-1.537c0-.526.096-1.054.312-1.534s.504-.91.864-1.27.792-.647 1.272-.863A3.743 3.743 0 0 1 12 5.874c.552 0 1.056.096 1.536.312s.912.504 1.272.862c.36.36.648.792.864 1.271a3.74 3.74 0 0 1 .312 1.534c0 .553-.096 1.056-.312 1.535a4.26 4.26 0 0 1-.864 1.272 4.26 4.26 0 0 1-1.272.863 4.072 4.072 0 0 1-3.096 0 4.26 4.26 0 0 1-1.272-.863 3.79 3.79 0 0 1-.84-1.272v.002Zm11.136 7.959c0-.049-.024-.073-.024-.12a7.83 7.83 0 0 0-1.032-2.11 7.325 7.325 0 0 0-1.632-1.702 7.813 7.813 0 0 0-1.56-.912c.252-.166.485-.359.696-.575a6.31 6.31 0 0 0 .936-1.175 5.437 5.437 0 0 0 .792-2.902 5.566 5.566 0 0 0-.432-2.204 5.698 5.698 0 0 0-1.224-1.799 5.767 5.767 0 0 0-1.8-1.2 5.58 5.58 0 0 0-2.208-.43 5.58 5.58 0 0 0-2.208.432 5.446 5.446 0 0 0-1.8 1.222 5.76 5.76 0 0 0-1.2 1.799 5.565 5.565 0 0 0-.432 2.204c0 .528.072 1.032.216 1.511.144.504.336.96.6 1.39.24.432.576.816.936 1.176.216.216.456.407.72.575a7.68 7.68 0 0 0-1.56.936 8.314 8.314 0 0 0-1.632 1.678 7.498 7.498 0 0 0-1.032 2.11c-.024.047-.024.096-.024.12-1.896-1.919-3.072-4.508-3.072-7.385C1.488 6.21 6.216 1.486 12 1.486c5.784 0 10.512 4.724 10.512 10.502a10.44 10.44 0 0 1-3.048 7.36Z"></path>
	         </svg>    
    	<%}else{ %>
        <img src="data:image/png;base64,<%= imagemBase64 %>" alt="" class="profile-picture">
        <%}; %>
        <h1 class="profile-name"><%=person.getName() +" "+ person.getSurname() %></h1>
        <h2 class="profile-tag">@<%=person.getTag() %></h2> 
        <h3 class="profile-age"><%=essentials.calculateAge(person.getBirth()) %> Anos</h3> 
        <h4 class="profile-bio"><%=person.getBio() %></h4>

        <hr>
        

        <div class="books">
            <%if (books.isEmpty() == false){%>
            <h1 class="books-label">Livros publicados</h1>
            <div class="list-books" >
            <%for (Book book: books){
        		byte[] imagemBytesBook = book.getCape();
        		String imagemBase64Book = Base64.getEncoder().encodeToString(imagemBytesBook);
            
            %>
                <div class="background-book" onclick="window.location.href='/seuLivro/Book?id=<%=book.getId()%>'">
                    <span class="book">
                        <img src="data:image/png;base64,<%= imagemBase64Book %>" alt="">
                    </span>
                </div>
                

            <%}%>
            </div>
            <%}else{ %>
            <h1 class="books-label">Sem Livros publicados</h1>
            <%}; %>
        </div>
        
    </div>
</body>
</html>