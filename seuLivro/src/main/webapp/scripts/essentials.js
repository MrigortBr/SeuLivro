function clickUser(){
    const user = document.getElementsByClassName("user")[0]
    user.innerHTML += `
    <div class="functions">
        <p class="functions-text profile" onclick="window.location.href=' /seuLivro/profile'">Meu Perfil</p>
        <p class="functions-text my-books" onclick="window.location.href=' /seuLivro/MyBooks'">Meus Livros</p>
        <p class="functions-text exit" onclick="window.location.href=' /seuLivro/leave'">Sair</p>
    </div>
    
    `

    user.onclick = unclickUser
}


function search(){
   window.location.href = "/seuLivro/search?search=" + document.getElementsByClassName("search-input")[0].value;

}

function unclickUser(){
    const user = document.getElementsByClassName("user")[0]
    user.removeChild(document.getElementsByClassName('functions')[0])
    user.onclick = clickUser
}
