const updateuttons = document.getElementsByClassName("update");
const deleteButtons = document.getElementsByClassName("delete");
const canceluttons = document.getElementsByClassName("cancel");
const pdfButton = document.getElementsByClassName("book-button");
const capeButton = document.getElementsByClassName("cape-img");

for (let cont=0; cont < updateuttons.length; cont++){
    updateuttons[cont].setAttribute("onclick", `update(this)`)
}

for (let cont=0; cont < deleteButtons.length; cont++){
    deleteButtons[cont].setAttribute("onclick", `drop(this)`)
}

for (let cont=0; cont < canceluttons.length; cont++){
    canceluttons[cont].setAttribute("onclick", `cancel(this)`)
}

for (let cont=0; cont < pdfButton.length; cont++){
    pdfButton[cont].onclick = function (){
		pdfButton[cont].parentNode.parentNode.querySelector(".pdf-book").click()
	}
}

//document.getElementsByClassName("cape-book")[0].parentNode.parentNode.querySelector(".cape-img")

for (let cont=0; cont < capeButton.length; cont++){
    capeButton[cont].onclick = function (){
		const input = capeButton[cont].parentNode.querySelector(".cape-book");
		input.onchange = function(event){
			var file = event.target.files[0];
			var reader = new FileReader();

			console.log("asda")
			 reader.onload  = function(event) {
				 console.log("oi")
				 capeButton[cont].src = event.target.result;
			 };
			reader.readAsDataURL(file);
		}
		input.click();
		 
	}
}

function update(element){
    const elementBook = element.parentNode.parentNode.parentNode;
    const name = elementBook.querySelectorAll("#name")[0].value
    const author = elementBook.querySelectorAll("#author")[0].value
    console.log(author)
    const description = elementBook.querySelectorAll("#description")[0].value
    const cape = elementBook.querySelectorAll(".cape-book")[0]
    const pdf = elementBook.querySelectorAll(".pdf-book")[0]

	if (name.trim() != "" && author.trim() != "" && description.trim() != ""){
	    let formData = new FormData()
	    formData.append("name", name);
	    formData.append("author", author);
	    formData.append("description", description);
	    if (cape.files.length  > 0) formData.append("cape", cape.files[0]);
	    if (pdf.files.length  >0) formData.append("pdf", pdf.files[0]);
        if(elementBook.getAttribute("data-id") != null){
			formData.append("idBook", elementBook.getAttribute("data-id"));
		}else{
			console.log("Novo livro")
		}
        
	    $.ajax({
	        url: "http://localhost:8080/seuLivro/MyBooks",
	        method: "PUT",
	        data: formData,
	   		cache: false,
			contentType: false,
			processData: false,
		success: function(data){
			console.log("sucesso")
		}
	    })
    }
}

function drop(element){
    const elementBook = element.parentNode.parentNode.parentNode;
		    $.ajax({
	        url: "http://localhost:8080/seuLivro/MyBooks",
	        method: "POST",
	        data: {idBook: elementBook.getAttribute("data-id")},
		success: function(data){
			console.log("sucesso")
		}
	    })
}

function cancel(element){
    alert("Oi")
}
