<%@page import="service.PersonService"%>
<%@page import="object.Book"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina de navegação</title>
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>
	
    <%@ include file="partials/header.jsp" %>

    <div class="search">
        <input class="search-input"type="text" required="required">
        <h1    class="search-text">Proucure seu livro</h1>
        <div class="button-search" onclick="search()"><svg width="100" height="100" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path d="M11 3a8 8 0 1 0 0 16 8 8 0 1 0 0-16z"></path>
            <path d="m21 21-4.35-4.35"></path>
            </svg></div>
    </div>
   
    <div class="books">	
    
    <%
		PersonService service = new PersonService();
		if (request.getSession().getAttribute("id") != null){
			ArrayList<Book> books = service.getBooksUser((int) request.getSession().getAttribute("id"));
			if (books.isEmpty() == false){
				for (Book book: books){
	    			byte[] imagemBytes = book.getCape();
	    			String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);
    %>
    
        <div class="background-book">
            <span class="book">
                <img src="data:image/png;base64,<%= imagemBase64 %>" onclick="window.location.href='/seuLivro/Book?id=<%=book.getId()%>'">
            </span>
    	</div>
   	</div>
    	
       <footer>
        <div class="logo">
            <svg class="logo-svg" width="50" height="50" fill="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M21.75 2.25c-3.17.014-5.525.45-7.23 1.204-1.272.563-1.77.988-1.77 2.43V21c1.949-1.758 3.678-2.25 10.5-2.25V2.25h-1.5Z"></path>
                <path d="M2.25 2.25c3.17.014 5.525.45 7.23 1.204 1.272.563 1.77.988 1.77 2.43V21c-1.949-1.758-3.678-2.25-10.5-2.25V2.25h1.5Z"></path>
            </svg>
            <h1  class="logo-text">SeuLivro</h1>
        </div>
    </footer>
    <%}}else{%>
	    <h1 class="alert">Você não esta lendo nem um livro no momento</h1>
	    </div>
    <%}}else{ %>
        <h1 class="alert" >Crie sua conta agora e tenha acesso a sua biblioteca propria</h1>
        </div>
    <%}; %>

    

 





</body>
</html>