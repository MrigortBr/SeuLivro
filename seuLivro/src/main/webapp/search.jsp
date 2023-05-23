<%@page import="service.BookService"%>
<%@page import="service.PersonService"%>
<%@page import="object.Book"%>
<%@page import="object.Person"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="styles/search.css">
</head>
<body>
    <%@ include file="partials/header.jsp" %>
	<%
		BookService serviceBook = new BookService();
		PersonService servicePerson = new PersonService();
		ArrayList<Book> books = serviceBook.searchBook((String) request.getParameter("search"));
		ArrayList<Person> persons = servicePerson.searchPerson((String) request.getParameter("search"));
	%>

    <div class="search">
        <input class="search-input"type="text" required="required" value="<%=request.getParameter("search") %>" >
        <h1    class="search-text">Proucure seu livro</h1>
        <div class="button-search" onclick="search()"><svg width="100" height="100" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path d="M11 3a8 8 0 1 0 0 16 8 8 0 1 0 0-16z"></path>
            <path d="m21 21-4.35-4.35"></path>
            </svg></div>
    </div>
    
    <%if (persons.size() > 0){ %>
    <h1 class="label-finded" >Usuarios Encontrados</h1>
    <div class="users">
        <div class="user-background">
        <%for(Person person: persons){ 
			byte[] imagemBytes = person.getImage();
			String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);
        
        %>
            <span class="user-users" onclick="window.location.href='/seuLivro/profile?tag=<%=person.getTag()%>'">
        	<%if(imagemBase64.equals("")){ %>
        	    <img src="images/profile.svg" alt="" class="user-img-search">
            <%}else{ %>
            	<img src="data:image/png;base64,<%= imagemBase64 %>" alt="" class="user-img-search">
            <%}; %>
                <p class="user-name-search"><%=person.getName() +" "+ person.getSurname() %></p>
                <p class="user-tag-search">@<%=person.getTag() %></p>
            </span>
            <%}; %>
        </div>
    </div>
    <%}; %>
	<%if (books.size() > 0){ %>
    <h1 class="label-finded">Livro Encontrados</h1>
    <div class="books">
    <%
		for (Book book: books){
			byte[] imagemBytes = book.getCape();
			String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);
    %>
        <div class="background-book">
            <span class="book">
                <img src="data:image/png;base64,<%= imagemBase64 %>" alt="" onclick="window.location.href='/seuLivro/Book?id=<%=book.getId()%>'">
            </span>
        </div>
       <%}; %>
    </div>
    <%}; %>
    
    <%if (books.size() == 0 && persons.size() == 0){ %>
    	<h1 class="alert">Nenhuma livro ou autor encontrado com os valores passados</h1>
    <%}else{ %>
         <footer>
        <div class="logo">
            <svg class="logo-svg" width="50" height="50" fill="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path d="M21.75 2.25c-3.17.014-5.525.45-7.23 1.204-1.272.563-1.77.988-1.77 2.43V21c1.949-1.758 3.678-2.25 10.5-2.25V2.25h-1.5Z"></path>
                <path d="M2.25 2.25c3.17.014 5.525.45 7.23 1.204 1.272.563 1.77.988 1.77 2.43V21c-1.949-1.758-3.678-2.25-10.5-2.25V2.25h1.5Z"></path>
            </svg>
            <h1  class="logo-text">SeuLivro</h1>
        </div>
    </footer>
    <%}; %>
    

</body>
</html>