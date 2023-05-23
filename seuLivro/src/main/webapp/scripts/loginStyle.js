
document.getElementById("login").setAttribute("onclick" ,`mode(1000, this)`)
document.getElementById("register").setAttribute("onclick",`mode(0, this)`)
let interval = null

function mode(move, element){
	 try{
	        clearInterval(interval);
	 }catch{
			
	 }

    let over = document.getElementsByClassName("background-inputs")[0]
    document.getElementsByClassName("selected")[0].className = document.getElementsByClassName("selected")[0].className.replace("selected", "")
    element.className += " selected"
    enableScroll()

    if (move > 0){
        interval = setInterval(() => {
            move -= 10
            over.scroll(0, move)
            if (move < 0 ){
                clearInterval(interval);
                disableScroll()
            }
        }, over.scrollHeight/400);
    }else{
        interval = setInterval(() => {
            move += 10
            over.scroll(0, move)
            if (move >  over.scrollHeight){
                clearInterval(interval);
                disableScroll()
            }
        }, over.scrollHeight/1000);
    }
}

function disableScroll() {
    // Get the current page scroll position
    scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    scrollLeft = window.pageXOffset || document.documentElement.scrollLeft,
  
    // if any scroll is attempted, set this to the previous value
    document.getElementsByClassName("background-inputs")[0].onscroll = function() {
        document.getElementsByClassName("background-inputs")[0].scrollTo(scrollLeft, scrollTop);
    };
}

disableScroll();

function enableScroll() {
    document.getElementsByClassName("background-inputs")[0].onscroll = function() {};
};
