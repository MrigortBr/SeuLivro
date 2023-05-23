const stars = document.getElementsByClassName("star-svg")
const like = document.getElementsByClassName("avaliation-svg")

for (let cont= 0;cont < stars.length; cont++){
    stars[cont].setAttribute("onclick", "setStars(this)")
}

for (let cont= 0;cont < like.length; cont++){
    like[cont].setAttribute("onclick", "setRate(this)")
}

try{
	document.getElementsByClassName("read")[0].onclick = function(){
		 document.getElementsByClassName("read")[0].classList.add("delete");
		 $.ajax({
	        url: "http://localhost:8080/seuLivro/Book",
	        method: "POST",
	        data: {idBook: document.querySelectorAll(`[data-id]`)[0].getAttribute("data-id")}  
		 })
  		
	}
}catch{
}

function setRate(element){
    let value = "";

	if (document.getElementsByClassName("user")[0].onclick.toString().search("window.location.href") == -1){
	    if (element.className.baseVal.search(" like-svg") > -1){
	        value = "like"
	        element.classList.add("like-selected");
	        document.getElementsByClassName("deslike-svg")[0].classList.remove("like-selected")
	    }else{
	        value = "unlike"
	        element.classList.add("like-selected");
	        document.getElementsByClassName("like-svg")[0].classList.remove("like-selected")
	    }
	    $.ajax({
	        url: "http://localhost:8080/seuLivro/rate",
	        data: {"rate": value, idBook: document.querySelectorAll(`[data-id]`)[0].getAttribute("data-id")}    
	    })
    }else{
		document.getElementsByClassName("user")[0].click()
	}
}

function styleStars(number){
	let contStar = number;
	while (contStar != 0){
	        const star = document.querySelectorAll(`[number='${contStar}']`)[0]
	        star.classList.add("starSelected")
	        contStar--;
	    }
}


function setStars(element){
    const star = element.getAttribute("number");
    let contStar = star;

	if (document.getElementsByClassName("user")[0].onclick.toString().search("window.location.href") == -1){
	    while (contStar != 0){
	        const star = document.querySelectorAll(`[number='${contStar}']`)[0]
	        star.classList.add("starSelected")
	        contStar--;
	    }
		
	    $.ajax({
	        url: "http://localhost:8080/seuLivro/rate",
	        data: {"star": star, idBook: document.querySelectorAll(`[data-id]`)[0].getAttribute("data-id")}
	    })
    }else{
		document.getElementsByClassName("user")[0].click();
	}	
}

