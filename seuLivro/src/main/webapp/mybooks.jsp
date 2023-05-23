<%@page import="java.util.Base64"%>
<%@page import="java.util.ArrayList"%>
<%@page import="object.Book"%>
<%@page import="service.BookService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="styles/book.css">
</head>
<body>

	<%@ include file="partials/header.jsp" %>

	<%BookService service = new BookService();
	  ArrayList<Book> books = service.getBooksByAuthorId((int) request.getSession().getAttribute("id"));
	%>
    <div class="background-books">
 	<h1 class="label-book">Livros criados</h1>
    <%for(Book book: books){ 
		byte[] imagemBytes = book.getCape();
		String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);
    
    %>
        <div class="book" data-id=<%=book.getId() %>>
            <img src="data:image/png;base64,<%= imagemBase64 %>" alt="" class="cape-img">
            <div class="book-inputs">
                <input type="text" name="name" class="book-input" id="name" placeholder="Nome" value="<%=book.getName()%>">
                <input type="text" name="author" class="book-input" id="author" placeholder="Autor" value="<%=book.getAuthor()%>">
                <button class="book-button">PDF</button>
                <div class="functions-books">

                    <div class="option cancel">
                        <svg width="100" height="100" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M18 6 6 18"></path>
                            <path d="m6 6 12 12"></path>
                        </svg>
                        <p>Cancelar</p>

                    </div>
                    <div class="option delete">
                        <svg width="100" height="100" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M3 6h18"></path>
                            <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                        </svg>
                        <p>Deletar</p>
                    </div>
                    <div class="option update">
                        <svg width="100" height="100" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M20 6 9 17l-5-5"></path>
                        </svg>
                        <p>Atualizar</p>
                    </div>

                </div>
            </div>
            <textarea name="description" id="description" cols="30" rows="10"><%=book.getDescription() %></textarea>
            <form class="form" method="post" enctype="multipart/form-data">
                <input type="file" name="cape" class="cape-book">
                <input type="file" name="pdf" class="pdf-book">
            </form>
        </div>
        <%}; %>
        
		<h1 class="label-book">Enviar um novo livro</h1>
        <div class="book">
            <img src="dom.png" alt="" class="cape-img">
            <div class="book-inputs">
                <input type="text" name="name" class="book-input" id="name" placeholder="Nome">
                <input type="text" name="author" class="book-input" id="author" placeholder="Autor">
                <button class="book-button">PDF</button>
                <div class="functions">
                    <div class="option update">
                        <svg width="100" height="100" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M20 6 9 17l-5-5"></path>
                        </svg>
                        <p>Criar</p>
                    </div>

                </div>
            </div>
            <textarea name="description" id="description" cols="30" rows="10"></textarea>
            <form class="form" method="post" enctype="multipart/form-data">
                <input type="file" name="cape" class="cape-book" onChange="loader(this)">
                <input type="file" name="pdf" class="pdf-book">
            </form>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/book.js"></script>
</body>
</html>