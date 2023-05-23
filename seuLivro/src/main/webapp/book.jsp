<%@page import="java.text.DecimalFormat"%>
<%@page import="object.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="styles/bookPage.css">
    <link rel="stylesheet" href="styles/header.css">
</head>
<body>
	<%@ include file="partials/header.jsp" %>
	<%Book book = (Book) request.getAttribute("book");
		byte[] imagemBytes = book.getCape();
		String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);
		DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(2));
	%>
    <div class="book" data-id=<%=book.getId() %>>
        <img src="data:image/png;base64,<%= imagemBase64 %>"alt="" class="book-cover">
        <div class="book-informations">
            <h1 class="title">
                <%=book.getName() %>
            </h1>
            <h2 class="writer">
               <%=book.getAuthor() %> - <a href="/seuLivro/profile?tag=<%=book.getTag()%>">@<%=book.getTag() %> </a>
            </h2>

            <div class="avaliations">
                <div class="like-deslike">
                    <div class="like">
                        <svg class="avaliation-svg like-svg <%try{if (book.getRate().equals("like")){ %>like-selected"<%}}catch(Exception e){} %> width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"></path>
                        </svg>
                        <p class="like-deslike-text like-text"><%=book.getSumLikes()%></p>
                    </div>
                    
                    <div class="deslike">
                        <svg class="avaliation-svg deslike-svg <% try{if(book.getRate().equals("unlike")){ %>like-selected<%}}catch(Exception e){} %>" width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="M10 15v4a3 3 0 0 0 3 3l4-9V2H5.72a2 2 0 0 0-2 1.7l-1.38 9a2 2 0 0 0 2 2.3zm7-13h2.67A2.31 2.31 0 0 1 22 4v7a2.31 2.31 0 0 1-2.33 2H17"></path>
                        </svg>
                        <p class="like-deslike-text deslike-text"><%=book.getSumUnlikes() %></p>
                    </div>
                </div>
                
				<%if (book.isSelected() == true){ %>
				  <div class="read">
                    <svg  fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
						<path d="M18 6 6 18"></path>
						<path d="m6 6 12 12"></path>
					</svg>
                  </div>
                  <%} %>
    
    
                <div class="avaliations-stars">
                    <div class="five-stars">
                        <svg class="star-svg five" number=5 width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"></path>
                        </svg>
                        <svg class="star-svg four" number=4 width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"></path>
                        </svg>
                        <svg class="star-svg three" number=3 width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"></path>
                        </svg>
                        <svg class="star-svg two" number=2 width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"></path>
                        </svg>
                        <svg class="star-svg one" number=1 width="35" height="35" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="0.5" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path d="m12 2 3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"></path>
                        </svg>
                    </div>
                    
                    <p class="notaFinal"><%=decimalFormat.format(book.getAvgStars()) %></p>
                </div>
            </div>
            

            <h2 class="description">
				<%=book.getDescription() %>
            </h2>
            <button class="pdf" onclick="window.location.href='/seuLivro/Pdf?id=<%=book.getId() %>'")>
                PDF
            </button>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/PageBook.js"></script>
    
    <%if (book.getStars() > 0){ %>
    	<script>styleStars(<%=book.getStars()%>)</script>
    <%} %>
    
    
</body>
</html>