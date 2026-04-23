const formulario = document.querySelector("form");
const Inome = document.getElementById("nome");
const Iemail = document.getElementById("email");
const Isenha = document.getElementById("senha");
const Iparoquia = document.getElementById("paroquia")

function cadastrar() {
    
    fetch("http:localhost:8080/cadastrar"),
    {
        headers: {
            'Accept: ': 'application/json',
            'Content-Type ': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
          nome: Inome.value,
          email: Iemail.value,
          senha: Isenha.value,
          paroquia: Iparoquia.value
        })
    }
    .then(function (res) {console.log(res)})
    .catch(function (res) {console.log(res)})
};

function limpar() {
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Iparoquia.value = "";
}

formulario.addEventListener('submit', function (event) {
    event.preventDefault();

    cadastrar();
    limpar();   
});